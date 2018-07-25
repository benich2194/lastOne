package Controller;

import Model.*;
import utils.*;

import java.net.URL;
import java.util.*;

/**
 * This SysData object ~ represents the class system
 * 
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
@SuppressWarnings("rawtypes")
public class SysData {
	// -------------------------------Class Members------------------------------
	private static SysData instance;
	private HashMap<Integer, Coach> coaches;
	private HashMap<Integer, Receptionist> receptionists;
	private HashMap<Integer, Player> players;
	private HashMap<Integer, Stadium> stadiums;
	private HashMap<Integer, Team> teams;
	private HashMap<String, Customer> customers;
	private HashMap<Integer, Match> matches;
	private HashSet<Trophy> trophies;
        
        private HashMap<String,String> admins;

	// -------------------------------Constructors------------------------------
	private SysData() {
		coaches = new HashMap<>();
		receptionists = new HashMap<>();
		players = new HashMap<>();
		stadiums = new HashMap<>();
		teams = new HashMap<>();
		customers = new HashMap<>();
		matches = new HashMap<>();
		trophies = new HashSet<>();
                
                admins=new HashMap<>();
	}

	// -----------------------------------------Getters--------------------------------------
	public HashMap<Integer, Coach> getCoachs() {
		return coaches;
	}

	public HashMap<Integer, Receptionist> getReceptionists() {
		return receptionists;
	}

	public HashMap<Integer, Player> getPlayers() {
		return players;
	}
	
	public HashMap<Integer, Team> getTeams() {
		return teams;
	}

	public HashMap<Integer, Stadium> getStadiums() {
		return stadiums;
	}

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public HashMap<Integer, Match> getMatchs() {
		return matches;
	}
        
        public HashMap<String, String> getAdmins() {
		return admins;
	}

	public static SysData getInstance() {
		if (instance == null) 
			instance = new SysData();
		return instance;
	}

	// -------------------------------Add && Remove Methods------------------------------
	/**
	 * This method adds a new stadium to the JavaLeague
	 * only if all the parameters are valid and the stadium doesn't already exist in the system
	 * 
	 * @param id
	 * @param name
	 * @param capacity
	 * @param city
	 * @param street
	 * @param houseNumber
	 * @param phoneNumber
	 * @return true if the stadium was added successfully, false otherwise
	 */
	public boolean addStadium(int id, String name, int capacity, E_Cities city, String street, int houseNumber, String[] phoneNumber) {
		if (id > 0 && name != null && capacity > 0 && city != null && street != null && houseNumber > 0 && phoneNumber != null) {
			if (!stadiums.containsKey(id)) {
				Address address = new Address(city, street, houseNumber, phoneNumber);
				if (stadiums.put(id, new Stadium(id, name, address, capacity)) ==null) {
					return true;
				}
			}
		}
		return false;
	} // ~ END OF addStadium
        
        	/**
	 * This method adds a new Admin to the admins hashmap
	 *
         */
        public void addAdmin(String email, String password){
            admins.put(email, password);
        }
        
	/**
	 * This method adds a new team to the JavaLeague
	 * only if all the parameters are valid and the team doesn't already exist in the system
	 * IMPORTENT: In order to add a new team, 
	 * 			  the team must be added to it's stadium first.
	 * 			  Don't forget to roll-back :)
	 * 
	 * @param id
	 * @param name
	 * @param maxNumOfPlayers
	 * @param minNumOfPlayers
	 * @param value
	 * @param level
	 * @param stadiumId
	 * @return true if the team was added successfully, false otherwise
	 */
	public boolean addTeam(int id, String name, int value, E_Levels level, int stadiumId) {
		if (id > 0 && name != null && value > 0 && level != null && stadiumId > 0) {
			if (!teams.containsKey(id) && stadiums.containsKey(stadiumId)) {
				Stadium stadium = stadiums.get(stadiumId);
				Team team = new Team(id, name, value, level, stadium);
				if (stadium.addTeam(team)) {
					if (teams.put(id, team) == null) 
						return true;
					stadium.removeTeam(team);
				}
			}
		}
		return false;
	} // ~ END OF addTeam
	
	/**
	 * This method adds a new coach to the JavaLeague
	 * only if all the parameters are valid and the coach doesn't already exist in the system
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param startWorkingDate
	 * @param level
	 * @param teamId
	 * @param address
	 * @return true if the coach was added successfully, false otherwise
	 */
	public boolean addCoach(int id, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			E_Levels level, Address address) {
		Date today = new Date();
		if (id > 0 && firstName != null && lastName != null && birthdate != null && startWorkingDate != null &&
				level != null && address != null && birthdate.before(today) && startWorkingDate.before(today) &&
				birthdate.before(startWorkingDate)) {
			if (!coaches.containsKey(id)) {
				if (coaches.put(id, new Coach(id, firstName, lastName, birthdate, startWorkingDate, address, level)) == null) {
					return true;
				}
			}
		}
		return false;
	} // ~ END OF addCoach
	
	/**
	 * This method adds a new player to the JavaLeague
	 * only if all the parameters are valid and the player doesn't already exist in the system
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param startWorkingDate
	 * @param level
	 * @param value
	 * @param isRightLegKicker
	 * @param position
	 * @param address
	 * @return true if the player was added successfully, false otherwise
	 */
	public boolean addPlayer(int id, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			E_Levels level, long value, Boolean isRightLegKicker, E_Position position, Address address) {
		Date today = new Date();
		if (id > 0 && firstName != null && lastName != null && birthdate != null && startWorkingDate != null &&
				level != null && address != null && value > 0 && birthdate.before(today) && startWorkingDate.before(today) &&
				birthdate.before(startWorkingDate)) {
			if (!players.containsKey(id)) {
				if (players.put(id, new Player(id, firstName, lastName, birthdate, startWorkingDate, address, level, value, isRightLegKicker, position)) == null) {
					return true;
				}
			}
		}
		return false;
	} // ~ END OF addPlayer
	
	/**
	 * This method adds a new receptionist to the JavaLeague
	 * only if all the parameters are valid and the receptionist doesn't already exist in the system
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param startWorkingDate
	 * @param stadiumId
	 * @param address
	 * @return true if the receptionist was added successfully, false otherwise
	 */
	public boolean addReceptionist(int id, String firstName, String lastName, Date birthdate, Date startWorkingDate, Address address) {
		if (id > 0 && !firstName.equals("") && !lastName.equals("") && birthdate != null && startWorkingDate != null && address != null) {
			if (!receptionists.containsKey(id)) {
				if (receptionists.put(id, new Receptionist(id, firstName, lastName, birthdate, startWorkingDate, address)) == null) {
					return true;
				}
			}
		}
		return false;
	} // ~ END OF addReceptionist

	/**
	 * This method adds a new customer to the JavaLeague
	 * only if all the parameters are valid and the customer doesn't already exist in the system
	 * IMPORTENT: Every customer has a favorite team, 
	 * 			  if the team doesn't exist in the system the customer cannot be added :(
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param level
	 * @param email
	 * @param favoriteTeamId
	 * @param address
	 * @return true if the customer was added successfully, false otherwise
	 */
	public boolean addCustomer(String id, String firstName, String lastName, Date birthDate, E_Levels level, URL email, int favoriteTeamId, Address address) {
		if (id != null && firstName != null && lastName != null && birthDate != null && favoriteTeamId > 0 &&
			level != null && email != null && address != null) {
			if (!Customer.checkId(id).equals("0")) {
				if (!customers.containsKey(id) && teams.containsKey(favoriteTeamId)) {
					Team favoriteTeam = teams.get(favoriteTeamId);
					if (customers.put(id, new Customer(id, firstName, lastName, birthDate, level, email, address, favoriteTeam)) == null) {
						return true;
					}
				}
			}
		}
		return false;
	}// ~ END OF addCustomer

	/**
	 * This method adds an existing coach to an existing team of the JavaLeague
	 * only if all the parameters are valid and the both coach & team already exist in the system
	 * IMPORTENT: If the given coach already belongs to another team, 
	 * 			  transfer the coach to the given team. 
	 * 
	 * @param coachId
	 * @param teamId
	 * @return true if the coach was added successfully to team, false otherwise
	 */
	public boolean addCoachToTeam(int coachId, int teamId) {
		if (coachId > 0 && teamId > 0) {
			if (coaches.containsKey(coachId) && teams.containsKey(teamId)) {
				Coach coach = coaches.get(coachId);
				Team team = teams.get(teamId);

				if (coach.getCurrentTeam() != null) {
					if (coach.getCurrentTeam().equals(team))
						return false;
					
					if (team.registerCoach(coach)) {
						return coach.transferTo(team);
					}
				}
				
				if (team.registerCoach(coach)) {
					coach.setCurrentTeam(team);
					return true;
				}
			}
		}
		return false;
	}// ~ END OF addCoachToTeam

	/**
	 * This method adds an existing player to an existing team of the JavaLeague
	 * only if all the parameters are valid and the both player & team already exist in the system
	 * IMPORTENT: If the given player already belongs to another team, 
	 * 			  transfer the player to the given team. 
	 * 
	 * @param playerId
	 * @param teamId
	 * @return true if the player was added successfully to team, false otherwise
	 */
	public boolean addPlayerToTeam(int playerId, int teamId) {
		if (playerId > 0 && teamId > 0) {
			if (players.containsKey(playerId) && teams.containsKey(teamId)) {
				Player player = players.get(playerId);
				Team team = teams.get(teamId);
				
				if (player.getCurrentTeam() != null) {
					if (player.getCurrentTeam().equals(team))
						return false;
					player.getCurrentTeam().removePlayer(player);
				}
				
				if (team.addPlayer(player)) {
					player.setCurrentTeam(team);
					return true;
				}
			}
		}
		return false;
	}// ~ END OF addCoachToTeam

	/**
	 * This method adds an existing player to an existing team first players
	 * only if all the parameters are valid and the both player & team already exist in the system
	 * 
	 * @param playerId
	 * @param teamId
	 * @return true if the player was added successfully to team fir players, false otherwise
	 */
	public boolean addPlayerToTeamFirstPlayers(int playerId, int teamId) {
		if (playerId > 0 && teamId > 0) {
			if (players.containsKey(playerId) && teams.containsKey(teamId)) {
				Player player = players.get(playerId);
				Team team = teams.get(teamId);
				if (player.getCurrentTeam() != null) {
					if (!player.getCurrentTeam().equals(team)) {
						return false;
					}
					return team.addPlayerToFirstTeam(player);
				}
			}
		}
		return false;
	}// ~ END OF addCoachToTeam
	
	/**
	 * This method adds an existing receptionist to an existing stadium of the JavaLeague
	 * only if all the parameters are valid and the both receptionis & stadium already exist in the system
	 * 
	 * @param receptionistId
	 * @param stadiumId
	 * @return true if the receptionist was added successfully to stadium, false otherwise
	 */
	public boolean addReceptionistToStadium(int receptionistId, int stadiumId) {
		if (receptionistId > 0 && stadiumId > 0) {
			if (receptionists.containsKey(receptionistId) && stadiums.containsKey(stadiumId)) {
				Receptionist receptionist = receptionists.get(receptionistId);
				Stadium stadium = stadiums.get(stadiumId);
				Stadium s = receptionist.getWorkingStadium();
				
				if (s != null) {
					if (s.equals(stadium))
						return false;
					s.removeReceptionist(receptionist);
				}
				
				if (stadium.addReceptionist(receptionist)) {
					receptionist.setWorkingStadium(stadium);
					return true;
				}
			}
		}
		return false;
	}// ~ END OF addReceptionistToStadium

	/**
	 * This method adds a new subscription to an existing customer of the JavaLeague
	 * only if all the parameters are valid and the customer already exist in the system
	 * IMPORTENT: Every subscription was sold by a receptionist, 
	 * 			  hence it's very important that the receprionist belongs to a stadium.
	 * ALSO IMPORTENT: Subscription must be added to all the relevant arrays,
	 * 				   Don't forget to roll-back :)
	 * 
	 * @param subscriptionId
	 * @param customerId
	 * @param receptionistId
	 * @param period
	 * @param startDate
	 * @return true if the suscription was added successfully to customer, false otherwise
	 */
	public boolean addSubscriptionToCustomer(int subscriptionId, String customerId, int receptionistId, E_Periods period, Date startDate) {
		if (subscriptionId > 0 && receptionistId > 0 && !Customer.checkId(customerId).equals("0")) {
			if (customers.containsKey(customerId) && receptionists.containsKey(receptionistId)) {
				Customer customer = customers.get(customerId);
				Receptionist receptionist = receptionists.get(receptionistId);
				
				if (receptionist.getWorkingStadium() == null)
					return false;
				
				Subscription subscription = new Subscription(subscriptionId, customer, receptionist, period, startDate);
				if (customer.addSubscription(subscription)) {
					if (receptionist.addSubscription(subscription))
						return true;
					customer.removeSubscription(subscription);
				}
			}
		}
		return false;
	} // ~ END OF addSubscriptionToCustomer

	/**
	 * This method adds a new match to the JavaLeague
	 * only if all the parameters are valid and both teams already exist in the system
	 * VERY IMPORTENT: Match must be added to all the relevant arrays,
	 * 				   Don't forget to roll-back :)
	 * 
	 * @param id
	 * @param dateTime
	 * @param extraTime
	 * @param homeTeamId
	 * @param awayTeamId
	 * @param homeTeamScore
	 * @param awayTeamScore
	 * @return true if the match was added successfully, false otherwise
	 */
	public boolean addMatch(int id, Date dateTime, int extraTime, int homeTeamId, int awayTeamId, 
			int homeTeamScore, int awayTeamScore) {
		if (id > 0 && dateTime != null && extraTime >= 0 && homeTeamId > 0 && awayTeamId > 0 && 
				homeTeamScore >= 0 && awayTeamScore >= 0) {
			if (homeTeamId == awayTeamId)
				return false;
			if (teams.containsKey(homeTeamId) && teams.containsKey(awayTeamId) && !matches.containsKey(id)) {
				Team homeTeam = teams.get(homeTeamId);
				Team awayTeam = teams.get(awayTeamId);
				Stadium stadium = homeTeam.getStadium();
				Match match = new Match(id, dateTime, extraTime, homeTeam, homeTeamScore, awayTeam, awayTeamScore);
				if (homeTeam.addMatch(match)) {
					if (awayTeam.addMatch(match)) {
						if (stadium.addMatch(match)) {
							if (matches.put(id, match) == null) {
								return true;
							}
						}
						awayTeam.removeMatch(match);
					}
					homeTeam.removeMatch(match);
				}
			}
		}
		return false;
	}

	/**
	 * This method adds an existing customer to an existing match of the JavaLeague
	 * only if all the parameters are valid and both customer & match already exist in the system
	 * VERY IMPORTENT: Customer must be added to all the relevant arrays,
	 * 				   Don't forget to roll-back :)
	 * 
	 * @param customerId
	 * @param matchId
	 * @return true if the customer was added successfully to match, false otherwise
	 */
	public boolean addCustomerToMatch(String customerId, int matchId) {
		if (!Customer.checkId(customerId) .equals("0") && matchId > 0) {
			if (customers.containsKey(customerId) && matches.containsKey(matchId)) {
				Customer customer = customers.get(customerId);
				Match match = matches.get(matchId);
				if (customer.addMatch(match)) {
					if (match.addFan(customer)) {
						return true;
					}
					customer.removeMatch(match);
				}
			}
		}
		return false;
	}

	/**
	 * This method removes an existing subscription from its customer
	 * only if all the parameters are valid and the subscription already exist in the system
	 * 
	 * @param subscriptionId
	 * @return
	 */
	public boolean removeSubscription(int subscriptionId) {
		if (subscriptionId > 0) {
			Subscription subscription = new Subscription(subscriptionId);
			for (Customer c : customers.values()) {
				for (Subscription s : c.getSubscriptions()) 
					if (s.equals(subscription)) 
						subscription = s;
			
				if (subscription == null) 
					return false;
				
				if (c.removeSubscription(subscription)) 
					return true;
			}
		}
		return false;
	}// ~ END OF removeSubscription
