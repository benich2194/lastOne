package Model;

import java.io.Serializable;
import java.util.*;

import Exceptions.MaximumReachedException;
import Exceptions.ObjectExistsException;
import utils.Constants;
import utils.E_Levels;

/**
 * Class Team ~ represent a single team of the league
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7216260411613046779L;
	// -------------------------------Class Members------------------------------
	private int id;
	private String name;
	private int value;
	private E_Levels level;
	private Stadium stadium;
	private Coach coach;
	private Map<Player, Boolean> players;
	private Set<Match> matches;

	// -------------------------------Constructors------------------------------
	public Team(int id, String name, int value, E_Levels level, Stadium stadium) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.level = level;
		this.stadium = stadium;
		this.players = new TreeMap<>();
		this.matches = new HashSet<>();
	}
	
	public Team(int id) {
		this.id = id;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public Stadium getStadium() {
		return stadium;
	}
	
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public Map<Player, Boolean> getPlayers() {
		return players;
	}

	public void setPlayers(TreeMap<Player, Boolean> players) {
		this.players = players;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(HashSet<Match> matches) {
		this.matches = matches;
	}
	
	// -------------------------------More Methods------------------------------
	/**
	 * This method registers a new coach to the team only if coach's level is
	 * greater or equal to team's level
	 * 
	 * @param coach
	 * @return true if coach was registered successfully, false otherwise
	 */
	public boolean registerCoach(Coach coach) {
		if (coach == null) {// if coach is null, return false
			return false;
		}
		if (coach.getLevel().getLevel() < level.getLevel()) {// if coach level is smaller than team level, return false
			return false;
		}
		setCoach(coach);
		return true;// return true
	}

	/**
	 * This method adds a new player to the players array only if the given player
	 * doesn't already exists in the Team's players array.
	 * 
	 * @param employee
	 * @return true if the player was added successfully, false otherwise
	 * @throws MaximumReachedException 
	 */
	public boolean addPlayer(Player player) throws MaximumReachedException {
		if (player == null) {// if player is null, return false
			return false;
		}
		if (players.size() == Constants.MAX_PLAYERS_FOR_TEAM) {// if maximum number of players has reached, return false
			throw new MaximumReachedException("maximum players in this team");
		}
		if (players.containsKey(player)) {// if player already exists, return false
			return false;
		}
		players.put(player, false);// add player to team
		player.setCurrentTeam(this);// sets player current team to this team
		return true;// return true
	}

	/**
	 * This method removes a given player form the players array.
	 * 
	 * @param player
	 * @return true if the player was removed successfully, false otherwise
	 */
	public boolean removePlayer(Player player) {
		if (player == null) {// if player is null, return false
			return false;
		}
		if (!players.containsKey(player)) {// if player does not exist, return false
			return false;
		}
		if (players.remove(player)==null) {// if cannot remove player, return false
			return false;
		}
		player.setCurrentTeam(null);// set this player team to null(has no team) and return true
		return true;
	}

	/**
	 * This method adds a new player to the first team players array only if the
	 * given player doesn't already exists in the Team's first players array.
	 * 
	 * @param player
	 * @return true if the player was added successfully, false otherwise
	 * @throws ObjectExistsException 
	 */
	public boolean addPlayerToFirstTeam(Player player) throws ObjectExistsException {
		if (player == null) {// if player is null, return false
			return false;
		}
		int counter = 0;// initialize counter to count how many first team players exist
		for (Boolean b : players.values()) {
			if (b == true) {
				counter++;
			}
		}
		if (counter == Constants.NUM_OF_FIRST_TEAM_PLAYERS) {// if there are maximum number of first team players,
																// return false
			return false;
		}
		if (players.containsKey(player)) {// if player exists in team
			if (players.get(player).booleanValue() == true) {// if he is already in first team players array, return
																// false
				throw new ObjectExistsException("player already is a first team player");
			} else {
				players.remove(player);// if he wasn't a first team player, remove him and add him as a first team
										// player
				players.put(player, true);
				return true;
			}

		}
		players.put(player, true);// add player to first team
		return true;// return true
	}

	/**
	 * This method replace a given player form the first players array with another
	 * given player.
	 * 
	 * @param player
	 * @return true if the player was removed successfully, false otherwise
	 * @throws ObjectExistsException 
	 * @throws MaximumReachedException 
	 */
	public boolean replacePlayerOfFirstTeam(Player oldPlayer, Player newPlayer) throws ObjectExistsException, MaximumReachedException {
		if (oldPlayer == null || newPlayer == null || !players.containsKey(oldPlayer) || players.get(oldPlayer)) {// if
																													// one
																													// of
																													// the
																													// players
																													// is
																													// null
																													// or
																													// old
																													// player
																													// does
																													// not
																													// exist
																													// or
																													// new
																													// player
																													// exists
																													// already
																													// or
																													// player
																													// is
																													// already
																													// a
																													// first
																													// team
																													// player,
																													// retur
																													// nfalse
			return false;
		}
		if (!removePlayer(oldPlayer)) {// remove him from team, if failed return false
			return false;
		}
		if (!addPlayerToFirstTeam(newPlayer)) {// add him as first team player, if failed add him as regular player
			addPlayer(oldPlayer);
			return false;
		}
		return true;// returns true if succesful
	}

	/**
	 * This method adds a new match to the matches array only iF the match doesn't
	 * already exists in the Team's matches array and the time doesn't overlap with
	 * any other match in the array
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

	/**
	 * This method returns the total number of goals from home wins divided by the
	 * total number of goals from away wins
	 * 
	 * @return rate if valid, 0 otherwise
	 */
	public double getHomeAwayWinningsRate() {
		int awayGoals = 0, homeGoals = 0;// counter for homegoals and awaygoals
		for (Match m : matches) {
			if (m.getHomeTeam().equals(this) && (m.getHomeTeamScore() > m.getAwayTeamScore())) {// if was home team and
																								// won, add to homegoals
																								// the away team score
																								// and the home team
																								// score
				homeGoals += m.getAwayTeamScore() + m.getHomeTeamScore();
			} else if (m.getAwayTeam().equals(this) && m.getAwayTeamScore() > m.getHomeTeamScore()) {// if was away team
																										// and won, add
																										// to awaygoals
																										// the away team
																										// score
				awayGoals += m.getAwayTeamScore()+m.getHomeTeamScore();
			}
		}
		if (awayGoals == 0) {// if away goals is 0, return 0;
			return 0;
		}
		return (homeGoals / awayGoals);// return the winning rate
	}

	// -------------------------------hashCode equals &
	// toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		if(stadium!=null) {
			return "Team | id: " + id + ", name: " + name + ", value: " + value + ", level: " + level + ", stadium: "
					+ stadium.getName() + ", coach: "
					+ (coach != null ? coach.getFirstName() + " " + coach.getLastName() : "team has no coach");
		}
		return "Team | id: " + id + ", name: " + name + ", value: " + value + ", level: " + level + ", coach: "
		+ (coach != null ? coach.getFirstName() + " " + coach.getLastName() : "team has no coach");
	}

}
