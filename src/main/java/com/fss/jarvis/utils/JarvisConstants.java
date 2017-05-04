package com.fss.jarvis.utils;

import java.util.Arrays;
import java.util.List;

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

}
