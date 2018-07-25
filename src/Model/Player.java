package Model;

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
public class Player extends Coach {
	// -------------------------------Class Members------------------------------
	private long value;
	private boolean isRightLegKicker;
	private E_Position position;
	private HashSet<Match> matches;

	// -------------------------------Constructors------------------------------
	public Player(int id, String firstName, String lastName, Date birthdate, Date startWorkingDate, Address address,
			E_Levels level, long value, boolean isRightLegKicker, E_Position position) {
		super(id, firstName, lastName, birthdate, startWorkingDate, address, level);
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
		//TODO
		return false;
	}
	/**
	 * This method adds a new match to the matches array
	 * only iF the match doesn't already exists in the Player's matches array and 
	 * the time doesn't overlap with any other match in the array
	 * 
	 * @param match
	 * @return true if the match was added successfully, false otherwise
	 */
	public boolean addMatch(Match match) {
		if (match != null && !matches.contains(match)) {
			Date start = match.getStartDateTime();
			Date end = match.getFinishDateTime();
			for (Match m : matches) {
				if (m == null) continue;
				if (start.before(m.getStartDateTime()) && end.after(m.getStartDateTime()))
					return false;
				if (start.before(m.getFinishDateTime()) && end.after(m.getFinishDateTime()))
					return false;
				if (start.equals(m.getStartDateTime())) 
					return false;
			}
			return matches.add(match);
		}
		return false;
	}
	
	/**
	 * This method removes a given match from the matches array.
	 * 
	 * @param match
	 * @return true if match was removed successfully, false otherwise
	 */
	public boolean removeMatch(Match match) {
		if (match != null && matches.contains(match)) 
			return matches.remove(match);
		return false;
	}
	
}
