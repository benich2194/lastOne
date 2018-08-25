package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import Exceptions.ObjectExistsException;

/**
 * Class Receptionist ~ represent a single Receptionist of the company,
 * inheritor of Employee
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Receptionist extends Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8898478405999122128L;
	// -------------------------------Class Members------------------------------
	private Stadium workingStadium;
	private HashSet<Subscription> subscriptions;

	// -------------------------------Constructors------------------------------
	public Receptionist(int id,String password, String firstName, String lastName, Date birthdate, Date startWorkingDate, 
			Address address) {
		super(id,password, firstName, lastName, birthdate, startWorkingDate, address);
		this.subscriptions = new HashSet<>();
	}

	public Receptionist(int empNum) {
		super(empNum);
	}

	// -------------------------------Getters And Setters------------------------------
	public void setWorkingStadium(Stadium workingStadium) {
		this.workingStadium = workingStadium;
	}

	public Stadium getWorkingStadium() {
		return workingStadium;
	}
	
	public void setSubscriptions(HashSet<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public HashSet<Subscription> getSubscriptions() {
		return subscriptions;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a subscription to the subscriptions array 
	 * only if it doesn't already exist in receptionist's subscriptions array
	 * 
	 * @param subscription
	 * @return true if the subscription was added successfully, false otherwise
	 * @throws ObjectExistsException 
	 */
	public boolean addSubscription(Subscription subscription) throws ObjectExistsException {
		if (subscription == null) {// if subscription is null, return false
			return false;
		}
		if (subscriptions.contains(subscription)) {// if subscription already exists, return false
			throw new ObjectExistsException("Receptionist already sold subscription with this id");
		}
		if (!subscriptions.add(subscription)) {// if cannot add subscription, return false
			return false;
		}
		return true;// return true
	}

	/**
	 * This method removes a subscription from the subscriptions array
	 * 
	 * @param subscription
	 * @return true if the subscription was removed successfully, false otherwise
	 */
	public boolean removeSubscription(Subscription subscription) {
		if (subscription == null) {// if subscription is null, return false
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

}