//	
	/**
	 * This method adds a Trophy to the system
	 * only if all the parameters are valid and the Trophy does not already exist in the system
	 * @param <T>
	 * 
	 * @param subscriptionId
	 * @return
	 */
	public <T> boolean addTrophy(E_Trophy trophy, T owner, Date trophyWinningDate) {
		if (trophy != null && owner != null && trophyWinningDate != null) {
			if (owner instanceof Employee && trophyWinningDate.before(((Employee)owner).getStartWorkingDate())) 
				return false;
			Trophy<T> trp = new Trophy<T>(trophy, owner, trophyWinningDate);
			return trophies.add(trp);
		}
		return false;
	}// ~ END OF removeSubscription
	// -------------------------------Queries------------------------------
	// ===================================================
	// HW_2_Queries
	// ===================================================
	
	/**
	 * This query finds the "Super Play Maker" player, 
	 * a "Super Play Maker" player is the player that has the largest value,
	 * is a right leg kicker, is in MIDFIELDER position and is in a team's first players. 
	 * if there are more than one player the method returns the first player
	 * @return player object if found, null otherwise
	 */
	public Player getSuperPlayerMaker(int teamId) {
		long count = 0;
		Player player = null;
		Team team;
		
		if(!teams.containsKey(teamId))
			return null;

		team = teams.get(teamId);
		
		Player[] players = new Player[team.getPlayers().size()];
		int index = 0;
		for (Player p : team.getPlayers().keySet()) {
			if (p != null && p.isRightLegKicker() && p.getPosition().equals(E_Position.MIDFIELDER) && team.getPlayers().get(p)) 
				players[index++] = p;
		}
		for (Player p : players) 
			if(p != null && p.getValue() > count) {
				count = p.getValue();
				player = p;
			}
		return player;
	}

	/**
	 * This query returns an array with the "Super Play Maker" player from all the teams, 
	 * a "Super Play Maker" player is as defined in the first query.
	 * the returned ArrayList must be sorted by player's value.
	 * @return player array if found, null otherwise
	 */
	public ArrayList<Player> getAllSuperPlayerMakers() {
		ArrayList<Player> players = new ArrayList<>();
		for (Team t : teams.values()) {
			Player p = getSuperPlayerMaker(t.getId());
			if (p != null) 
				players.add(p);
		}
		players.sort(Comparator.comparing(Player::getValue));
		return players;
	}

	/**
	 * This query returns the most popular position. 
	 * Most popular position is the type that belongs to the highest number of players.
	 * 
	 * @return position object if found, null otherwise
	 */
	public E_Position getTheMostPopularPosition() {
		HashMap<E_Position, Integer> types = new HashMap<>();
		int max = 0;
		E_Position toReturn = null;
		for (Player p : players.values()) {
			if (p != null) {
				if (types.containsKey(p.getPosition())) {
					types.put(p.getPosition(), types.get(p.getPosition()) + 1);
				} else
					types.put(p.getPosition(), 1);
			}
		}
		for (Map.Entry<E_Position, Integer> entry : types.entrySet()) {
			E_Position key = entry.getKey();
			Integer value = entry.getValue();
			if (value > max) {
				max = value;
				toReturn = key;
			}
		}
		return toReturn;
	} 

	/**
	 * This query returns the most favored team. 
	 * Most favored team is the team that has the highest number of customers 
	 * that the team is their favorite team.
	 * 
	 * @return team object if found, null otherwise
	 */
	public Team getTheMostFavoredTeam() {
		HashMap<Team, Integer> types = new HashMap<>();
		int max = 0;
		Team toReturn = null;
		for (Customer c : customers.values()) {
			if (c != null) {
				if (types.containsKey(c.getFavoriteTeam())) {
					types.put(c.getFavoriteTeam(), types.get(c.getFavoriteTeam()) + 1);
				} else
					types.put(c.getFavoriteTeam(), 1);
			}
		}
		for (Map.Entry<Team, Integer> entry : types.entrySet()) {
			Team key = entry.getKey();
			Integer value = entry.getValue();
			if (value > max) {
				max = value;
				toReturn = key;
			}
		}
		return toReturn;
	} 
	
	/**
	 * This query finds the most active city of a given stadium, 
	 * the most active city is the city with the highest number of employees
	 * that are working in the given stadium. 
	 * @param stadiumId
	 * @return city object, null otherwise
	 */
	public E_Cities getTheMostActiveCity(int stadiumId) {
		E_Cities[] cities = E_Cities.values();
		int[] buckets = new int[cities.length];
		Stadium stadium = new Stadium(stadiumId);
		
		if(!stadiums.containsKey(stadiumId))
			return null;

		stadium = stadiums.get(stadiumId);
		
		for(Receptionist r : stadium.getReceptionists()) 
			for(int i = 0; i < cities.length; i++) 
				if(r != null && cities[i].equals(r.getAddress().getCity())) {
					buckets[i]++;
					break;
				}
		
		for(Coach c : coaches.values()) 
			if(c.getCurrentTeam() != null && c.getCurrentTeam().getStadium().equals(stadium))
				for(int i = 0; i < cities.length; i++) 
					if(cities[i] != null && cities[i].equals(c.getAddress().getCity())) {
						buckets[i]++;
						break;
					}
		
		for(Player p : players.values()) 
			if(p.getCurrentTeam() != null && p.getCurrentTeam().getStadium().equals(stadium))
				for(int i = 0; i < cities.length; i++)
					if(cities[i] != null && cities[i].equals(p.getAddress().getCity())) {
						buckets[i]++;
						break;
					}
		
		E_Cities city = null;
		int maxCount = 0;
		
		for(int i = 0; i < buckets.length; i++)
			if(buckets[i] >= maxCount) {
				maxCount = buckets[i];
				city = cities[i];
			}
		
		return city;
	}

	/**
	 * This query returns the entity that has won the most trophies.
	 * @return
	 */
	public Object getEntityWithMostTrophies(){
		Map<Object,Integer> counter = new HashMap<>();

		int max = 0;
		Object currentOwner = null;
		for(Trophy t : trophies){
			Object owner = t.getOwner();
			counter.merge(owner,1,(a,b)-> a + b);

			int val = counter.get(owner);
			if (val > max) {
				currentOwner = owner;
				max = val;
			}
		}

		return currentOwner;
	}

	/**
	 *
	 * @return
	 */
	public Team getTeamWithLargestHomeCrowd(){

		Map<Team,Integer> crowdCounter = new HashMap<>();
		for(Match m : matches.values()){
			Team t = m.getHomeTeam();
			int[] counterVal = {0};
			m.getCrowd().forEach((customer, isHome) -> {
				if(isHome)
					counterVal[0]++;

			});

			crowdCounter.merge(t,counterVal[0],(a,b)-> a+b);
		}

		int max = 0;
		Team t = null;
		for(Map.Entry<Team,Integer> entry : crowdCounter.entrySet()){
			if (entry.getValue() >= max){
				t = entry.getKey();
				max = entry.getValue();
				System.out.println(t.getId());
			}
		}
		return t;
	}

	/**
	 * This query returns all the customers that have a subscription 
	 * to stadiun1 or stadium2 but not to both 
	 * @return array of customers if customers were found, empty list otherwise  
	 */
	public ArrayList<Customer> getCustomersStadium1XORStadium2(int stud1, int stud2) {
		ArrayList<Customer> stud1Customers = new ArrayList<>();
		ArrayList<Customer> stud2Customers = new ArrayList<>();
		ArrayList<Customer> stud1UnionStud2 = new ArrayList<>();
		if (stadiums.containsKey(stud1) && stadiums.containsKey(stud2)) {
			Stadium s1 = stadiums.get(stud1);
			Stadium s2 = stadiums.get(stud2);
			for (Customer c : customers.values()) {
				for (Subscription sub : c.getSubscriptions()) {
					if (sub.getReceptionist().getWorkingStadium().equals(s1)) 
						stud1Customers.add(c);
					if (sub.getReceptionist().getWorkingStadium().equals(s2)) 
						stud2Customers.add(c);
				}
			}
			stud1UnionStud2.addAll(stud1Customers);
			stud1UnionStud2.addAll(stud2Customers);
			stud1UnionStud2.removeIf(c -> { return stud1Customers.contains(c) && stud2Customers.contains(c); });
		}
		return stud1UnionStud2;
	}
	
	/**
	 * This query returns all the first players of the "Best Home Team" 
	 * "Best Home Team" is the team with the highest Home Away winning rate
	 * returned players must be sorted by their value. 
	 * @return array of players if players were found, empty list otherwise  
	 */
	public ArrayList<Player> getFirstPlayersOfBestHomeTeam() {
		ArrayList<Player> fPlayers = new ArrayList<>();
		double max = 0;
		Team temp = null;
		for (Team t : teams.values()) {
			if (t.getHomeAwayWinningsRate() >= max) {
				max = t.getHomeAwayWinningsRate();
				temp = t;
			}
		}
		
		if (temp == null) 
			return fPlayers;
		
		temp.getPlayers().forEach((k,v) -> {
			if (v)  
				fPlayers.add(k);
		});
			
		fPlayers.sort(Comparator.comparing(Player::getValue));
		return fPlayers;
	}
	

}// ~ END OF Class SysData
