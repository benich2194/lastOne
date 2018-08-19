package Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import Exceptions.ObjectExistsException;
import utils.Constants;
import utils.E_Levels;

/**
 * Class Match ~ represent a single Match of the league
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Match implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -203686949049209767L;
	// -------------------------------Class Members------------------------------
	private int id;
	private Date startDateTime;
	private int duration;
	private int extraTime;
	private E_Levels level;
	private Team homeTeam;
	private int homeTeamScore;
	private Team awayTeam;
	private int awayTeamScore;
	private Map<Customer, Boolean> crowd;

	// -------------------------------Constructors------------------------------
	public Match(int id, Date startDateTime, int extraTime, Team homeTeam,
			int homeTeamScore, Team awayTeam, int awayTeamScore) {
		this.id = id;
		this.startDateTime = startDateTime;
		this.extraTime = extraTime;
		this.duration = Constants.DEFAULT_MATCH_DURATION + extraTime;
		this.homeTeam = homeTeam;
		this.homeTeamScore = homeTeamScore;
		this.awayTeam = awayTeam;
		this.awayTeamScore = awayTeamScore;
		this.crowd = new TreeMap<>();
		calculateMatchLevel();
	}

	public Match(int id) {
		this.id = id;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(int extraTime) {
		this.extraTime = extraTime;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	public void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	public Map<Customer, Boolean> getCrowd() {
		return crowd;
	}

	public void setCrowd(Map<Customer, Boolean> crowd) {
		this.crowd = crowd;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method calculates the end date/time of the match
	 * @return Date object of the end date
	 */
	@SuppressWarnings("deprecation")
	public Date getFinishDateTime() {
		Date finish = (Date) startDateTime.clone();
		finish.setMinutes(finish.getMinutes() + duration);
		return finish;
	}

	/**
	 * This method calculates the level of the match,
	 * the level will be the average of teams levels 
	 */
	private void calculateMatchLevel() {
		int avg = (homeTeam.getLevel().getLevel() + awayTeam.getLevel().getLevel()) / 2;
		setLevel(E_Levels.returnLevel(avg));
	}
	
	/**
	 * this method adds a fan to this match's crowd if there is enough space on the stadium
	 * 
	 * @param fan
	 * @return true if the fan was added successfully, false otherwise
	 * @throws ObjectExistsException 
	 */
	public boolean addFan(Customer fan) throws ObjectExistsException {
		if (fan == null) {// if fan is null, return false
			return false;
		}
		if (fan.getFavoriteTeam() != null && fan.getFavoriteTeam().equals(homeTeam)) {// if fan is a fan of the home
																						// team, add him with true value
																						// and retur ntrue;
			crowd.put(fan, true);
			return true;
		}
		for (Customer c : crowd.keySet()) {// if fan exists, return false
			if (c != null) {
				if (fan.getId()==c.getId()) {
					throw new ObjectExistsException("customer is already in match");
				}
			}
		}
		crowd.put(fan, false);// add fan with false boolean, return true;
		return true;
	}

	/**
	 * This method removes a fan from the crowd array 
	 * only if the fan exists there
	 * 
	 * @param fan
	 * @return true if the fan was deleted, false otherwise
	 */
	public boolean removeFan(Customer fan) {
		if (fan != null && crowd.containsKey(fan)) 
			return crowd.remove(fan);
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
		Match other = (Match) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateFormat dft = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
		return "Match | id: " + id + ", start date: " + dft.format(startDateTime) + ", duration: " + duration + ", extra time: "
				+ extraTime + ", level: " + level + ", teams: " + homeTeam.getName() + " vs " + awayTeam.getName() 
				+ ", score: " + homeTeamScore + " - " + awayTeamScore;
	}

}
