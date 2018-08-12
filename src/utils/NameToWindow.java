package utils;

//**ENUM NAMETOWINDOW was created in order to neatly sort and categorize pages and their paths.

public enum NameToWindow {

	/*------------ADD-------------------------------------------------*/
	ADD_TEAM("add/addTeam"),
	ADD_CUSTOMER("add/addCustomer"),
	ADD_MATCH("add/addMatch"),
	ADD_PLAYER("add/addPlayer"),
	ADD_RECEPTIONIST("add/addReceptionist"),
	ADD_STADIUM("add/addStadium"),
	ADD_TROPHY("add/addTrophy"),
	ADD_COACH("add/addCoach"),

	/*------------MODFIY-------------------------------------------------*/
	MODIFY_TEAM("modify/modifyTeam"),
	MODIFY_CUSTOMER("modify/modifyCustomer"),
	MODIFY_MATCH("modify/modifyMatch"),
	MODIFY_PLAYER("modify/modifyPlayer"),
	MODIFY_RECEPTIONIST("modify/modifyReceptionist"),
	MODIFY_STADIUM("modify/modifyStadium"),
	MODIFY_TROPHY("modify/modifyTrophy"),
	MODIFY_COACH("modify/modifyCoach"),
	MODIFY_SUB("modify/modifySub"),
	
	/*------------REMOVE-------------------------------------------------*/
	REMOVE_TEAM("remove/removeTeam"),
	REMOVE_CUSTOMER("remove/removeCustomer"),
	REMOVE_MATCH("remove/removeMatch"),
	REMOVE_PLAYER("remove/removePlayer"),
	REMOVE_RECEPTIONIST("remove/removeReceptionist"),
	REMOVE_STADIUM("remove/removeStadium"),
	REMOVE_TROPHY("remove/removeTrophy"),
	REMOVE_SUB("remove/removeSub"),
	REMOVE_COACH("remove/removeCoach"),
	
	/*------------MENUS-------------------------------------------------*/
	MENU_TEAM("menus/teamMenu"),
	MENU_CUSTOMER("menus/customerMenuAdmin"),
	MENU_MATCH("menus/matchMenu"),
	MENU_PLAYER("menus/playerMenu"),
	MENU_RECEPTIONIST("menus/receptionistMenuAdmin"),
	MENU_STADIUM("menus/stadiumMenu"),
	MENU_TROPHY("menus/trophyMenu"),
	MENU_SUB("menus/subscriptionMenu"),
	MENU_COACH("menus/coachModif"),
	MENU_CONNECTION("menus/connectionMenu"),
	QUERIES("menus/queries"),
	
	/*------------LOGIN SCREENS-------------------------------------------*/
	
	//NOT DONE YET
	ADMIN_LOGIN("menus/teamMenu"),
	COACH_LOGIN("menus/customerMenuAdmin"),
	CUSTOMER_LOGIN("menus/matchMenu"),
	RECEPTIONIST_LOGIN("menus/playerMenu"),
	
	/*------------QUERIES-------------------------------------------------*/
	QUERY_GETMAC("queries/getMAC"),
	QUERY_GETALLSPM("queries/getALLSPM"),
	QUERY_GETMPP("queries/getMPP"),
	QUERY_MFT("queries/getMFT"),
	QUERY_GETSPM("queries/getSPM");	
	
	
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