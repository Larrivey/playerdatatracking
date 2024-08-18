package com.playerdatatracking.common;

public class Constants {
	
	
	public static final int CODE_OK = 0;
	
	//ERRORS
	public static final int CODE_ERR_MALFORMED_PARAMS = 1;
	public static final int CODE_ERR_PDDB = 2;
	public static final int CODE_ERR_INPUT_EXCEPTION = 3;
	public static final int CODE_ERR_XSL_READING_EXCEPTION = 4;
	public static final int CODE_ERR_PARSE_EXCEPTION = 5;
	public static final int CODE_ERR_NOT_FILLED_JSON_RESPONSE = 6;
	public static final int CODE_ERR_NOT_CREATED_JSON_RESPONSE = 7;
	public static final int CODE_ERR_GENERATED_KEY = 8;
	public static final int CODE_ERR_KEY_MNGMT = 9;
	
	//API PLANS
	public static final String APISPORTS_FREE = "FREE";
	public static final String APISPORTS_PRO = "PRO";
	public static final String APISPORTS_ULTRA = "ULTRA";
	public static final String APISPORTS_MEGA = "MEGA";
	
	//API KEY NUMBER OF USAGES
	public static final int USAGES_APISPORTS_FREE = 100;
	public static final int USAGES_APISPORTS_PRO = 7500;
	public static final int USAGES_APISPORTS_ULTRA = 75000;
	public static final int USAGES_APISPORTS_MEGA = 150000;
	
	
	//COMMON
	public static final String CUP = "Cup";
	public static final int CUP_ID = 1;
	public static final String LEAGUE = "League";
	public static final int LEAGUE_ID = 2;
	public static final String WORLD = "World";
	public static final int COPA_INT = 3;
	public static final int INTERNATIONAL_ID = 4;
}
