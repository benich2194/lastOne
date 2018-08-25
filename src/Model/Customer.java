package Model;

import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Exceptions.NoValidSubscriptionException;
import Exceptions.ObjectExistsException;
import Exceptions.ObjectNotExistException;
import utils.Constants;
import utils.E_Levels;

/**
 * Class Customer ~ represent a single customer/fan of the league
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Customer implements Comparable<Customer>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -468143693890518202L;
	// -------------------------------Class Members------------------------------
	private String id;
	private String firstName;
	private String lastName;
	private Date birthdate; // Calendar can also be used here
	private E_Levels level;
	private URL email;
	private Address address;
	private Team favoriteTeam;
	private Set<Subscription> subscriptions;
	private String password;


	// -------------------------------Constructors------------------------------
	public Customer(String id,String password, String firstName, String lastName, Date birthdate, E_Levels level,
			URL email, Address address, Team favoriteTeam, Subscription subscription) throws ObjectExistsException {
		this.id = checkId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.level = level;
		this.email = email;
		this.address = address;
		this.favoriteTeam = favoriteTeam;
		this.subscriptions = new HashSet<>();
		this.password=password;
		addSubscription(subscription);
	}

	public Customer(String id,String password, String firstName, String lastName, Date birthdate, E_Levels level,
			URL email, Address address, Team favoriteTeam) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.level = level;
		this.email = email;
		this.address = address;
		this.favoriteTeam = favoriteTeam;
		this.password=password;
		this.subscriptions = new HashSet<>();
	}

	public Customer(String id) {
		this.id = id;
	}

	// -------------------------------Getters And Setters------------------------------
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public URL getEmail() {
		return email;
	}

	public void setEmail(URL email) {
		this.email = email;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(HashSet<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Team getFavoriteTeam() {
		return favoriteTeam;
	}

	public void setFavoriteTeam(Team favoriteTeam) {
		this.favoriteTeam = favoriteTeam;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a new Subscription to the subscriptions array 
	 * only if the given subscription doesn't already exist in the Customer's subscriptions array.
	 * 
	 * @param subscription
	 * @return true if this subscription was successfully added, false otherwise
	 * @throws ObjectExistsException 
	 */
	public boolean addSubscription(Subscription subscription) throws ObjectExistsException {
		if (subscription == null) {// if subscription is null, return false
			return false;
		}
		if (subscriptions.contains(subscription)) {// if subscription already exists, return false
			throw new ObjectExistsException("Customer already has subscription with this id");
		}
		if (!subscriptions.add(subscription)) {// if cannot add subscription, return false
			return false;
		}
		return true;// return true
	}

	/**
	 * This method removes an existing subscription from the subscriptions array
	 * only if the subscription exists
	 * 
	 * @param subscription
	 * @return true if this subscription was removed successfully, false otherwise
	 */
	public boolean removeSubscription(Subscription subscription) {
		if (subscription == null) {// if subscription is null, return false;
			return false;
		}
		if (!subscriptions.contains(subscription)) {// if subscription does not exist, return false
			return false;
		}
		if (!subscriptions.remove(subscription)) {// if cannot remove subscription, return false
			return false;
		}
		return true;// return true
	}

	/**
	 * This method counts the number of the valid subscriptions that belongs to the customer.
	 * a valid subscription is a subscription that its last date is after today.
	 * 
	 * @return customerSubs number of subscriptions
	 */
	public int getNumOfCustomerSubscriptions() {
		int customerSubs = 0;
		for (Subscription s : subscriptions) 
			if (s.getLastDay().after(new Date())) 
				customerSubs++;
		return customerSubs;
	}

	/**
	 * This method adds a given match to the first valid subscription of the customer
	 * a subscription is defined valid iff:
	 * 		1. its last date wasn't expired
	 * 		2. the subscription's stadium is the home team's stadium
	 * *subscription's stadium is the working stadium of the receptionist that sold the subscription
	 * IMPORTANT: the match will be added only if its time doesn't ovelap
	 * 			  with any other match in the valid subscription
	 * 
	 * @param match
	 * @return true if the match was added successfully, false otherwise
	 * @throws NoValidSubscriptionException 
	 * @throws ObjectNotExistException 
	 */
	public boolean addMatch(Match match) throws NoValidSubscriptionException, ObjectNotExistException {
		int flag=0;
		if (match == null) {// if match is null, return false
			return false;
		}
			for (Subscription s : subscriptions) {
				if (s != null) {
					if (s.getLastDay().after(match.getFinishDateTime())
							&& match.getHomeTeam().getStadium().getReceptionists().contains(s.getReceptionist())) {// if
																													// subscription
																													// is
																													// valid,
																													// and
																													// the
																													// match
																													// stadium
																													// receptionist
																													// sold
																													// it,
																													// add
																													// match
																													// to
																													// subscription
						if(!match.getHomeTeam().getStadium().getReceptionists().contains(s.getReceptionist())) {
							flag=2;
						}
						if (s.addMatch(match)) {// if succesfully added, return true
							flag=1;
							return true;
						}

					}

				}
			}
			if(flag==0) {
				throw new NoValidSubscriptionException("no valid subscription for customer to add match");
			}
			if(flag==2) {
				throw new ObjectNotExistException("No receptionist from match stadium that sold subscription for the customer");
			}
		return false;
	}
	
	/**
	 * This method removes a given match from the relevant subscription
	 * 
	 * @param match
	 * @return true if the match was removed successfully, false otherwise
	 */
	public boolean removeMatch(Match match) {
		for (Subscription s : subscriptions) 
			for (Match m : s.getMatches())
				if (m.equals(match)) 
					return s.removeMatch(m);
		return false;
	}
	
	/**
	 * This method gets a string and checks if the string is a valid ID number
	 * 
	 * @see Constants.ID_NUMBER_SIZE
	 * @param id
	 * @return the id if it's a valid id, "0" otherwise
	 */
	public static String checkId(String id) {
		if (id.length() == Constants.ID_NUMBER_SIZE) {
			for (int i = 0; i < id.length(); i++) {
				if (!Character.isDigit(id.charAt(i)))
					return "0";
			}
			return id;
		}
		return "0";
	}
	
	@Override
	public int compareTo(Customer o) {
		Integer lvl1 = this.getLevel().getLevel();
		Integer lvl2 = o.getLevel().getLevel();
		return lvl1.compareTo(lvl2);
	}

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateFormat dft = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
		return "Customer | id: " + id + ", name: " + firstName + " " + lastName + ", birthdate: " + dft.format(birthdate)
				+ ", level: " + level + ", email: " + email + ", favorite team: " + favoriteTeam.getName() + ", address: " + address;
	}

}
