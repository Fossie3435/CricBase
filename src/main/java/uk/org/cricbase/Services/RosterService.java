package uk.org.cricbase.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uk.org.cricbase.DTOs.PlayerRosterCreateRequest;
import uk.org.cricbase.DTOs.RosterCreateRequest;
import uk.org.cricbase.DTOs.RosterSummary;
import uk.org.cricbase.Mappers.RosterMapper;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;
import uk.org.cricbase.Models.TournamentEdition;

@Service
public class RosterService {
	private final RosterMapper rosterMapper;
	private final TournamentService tournamentService;

	public RosterService(RosterMapper rosterMapper, TournamentService tournamentService) {
		this.rosterMapper = rosterMapper;
		this.tournamentService = tournamentService;
	}

	public void addNewRoster(RosterCreateRequest request) {
		Roster roster = request.createRoster();	
		TournamentEdition te = tournamentService.findTournamentEditionByRosterId(roster.getId()).get();
		roster.setDefaultDates(te.getStart(), te.getEnd());
		try {
			this.rosterMapper.insertRoster(roster);
		} catch (DataAccessException e) {
			System.out.println("roster insert failed");
		}
		try {
			for(PlayerRosterContainer p : roster.getPlayers()) {
				this.rosterMapper.insertPlayerOnRoster(p);
			}
		} catch (DataAccessException e) {
			System.out.println("player insert failed");
			// log missing player
		}
		
	}

    public void addPlayersToRoster(long rosterId, List<PlayerRosterCreateRequest> request) {
		Roster roster = new Roster();

		TournamentEdition tournamentEdition = this.tournamentService.findTournamentEditionByRosterId(rosterId).get();
		for(PlayerRosterCreateRequest p : request) {
			
			try {
				PlayerRosterContainer prc = p.getPlayerRosterContainer(roster);
				if(prc.getStart() == null) {
					prc.setStart(tournamentEdition.getStart());
				}
				if(prc.getEnd() == null) {
					prc.setEnd(tournamentEdition.getEnd());
				}
				this.rosterMapper.insertPlayerOnRoster(prc);
			} catch (DataAccessException e) {
				// log failed insert players	
			}
		}
	}

    public Optional<RosterSummary> getRosterById(long rosterId) {
		return Optional.of(this.rosterMapper.findRosterSummaryById(rosterId));
    }			
}
