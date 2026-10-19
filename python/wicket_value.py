import pandas as pd
from sqlalchemy import create_engine
from xgboost import XGBRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, mean_absolute_error


engine = create_engine(
    "postgresql+psycopg://postgres:your_password@localhost:5432/cricbase"
)

df = pd.read_sql("""
    
    WITH tournament_averages AS (
        SELECT
            m.tournament_id,
            AVG(d.runs) AS average
        FROM deliveries d
        JOIN overs o ON d.over_id = o.id
        JOIN innings i ON o.inning_id = i.id
        JOIN matches m ON i.match_id = m.id
        JOIN tournament_editions te ON m.tournament_id = te.id
        WHERE te.tournament_id = 1
        GROUP BY m.tournament_id
    )
    SELECT 
        (101 - d.delivery_count - 5*(o.over)) AS balls_left,
        d.wickets AS wickets_so_far,
        d.total AS total_so_far,
        ta.average AS tournament_average,
        i.total_runs AS total,
        m.tournament_id AS tournament_id
    FROM deliveries d 
    JOIN batting_performances bp1 ON d.batting_performance_id = bp1.id
    JOIN batting_performances bp2 ON d.non_striker_batting_performance_id = bp2.id
    JOIN overs o ON d.over_id = o.id 
    JOIN innings i ON o.inning_id = i.id 
    JOIN matches m ON i.match_id = m.id 
    JOIN tournament_averages ta ON m.tournament_id = ta.tournament_id
    WHERE m.result_type != 'NO_RESULT'
    AND i.inning_number = 1;

""", engine)

print(df.head(200))

# Split data into training and testing
train_df = df[df["tournament_id"].isin([1, 2, 3, 4, 5, 6])]
test_df = df[df["tournament_id"] == 6]

features = [
    "balls_left",
    "wickets_so_far",
    "total_so_far",
    "tournament_average"
]

X_train = train_df[features]
y_train = train_df["total"]

X_test = test_df[features]
y_test = test_df["total"]
# Create model
model = XGBRegressor(
    n_estimators=300,
    max_depth=4,
    learning_rate=0.05,
    objective="reg:squarederror"
)

# Train
model.fit(X_train, y_train)

predictions = model.predict(X_test)

importance = pd.Series(
    model.feature_importances_,
    index=X_train.columns
).sort_values(ascending=False)

print(importance)

print("MAE:", mean_absolute_error(y_test, predictions))
print("RMSE:", mean_squared_error(y_test, predictions) ** 0.5)

balls_left_values = [80, 60, 40, 20, 10, 5]

rows = []

for balls_left in balls_left_values:
    for wickets in range(10):
        rows.append({
            "balls_left": balls_left,
            "wickets_so_far": wickets,
            "tournament_average": 1.45
        })

test_df = pd.DataFrame(rows)

# Predict with your trained model
test_df["predicted_total"] = model.predict(test_df)

# Make it easier to inspect
prediction_table = test_df.pivot(
    index="wickets_so_far",
    columns="balls_left",
    values="predicted_total"
)

print(prediction_table.round(1))
