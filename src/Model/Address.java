package Model;

import java.io.Serializable;
import java.util.Arrays;

import utils.E_Cities;

/**
 * Class Address ~ represent a single Address
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6498902946694776310L;
	// -------------------------------Class Members------------------------------
	private E_Cities city;
	private String country;
	private int houseNumber;
	private String[] phoneNumber;
	private String street;

	// -------------------------------Constructors-----------------------------
	public Address(E_Cities city, String street, int houseNumber, String[] phoneNumber) {
		super();
		this.city = city;
		this.country = city.getCountry();
		this.houseNumber = houseNumber;
		this.phoneNumber = new String[1];
		this.phoneNumber = phoneNumber;
		this.street = street;
	}

	// -------------------------------Getters And Setters------------------------------
	public E_Cities getCity() {
		return city;
	}

	public void setCity(E_Cities city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String[] getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String[] phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	// -------------------------------More Methods------------------------------

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + houseNumber;
		result = prime * result + Arrays.hashCode(phoneNumber);
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (city != other.city)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (houseNumber != other.houseNumber)
			return false;
		if (!Arrays.equals(phoneNumber, other.phoneNumber))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return street + " " + houseNumber + ", " + city + ", " + country + ", phones: " + Arrays.toString(phoneNumber).replace("[", "{").replace("]", "}");
	}
} // ~ END OF Class Address
