import pandas as pd
from sqlalchemy import create_engine
from xgboost import XGBRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, mean_absolute_error


engine = create_engine(
    "postgresql+psycopg://postgres:your_password@localhost:5432/cricbase"
)

df = pd.read_sql("""
    WITH ground_averages AS (
        SELECT
            m.ground_id,
            AVG(d.runs) AS average
        FROM deliveries d
        JOIN overs o ON d.over_id = o.id
        JOIN innings i ON o.inning_id = i.id
        JOIN matches m ON i.match_id = m.id
        JOIN tournament_editions te ON m.tournament_id = te.id
        WHERE te.tournament_id = 1 AND te.id != 6
        GROUP BY m.ground_id
    ),
    tournament_averages AS (
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
        d.wickets AS wickets,
        i.inning_number AS inning_number,
        d.runs AS runs,
        d.total AS total,
        i.target AS target,
        ga.average AS ground_average,
        ta.average AS tournament_average,
        m.tournament_id AS tournament_id
    FROM deliveries d 
    JOIN batting_performances bp1 ON d.batting_performance_id = bp1.id
    JOIN batting_performances bp2 ON d.non_striker_batting_performance_id = bp2.id
    JOIN overs o ON d.over_id = o.id 
    JOIN innings i ON o.inning_id = i.id 
    JOIN matches m ON i.match_id = m.id 
    JOIN ground_averages ga ON m.ground_id = ga.ground_id
    JOIN tournament_averages ta ON m.tournament_id = ta.tournament_id
    WHERE m.result_type != 'NO_RESULT';
""", engine)

df["runs_required"] = df["target"] - df["total"]
df["chasing"] = df["target"].notna().astype(int)
df["powerplay"] = (df["balls_left"] > 80).astype(int)
print(df.head(200))

# Split data into training and testing
train_df = df[df["tournament_id"].isin([1, 2, 3, 4, 5])]
test_df = df[df["tournament_id"] == 6]

features = [
    "balls_left",
    "wickets",
    "ground_average",
    "tournament_average",
    "runs_required",
    "chasing"
]

X_train = train_df[features]
y_train = train_df["runs"]

X_test = test_df[features]
y_test = test_df["runs"]
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

print("MAE:", mean_absolute_error(y_test, predictions))
print("RMSE:", mean_squared_error(y_test, predictions) ** 0.5)
