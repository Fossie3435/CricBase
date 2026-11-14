package uk.org.cricbase.Services;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import uk.org.cricbase.DTOs.PlayerRosterCreateRequest;
import uk.org.cricbase.DTOs.RosterCreateRequest;
import uk.org.cricbase.DTOs.RosterSummary;
import uk.org.cricbase.DTOs.RosterTournamentEditionEntry;
import uk.org.cricbase.Mappers.PlayerMapper;
import uk.org.cricbase.Mappers.RosterMapper;
import uk.org.cricbase.Mappers.TournamentMapper;
import uk.org.cricbase.Models.Player;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;
import uk.org.cricbase.Models.TournamentEdition;

@Service
public class RosterService {
	private final RosterMapper rosterMapper;
	private final PlayerMapper playerMapper;
	private final TournamentMapper tournamentMapper;

	public RosterService(RosterMapper rosterMapper, TournamentMapper tournamentMapper, PlayerMapper playerMapper) {
		this.rosterMapper = rosterMapper;
		this.tournamentMapper = tournamentMapper;
		this.playerMapper = playerMapper;
	}

	public void addNewRoster(RosterCreateRequest request) {
		Roster roster = request.createRoster();	
		TournamentEdition te = tournamentMapper.findTournamentEditionById(roster.getTournament().getId());
		roster.setDefaultDates(te.getStart(), te.getEnd());
		try {
			this.rosterMapper.insertRoster(roster);
		} catch (DataAccessException e) {
			System.out.println("roster insert failed");
		}
		try {
			for(PlayerRosterContainer p : roster.getPlayers()) {
				this.rosterMapper.insertPlayerOnRoster(p.getPlayer(), p.getRoster().getId());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("player insert failed");
			// log missing player
		}
		
	}

    public void addPlayersToRoster(long rosterId, List<PlayerRosterCreateRequest> request) {
		Roster roster = new Roster();
		roster.setId(rosterId);
		TournamentEdition tournamentEdition = this.tournamentMapper.findTournamentEditionById(rosterId);
		if(tournamentEdition != null) {
			for(PlayerRosterCreateRequest p : request) {
				try {
					PlayerRosterContainer prc = p.getPlayerRosterContainer(roster);
					if(prc.getStart() == null) {
						prc.setStart(tournamentEdition.getStart());
					}
					if(prc.getEnd() == null) {
						prc.setEnd(tournamentEdition.getEnd());
					}
					this.rosterMapper.insertPlayerOnRoster(prc.getPlayer(), prc.getRoster().getId());
				} catch (DataAccessException e) {
					System.out.println("Player addition failed");
				}
			}
		}
	}

    public RosterSummary getRosterById(long rosterId) {
		return (this.rosterMapper.findRosterSummaryById(rosterId));
    }

    public List<RosterTournamentEditionEntry> getAllRosters() {
		return this.rosterMapper.findAllRosterSummaries();
    }

    public void insertRoster(Roster r) {
		this.rosterMapper.insertRoster(r);
    }


	public void insertCappedPlayersOnRoster(long rosterId) {
		List<Player> players = this.playerMapper.findPlayersOnRoster(rosterId);	
		for(Player player: players) {
			if(!rosterMapper.isPlayerOnRoster(player, rosterId)) {
				this.rosterMapper.insertPlayerOnRoster(player, rosterId);
			}
		}
	}
}
