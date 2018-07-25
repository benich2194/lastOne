package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import utils.E_Periods;

/**
 * Class Subscription ~ represent a single Subscription in the company Each
 * Subscription belongs to a specific Customer
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Subscription {
	// -------------------------------Class Members------------------------------
	private int id;
	private Customer customer;
	private Receptionist receptionist;
	private E_Periods period;
	private Date startDate;
	private HashSet<Match> matches;

	// -------------------------------Constructors------------------------------
	public Subscription(int id, Customer customer, Receptionist receptionist, E_Periods period, Date startDate) {
		this.id = id;
		this.customer = customer;
		this.receptionist = receptionist;
		this.period = period;
		this.startDate = startDate;
		this.matches = new HashSet<>();
	}

	public Subscription(int id) {
		this.id = id;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashSet<Match> getMatches() {
		return matches;
	}

	public void setMatches(HashSet<Match> matches) {
		this.matches = matches;
	}

	public Receptionist getReceptionist() {
		return receptionist;
	}
	
	public void setReceptionist(Receptionist receptionist) {
		this.receptionist = receptionist;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer resCustomer) {
		this.customer = resCustomer;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public E_Periods getPeriod() {
		return period;
	}

	public void setPeriod(E_Periods period) {
		this.period = period;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method calculate the last date the subscription is valid for
	 * 
	 * @see utils.E_Periods
	 * @return lastDate of the subscription if no nulls, null otherwise
	 */
	@SuppressWarnings("deprecation")
	public Date getLastDay() {
		if (this.getStartDate() != null && this.getPeriod() != null) {
			Date lastDay = (Date) this.getStartDate().clone();
			lastDay.setMonth(lastDay.getMonth() + this.getPeriod().getNumber());
			return lastDay;
		}
		return null;
	}

	/**
	 * This method adds a new match to the matches array
	 * only iF the match doesn't already exists in the Subscription's matches array, match is valid 
	 * and the time doesn't overlap with any other match in the array
	 * 
	 * @param match
	 * @return true if the match was added successfully, false otherwise
	 */
	public boolean addMatch(Match match) {
		if (match != null && !matches.contains(match)) 
			return matches.add(match);
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
		Subscription other = (Subscription) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateFormat dft = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
		return "Subscription | id: " + id + ", customer: " + customer.getFirstName() + " " + customer.getLastName() 
				+ ", receptionist: " + receptionist.getFirstName() + " " + receptionist.getLastName() 
				+ ", period: " + period + ", start date: " + dft.format(startDate);
	}

}
