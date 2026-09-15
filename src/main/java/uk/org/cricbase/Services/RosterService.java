package uk.org.cricbase.Services;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uk.org.cricbase.DTOs.PlayerRosterCreateRequest;
import uk.org.cricbase.DTOs.RosterCreateRequest;
import uk.org.cricbase.Mappers.RosterMapper;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;

@Service
public class RosterService {
	private final RosterMapper rosterMapper;

	public RosterService(RosterMapper rosterMapper) {
		this.rosterMapper = rosterMapper;
	}

	public void addNewRoster(RosterCreateRequest request) {
		Roster roster = request.createRoster();	
		try {
			this.rosterMapper.insertRoster(roster);
		} catch (DataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "tournament edition not found with that id");
		}
		try {
			for(PlayerRosterContainer p : roster.getPlayers()) {
				this.rosterMapper.insertPlayerOnRoster(p);
			}
		} catch (DataAccessException e) {
			// log missing player
		}
		
	}

    public void addPlayersToRoster(long rosterId, List<PlayerRosterCreateRequest> request) {
		Roster roster = new Roster();
		roster.setId(rosterId);

		for(PlayerRosterCreateRequest p : request) {
			try {
				this.rosterMapper.insertPlayerOnRoster(p.getPlayerRosterContainer(roster));
			} catch (DataAccessException e) {
				// log failed insert players	
			}
		}
	}			
}
