package utils;

/**
 * Enumeration Cities ~ represent the city names used in the system
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public enum E_Cities {
	//-------------------------------------------------------------Values---------------------------------------------------------------------
	Acre("Israel"),				Afula("Israel"),				Arad("Israel"),	
	Ariel("Israel"),			Ashdod("Israel"),				Ashkelon("Israel"),	
	Baqa_Jatt("Israel"),		Bat_Yam("Israel"),				Beersheba("Israel"),
	Beit_Shean("Israel"),		Beit_Shemesh("Israel"),			Beitar_Illit("Israel"),	
	Bnei_Brak("Israel"),		Dimona("Israel"),				Eilat("Israel"),	
	Elad("Israel"),				Givatayim("Israel"),			Givat_Shmuel("Israel"),	
	Hadera("Israel"),			Haifa("Israel"),				Herzliya("Israel"),	
	Hod_HaSharon("Israel"),		Holon("Israel"),				Jerusalem("Israel"),	
	Karmiel("Israel"),			Kafr_Qasim("Israel"),			Kfar_Saba("Israel"),			
	Kiryat_Ata("Israel"),		Kiryat_Bialik("Israel"),		Kiryat_Gat("Israel"),	
	Kiryat_Malakhi("Israel"),	Kiryat_Motzkin("Israel"),		Kiryat_Ono("Israel"),	
	Kiryat_Shmona("Israel"),	Kiryat_Yam("Israel"), 			Kiryat_Haim("Israel"),	
	Maale_Adumim("Israel"),		Maalot_Tarshiha("Israel"),		Migdal_HaEmek("Israel"),
	Modiin_Illit("Israel"),		Modiin_Maccabim_Reut("Israel"),	Nahariya("Israel"),	
	Nazareth("Israel"),			Nazareth_Illit("Israel"),		Nesher("Israel"),	
	Ness_Ziona("Israel"),		Netanya("Israel"),				Netivot("Israel"),				
	Ofakim("Israel"),			Or_Akiva("Israel"),				Or_Yehuda("Israel"),	
	Petah_Tikva("Israel"),		Qalansawe("Israel"),			Raanana("Israel"),
	Rahat("Israel"),			Ramat_Gan("Israel"),			Ramat_HaSharon("Israel"),	
	Ramla("Israel"),			Rehovot("Israel"),				Rishon_LeZion("Israel"),	
	Rosh_HaAyin("Israel"),		Safed("Israel"),				Sakhnin("Israel"),		
	Sderot("Israel"),			Shefa_Amr("Israel"),			Tamra("Israel"),	
	Tayibe("Israel"),			Tel_Aviv("Israel"),				Tiberias("Israel"),	
	Tira("Israel"),				Tirat_Carmel("Israel"),			Umm_al_Fahm("Israel"),	
	Yavne("Israel"),			Yehud_Monosson("Israel"),		Yokneam("Israel"), Lod("Israel");
	
	//-------------------------------------------------------------Class Members----------------------------------------------------------------
	private final String country;
	
	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_Cities(String country){
		this.country = country;
	}
	
	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public String getCountry() { 
		return country; 
	}

}// ~ END OF Enum Class Cities
