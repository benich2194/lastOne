package utils;

/**
* Class Constants ~ all constants necessary for the system
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
*/
public final class Constants {
	
	/**
	 * The default regular match duration 
	 */
	public static final int DEFAULT_MATCH_DURATION = 90;
	
	
	/**
	 * The minimum of an array capacity for subscriptions
	 */
	public static final int MIN_SUB_ORDERS = 5;
	
	/**
	 * The minimum of an array capacity for matches per subscription/team
	 * or every array of matches
	 */
	public static final int MIN_NUM_OF_MATCHES = 10;

	/**
	 * The maximum of an array capacity for coaches in a team
	 */
	public static final int MAX_COACHES_FOR_TEAM = 2;

	/**
	 * The maximum of an array capacity for players in a team
	 */
	//Used to be 7, changed to 18 in order to test the Bonus
	public static final int MAX_PLAYERS_FOR_TEAM = 18;

	/**
	 * The minimum of an array capacity for teams of coach
	 */
	public static final int MIN_TEAMS_FOR_COACH = 5;

	/**
	 * The maximum of an array capacity for Receptionists in a stadium
	 */
	public static final int MAX_RESEPTIONISTS_FOR_STADIUM = 4;

	/**
	 * The maximum of an array capacity for Teams in a stadium
	 */
	public static final int MAX_TEAMS_FOR_STADIUM = 2;
	
	/**
	 * The number of first players in a team
	 */
	//Used to be 4, changed to 11 Inorder to test the Bonus
	public static final int NUM_OF_FIRST_TEAM_PLAYERS = 11;

	/**
	 * The standard size of an ID number
	 */
	public static final int ID_NUMBER_SIZE = 9;

	/**
	 * The minimum size of fans array per match
	 */
	public static final int MIN_NUM_OF_FANS = 5;
	
}// ~ END OF Class Constants