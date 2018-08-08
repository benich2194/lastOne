package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import utils.E_Levels;
import utils.E_Position;

/**
 * Class Player ~ represent a single Player in the league,
 * inheritor of coach
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Player extends Coach implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5491200368814041188L;
	// -------------------------------Class Members------------------------------
	private long value;
	private boolean isRightLegKicker;
	private E_Position position;
	private HashSet<Match> matches;

	// -------------------------------Constructors------------------------------
	public Player(int id,String password, String firstName, String lastName, Date birthdate, Date startWorkingDate, Address address,
			E_Levels level, long value, boolean isRightLegKicker, E_Position position) {
		super(id,password, firstName, lastName, birthdate, startWorkingDate, address, level);
		this.value = value;
		this.isRightLegKicker = isRightLegKicker;
		this.position = position;
		this.matches = new HashSet<>();
	}
	
	public Player(int id) {
		super(id);
	}

	// -------------------------------Getters And Setters------------------------------
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public boolean isRightLegKicker() {
		return isRightLegKicker;
	}

	public void setRightLegKicker(boolean isRightLegKicker) {
		this.isRightLegKicker = isRightLegKicker;
	}

	public E_Position getPosition() {
		return position;
	}

	public void setPosition(E_Position position) {
		this.position = position;
	}

	public HashSet<Match> getMatches() {
		return matches;
	}

	public void setMatches(HashSet<Match> matches) {
		this.matches = matches;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds the player to the given team and removes the player from its current team
	 * only if the given team doesn't equal to the Player's current team.
	 *
	 * @param team
	 * @return true if the player was added successfully to team, false otherwise
	 */
	public boolean transferTo(Team team) {
		if (team == null) {// if team is null, return false
			return false;
		}
		if (getCurrentTeam() == null) {// if current team of player is null, cannot transfer(has no team)
			return false;
		}
		if (getCurrentTeam().equals(team)) {// if he is already in this team, return false
			return false;
		}
		for(Player p:team.getPlayers().keySet()) {//if player exists in this team, return false
			if(p!=null&&p.getId()==this.getId()) {
				return false;
			}
		}
		getCurrentTeam().removePlayer(this);// remove player from his current team
		this.setCurrentTeam(null);// set his current team to null
		if (!team.addPlayer(this)) {// if cannot add player, return false
			return false;
		}
		setCurrentTeam(team);// set player current team to the new team and return true
		return true;
	}

	/**
	 * This method adds a new match to the matches array only iF the match doesn't
	 * already exists in the Player's matches array and the time doesn't overlap
	 * with any other match in the array
	 * 
	 * @param match
	 * @return true if the match was added successfully, false otherwise
	 */
	public boolean addMatch(Match match) {
		if (match == null) {// if match is null,return false
			return false;
		}
		if (matches.contains(match)) {// if match already exists, return false
			return false;
		}
		for (Match m : matches) {
			if ((m.getStartDateTime().before(match.getFinishDateTime())
					|| m.getStartDateTime().equals(match.getFinishDateTime()))
					&& (match.getFinishDateTime().before(m.getFinishDateTime())
							|| match.getFinishDateTime().equals(m.getFinishDateTime()))) {
				return false;// if match overlaps with other matches,return false
			}
		}
		if (!matches.add(match)) {// if unable to add match, return false
			return false;
		}
		return true;// return true
	}

	/**
	 * This method removes a given match from the matches array.
	 * 
	 * @param match
	 * @return true if match was removed successfully, false otherwise
	 */
	public boolean removeMatch(Match match) {
		if (match == null) {// if match is null, return false
			return false;
		}
		if (!matches.contains(match)) {// if matches does not exist, return false
			return false;
		}
		if (!matches.remove(match)) {// if unable to remove match, return false
			return false;
		}
		return true;// return true
	}
}
