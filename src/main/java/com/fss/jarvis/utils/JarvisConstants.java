package com.fss.jarvis.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class JarvisConstants {

	public final static String LOANACCOUNTTYPE = "loan";

	public final static String SAVINGSACCOUNTTYPE = "savings";
	
	public final static String DEPOSITACCOUNTTYPE = "deposit";

	public final static String CURRENTACCOUNTTYPE = "current";

	public final static String[] ACCOUNTTYPE = new String [] {
			LOANACCOUNTTYPE,SAVINGSACCOUNTTYPE,DEPOSITACCOUNTTYPE,CURRENTACCOUNTTYPE,"P","PRIMARY","ALL","all","primary"
	};
	public final static List<String> accountTypes = Arrays.asList(ACCOUNTTYPE);

	public final static String REGISTRATIONTYPE = "jr002";

	public final static String BALANCEENQUIRYTYPE = "jr003";

	public final static String MINISTATEMENTTYPE = "jr004";

	public final static String ATMBRANCHTTYPE = "jr005"; 
	
	public final static String SEARCHEDRESULTTYPE = "jr006";
	
	public final static String BALANCEQUES = "show me my balance in savings account?";
	
	public final static String ATMQUES = "show me nearest atm?";
	
	public final static String MINISTATQUES = "show me mini statement of savings account?";
	
	public final static Map<String, String> searchMap = new ConcurrentHashMap<String, String> ();
	static  {
		searchMap.put(BALANCEENQUIRYTYPE, BALANCEQUES);
		searchMap.put(MINISTATEMENTTYPE, MINISTATQUES);
		searchMap.put(ATMBRANCHTTYPE, ATMQUES);
	}
	
	public final static String DEFAULTFALLBACK = "default_fallback";
	
	public final static String DEFAULTQUES = "Apply for Loan in 10 seconds?";
	
}