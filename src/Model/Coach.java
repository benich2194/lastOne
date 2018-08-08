package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import utils.E_Levels;

/**
 * Class Coach ~ represent a single Coach in the league,
 * every coach has a current team that being coached by that coach.
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Coach extends Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8942513137021909081L;
	// -------------------------------Class Members------------------------------
	private Team currentTeam;
	private E_Levels level;
	private HashSet<Team> teams;

	// -------------------------------Constructors------------------------------
	public Coach(int id,String password, String firstName, String lastName, Date birthdate, Date startWorkingDate, Address address,
			E_Levels level) {
		super(id,password, firstName, lastName, birthdate, startWorkingDate, address);
		this.level = level;
		this.teams = new HashSet<>();
	}

	public Coach(int id) {
		super(id);
	}

	// -------------------------------Getters And Setters------------------------------
	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public HashSet<Team> getTeams() {
		return teams;
	}

	public void setTeams(HashSet<Team> teams) {
		this.teams = teams;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds the coach to the given team and removes the coach from its current team
	 * only if the given team doesn't equal to the Coach's current team.
	 *
	 * @param team
	 * @return true if the coach was added successfully to team, false otherwise
	 */
	public boolean transferTo(Team team) {
		if (team != null && currentTeam != null && team != currentTeam) {
			currentTeam.setCoach(null);
			if (addTeam(currentTeam)) { 
				if (team.registerCoach(this)) 
					return true;
				removeTeam(team);
			} else return team.registerCoach(this);
		}
		return false;
	}
	
	/**
	 * This method adds a new team to the teams array 
	 * only if the given team doesn't already exists in the Coach's teams array.
	 * 
	 * @param team
	 * @return true if the team was added successfully, false otherwise
	 */
	protected boolean addTeam(Team team) {
		if (team != null && !teams.contains(team)) 
			return teams.add(team);
		return false;
	}

	/**
	 * This method removes a given team form the teams array.
	 * 
	 * @param team
	 * @return true if the team was removed successfully, false otherwise
	 */
	protected boolean removeTeam(Team team) {
		if (team != null && teams.contains(team))
			return teams.remove(team);
		return false;
	}
	
}
