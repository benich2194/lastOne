package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.E_Trophy;

/**
 * Class Trophy ~ represent a single Trophy, a trophy may belong to a stadium, team, coach or player.
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 * @param <T>
 */
public class Trophy<T> implements Comparable<Trophy<T>>{
	// -------------------------------Class Members------------------------------
	private E_Trophy trophy;
	private T owner;
	private Date trophyWinningDate;

	// -------------------------------Constructors------------------------------
	public Trophy(E_Trophy trophy, T owner, Date trophyWinningDate) {
		this.trophy = trophy;
		this.owner = owner;
		this.trophyWinningDate = trophyWinningDate;
	}
	
	// -------------------------------Getters And Setters------------------------------
	public T getOwner() {
		return owner;
	}
	
	public E_Trophy getTrophy() {
		return trophy;
	}
	
	public void setTrophy(E_Trophy trophy) {
		this.trophy = trophy;
	}
	
	public void setOwner(T owner) {
		this.owner = owner;
	}
	
	public Date getTrophyWinningDate() {
		return trophyWinningDate;
	}
	
	public void setTrophyWinningDate(Date trophyWinningDate) {
		this.trophyWinningDate = trophyWinningDate;
	}
	
	// -------------------------------More Methods------------------------------
	public int compareTo(Trophy<T> o) {
		return this.getTrophyWinningDate().compareTo(o.getTrophyWinningDate());
	}
	
	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((trophy == null) ? 0 : trophy.hashCode());
		result = prime * result + ((trophyWinningDate == null) ? 0 : trophyWinningDate.hashCode());
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
		Trophy<?> other = (Trophy<?>) obj;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (trophy != other.trophy)
			return false;
		if (trophyWinningDate == null) {
			if (other.trophyWinningDate != null)
				return false;
		} else if (!trophyWinningDate.equals(other.trophyWinningDate))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		DateFormat dft = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
		return "Trophy | trophy: " + trophy + ", owner: " + owner + ", winning date: " + dft.format(trophyWinningDate);
	}

}
