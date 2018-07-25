package Model;

import utils.Constants;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Stadium ~ represent a single stadium of the league
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Stadium {
	// -------------------------------Class Members------------------------------
	private int id;
	private String name;
	private Address address;
	private int capacity;
	private Set<Receptionist> receptionists;
	private Set<Team> teams;
	private Set<Match> matches;

	// -------------------------------Constructors------------------------------
	public Stadium(int id, String name, Address address, int capacity) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.capacity = capacity;
		this.receptionists = new HashSet<>();
		this.teams = new HashSet<>();
		this.matches = new HashSet<>();
	}

	public Stadium(int id) {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Set<Receptionist> getReceptionists() {
		return receptionists;
	}

	public void setReceptionists(HashSet<Receptionist> receptionists) {
		this.receptionists = receptionists;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(HashSet<Team> teams) {
		this.teams = teams;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public void setMatches(HashSet<Match> matches) {
		this.matches = matches;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a new receptionist to the receptionists array 
	 * only if the given receptionist doesn't already exist in the Stadium's receptionists array.
	 * 
	 * @param receptionist
	 * @return true if the receptionist was added successfully, false otherwise
	 */
	public boolean addReceptionist(Receptionist receptionist) {
		if (receptionist != null && !receptionists.contains(receptionist)) 
			return receptionists.add(receptionist);
		return false;
	}

	/**
	 * This method removes a given receptionist form the receptionists array.
	 * 
	 * @param receptionist
	 * @return true if the receptionist was removed successfully, false otherwise
	 */
	public boolean removeReceptionist(Receptionist receptionist) {
		if (receptionist != null && receptionists.contains(receptionist))
			return receptionists.remove(receptionist);
		return false;
	}

	/**
	 * This method adds a new team to the teams array 
	 * only if the given team doesn't already exists in the Stadium's teams array.
	 * 
	 * @param team
	 * @return true if the team was added successfully, false otherwise
	 */
	public boolean addTeam(Team team) {
		if (team != null && !teams.contains(team) && teams.size() < Constants.MAX_TEAMS_FOR_STADIUM)
			return teams.add(team);
		return false;
	}

	/**
	 * This method removes a given team form the teams array.
	 * 
	 * @param team
	 * @return true if the team was removed successfully, false otherwise
	 */
	public boolean removeTeam(Team team) {
		if (team != null && teams.contains(team))
			return teams.remove(team);
		return false;
	}

	/**
	 * This method adds a new match to the matches array
	 * only iF the match doesn't already exists in the Stadium's matches array and 
	 * the time doesn't overlap with any other match in the array
	 * 
	 * @param match
	 * @return true if the match was added successfully, false otherwise
	 */
	public boolean addMatch(Match match) {
		if (match != null) {
			for (Match m : matches) 
				if (m.equals(match)) 
					return false;
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
		Stadium other = (Stadium) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stadium | id: " + id + ", name: " + name + ", capacity: " + capacity + ", address: " + address;
	}

}
