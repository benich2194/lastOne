package Model;

import java.util.*;

import utils.Constants;
import utils.E_Levels;

/**
 * Class Team ~ represent a single team of the league
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Team {
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
	 * This method registers a new coach to the team
	 * only if coach's level is greater or equal to team's level
	 * 
	 * @param coach
	 * @return true if coach was registered successfully, false otherwise
	 */
	public boolean registerCoach(Coach coach) {
		if (coach != null)
			if (coach.getLevel().getLevel() >= getLevel().getLevel()) {
				setCoach(coach);
				return true;
			}
		return false;
	}
	
	/**
	 * This method adds a new player to the players array 
	 * only if the given player doesn't already exists in the Team's players array.
	 * 
	 * @param employee
	 * @return true if the player was added successfully, false otherwise
	 */
	public boolean addPlayer(Player player) {
		if (player != null && !players.containsKey(player)) {
			if (players.put(player, false) == null)
			return true;
		}
		return false;
	}

	/**
	 * This method removes a given player form the players array.
	 * 
	 * @param player
	 * @return true if the player was removed successfully, false otherwise
	 */
	public boolean removePlayer(Player player) {
		if (player != null && players.containsKey(player))
			if (players.remove(player) != null)
				return true; 
		return false;
	}
	
	/**
	 * This method adds a new player to the first team players array 
	 * only if the given player doesn't already exists in the Team's first players array.
	 * 
	 * @param player
	 * @return true if the player was added successfully, false otherwise
	 */
	public boolean addPlayerToFirstTeam(Player player) {
		int ftCount = 0;
		if (player != null && players.containsKey(player)) {
			for (Boolean b : players.values()) 
				if (b != null && b)
					ftCount++;
			if (ftCount < Constants.NUM_OF_FIRST_TEAM_PLAYERS) 
				return !players.put(player, true);
		}
		return false;
	}

	/**
	 * This method replace a given player form the first players array
	 * with another given player.
	 * 
	 * @param player
	 * @return true if the player was removed successfully, false otherwise
	 */
	public boolean replacePlayerOfFirstTeam(Player oldPlayer, Player newPlayer) {
		boolean isFound = false;
		if (newPlayer != null &&
				players.containsKey(newPlayer) &&
				!players.get(newPlayer))
			isFound = true;

		if (oldPlayer != null &&
				players.containsKey(oldPlayer) &&
				players.get(oldPlayer) &&
				isFound)

			if (players.put(oldPlayer, false)
					&& !players.put(newPlayer, true))
				return true;

		return false;
	}
	
	/**
	 * This method adds a new match to the matches array
	 * only iF the match doesn't already exists in the Team's matches array and 
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
	
	public double getHomeAwayWinningsRate() {
		double homeWinGoals = 0, awayWinGoals = 0;
		for (Match m : matches) 
			if (m != null)  {
				if (m.getHomeTeam().equals(this) && m.getHomeTeamScore() > m.getAwayTeamScore()) {
					homeWinGoals += m.getHomeTeamScore();
				}
				if (m.getAwayTeam().equals(this) && m.getHomeTeamScore() < m.getAwayTeamScore()) {
					awayWinGoals += m.getAwayTeamScore();
				}
			}

		if (awayWinGoals == 0) 
			return 0;
		return (double) homeWinGoals / awayWinGoals;
	}

	// -------------------------------hashCode equals & toString------------------------------
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
		return "Team | id: " + id + ", name: " + name + ", value: " + value + ", level: " + level + ", stadium: " + stadium.getName()
				+ ", coach: " + (coach != null ? coach.getFirstName() + " " + coach.getLastName() : "team has no coach");
	}

}
