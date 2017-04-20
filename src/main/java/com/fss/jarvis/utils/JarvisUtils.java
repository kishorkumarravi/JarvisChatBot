/**
 * 
 */
package com.fss.jarvis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.jarvis.bean.AIParamRequest;
import com.fss.jarvis.bean.AIRequest;
import com.fss.jarvis.bean.CustomerDetails;
import com.fss.jarvis.dao.ProcessQuery;
import com.fss.jarvis.entity.TransactionConfiguration;

/**
 * @author admin
 *
 */
@Service("jarvisUtils")
public class JarvisUtils {

	public static Logger LOGGER = LoggerFactory.getLogger(JarvisUtils.class);

	@Autowired
	private ProcessQuery processQuery;

	public String validateNullData(String value) {

		if (value == null || "".equals(value)) {
			value = "-";
		}
		LOGGER.info("value :: {}", value);
		return value;
	}

	public CustomerDetails validateMpinAndAccountType(AIRequest request) {
		CustomerDetails custDetails = new CustomerDetails();
		AIParamRequest paramRequest = request.getResultRequest().getParameters();
		TransactionConfiguration tranConfig = processQuery.getMpinFlag(paramRequest.getTranType());
		custDetails.setResolvedQuery(request.getResultRequest().getReqQuery());
		if (validateNullData(paramRequest.getAccountType()).equals("-")) {
			custDetails.setSpeech("Please enter  account type...");
			custDetails.setStrArray(processQuery.getAccountType(paramRequest.getMobileNo()));
			custDetails.setmPinFlag(tranConfig.getMpinRequiredFlg());
			custDetails.setStrArrayKey("accountType");
			custDetails.setParamRequest(paramRequest);
		} else {
			if ((validateNullData(paramRequest.getMpin()).equals("-") && "Y".equals(tranConfig.getMpinRequiredFlg()))) {
				custDetails.setSpeech("Please enter valid mPin");
				custDetails.setmPinFlag(tranConfig.getMpinRequiredFlg());
				custDetails.setParamRequest(paramRequest);
			} else {
				if (paramRequest.getAccountType().equalsIgnoreCase("savings")
						|| paramRequest.getAccountType().equalsIgnoreCase("loan")
						|| paramRequest.getAccountType().equalsIgnoreCase("deposit")
						|| paramRequest.getAccountType().equalsIgnoreCase("current")
						|| paramRequest.getAccountType().equalsIgnoreCase("p")
						|| paramRequest.getAccountType().equalsIgnoreCase("primary")
						|| paramRequest.getAccountType().equalsIgnoreCase("ALL")) {
					if (paramRequest.getAccountType().equalsIgnoreCase("p")
							|| paramRequest.getAccountType().equalsIgnoreCase("primary")) {
						paramRequest.setAccountType("savings");
					}
				}
			}
		}
		return custDetails;
	}
}
