package utils;

public enum NameToWindow {

	ADD_TEAM("add/addTeam"),
	ADD_CUSTOMER("add/addCustomer"),
	ADD_MATCH("add/addMatch"),
	ADD_PLAYER("add/addPlayer"),
	ADD_RECEPTIONIST("add/addReceptionist"),
	ADD_STADIUM("add/addStadium"),
	ADD_TROPHY("add/addTrophy"),
	ADD_COACH("add/addCoach");
	
	
	NameToWindow(String s)
	{
		this.myPath = s;
	}
	
	private String myPath;
	
	@Override
	public String toString() {
		return myPath;
	}
}
