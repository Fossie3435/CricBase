/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

import java.time.LocalDate;
import java.util.List;

/**
 *
 */
public class MatchSummary {
    private long id;
    private String season;
	private String gender;
	private int ballsPerOver;
    private int matchNumber;
    private String format;
    private String teamType;
    private int overs;
	private LocalDate date;

    private List<InningSummary> innings;
    private GroundSummary ground;
    private TournamentEditionSummary tournament;
	private ResultSummary result;
	private PlayerDto potm;
	private TossSummary toss;

    public MatchSummary() {    
    }

    public long getId() {
        return id;
    }
 	public String getSeason() {
        return season;
    }
    public String getTeamType() {
        return teamType;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public List<InningSummary> getInnings() {
        return innings;
    }

    public void setInnings(List<InningSummary> innings) {
        this.innings = innings;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    } 

    public GroundSummary getGround() {
        return ground;
    }

    public void setGround(GroundSummary ground) {
        this.ground = ground;
    }



	public void setId(long id) {
		this.id = id;
	}



	public void setSeason(String season) {
		this.season = season;
	}



	public void setMatchNumber(int matchNumber) {
		this.matchNumber = matchNumber;
	}



	public String getFormat() {
		return format;
	}



	public void setFormat(String format) {
		this.format = format;
	}



	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public TournamentEditionSummary getTournament() {
		return tournament;
	}



	public void setTournament(TournamentEditionSummary tournament) {
		this.tournament = tournament;
	}



	public ResultSummary getResult() {
		return result;
	}

	public void setResult(ResultSummary result) {
		this.result = result;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getBallsPerOver() {
		return ballsPerOver;
	}

	public void setBallsPerOver(int ballsPerOver) {
		this.ballsPerOver = ballsPerOver;
	}

	public TossSummary getToss() {
		return toss;
	}

	public void setToss(TossSummary toss) {
		this.toss = toss;
	}

	public PlayerDto getPotm() {
		return potm;
	}

	public void setPotm(PlayerDto potm) {
		this.potm = potm;
	}
}
