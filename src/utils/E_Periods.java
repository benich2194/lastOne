package utils;

/**
 * Enumeration Periods ~ represent the period names used in the system
 * @author Java Course Team 2018 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public enum E_Periods {
	//-------------------------------------------------------------Values---------------------------------------------------------------------
	MONTH(1), QUARTER(3), HALF(6), YEAR(12);
	
	//-------------------------------------------------------------Class Members----------------------------------------------------------------
	private final int number;
	
	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_Periods (int number){
		this.number = number;
	}

	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public int getNumber() {
		return number;
	}
	
}// ~ END OF Enum Class Periods
