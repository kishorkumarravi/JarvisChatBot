/**
 * 
 */
package com.fss.jarvis.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.jarvis.bean.AIDataResponse;
import com.fss.jarvis.bean.AIParamRequest;
import com.fss.jarvis.bean.AIRequest;
import com.fss.jarvis.bean.AIResponse;
import com.fss.jarvis.bean.CustomerDetails;
import com.fss.jarvis.dao.ProcessQuery;
import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.Registration;
import com.fss.jarvis.service.RegistrationService;

/**
 * @author Abdulla
 * @param <T>
 *
 */
@Service("registrationService")
public class RegistrationServiceImpl<T> implements RegistrationService {

	@Autowired
	private ProcessQuery<T> processQuery;
	
	public static Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	
	/* (non-Javadoc)
	 * @see com.fss.jarvis.service.RegistrationService#insertUserDetails(com.fss.jarvis.bean.AIRequest)
	 */
	@Override
	public AIResponse insertUserDetails(AIRequest request) {
		String speech = "";
		CustomerDetails custDetails = new CustomerDetails();
		AIParamRequest paramRequest = request.getResultRequest().getParameters();
		Registration regCheck = getUserDetails(paramRequest.getMobileNo());
		if (paramRequest.getTranType().equalsIgnoreCase("jr002")) {
			if (regCheck == null) {
				Registration registration = new Registration();
				registration.setMobileNo(paramRequest.getMobileNo());
				registration.setEmailId(paramRequest.getEmailId());
				registration.setRegName(paramRequest.getRegname());
				processQuery.saveObject((T) registration);
				custDetails.setEmailId(paramRequest.getEmailId());
				custDetails.setMobileNo(paramRequest.getMobileNo());
				custDetails.setRemitterName(paramRequest.getRegname());
				String balance = RandomStringUtils.randomNumeric(5);
				AccountInfo accInfo = new AccountInfo();
				accInfo.setMobileNo(paramRequest.getMobileNo());
				accInfo.setBalance(balance);
				accInfo.setAccountType("savings");
				processQuery.saveObject((T) accInfo);
				AccountInfo accLoanInfo = accInfo;
				accLoanInfo.setAccountType("loan");
				accLoanInfo.setBalance(RandomStringUtils.randomNumeric(5));
				processQuery.saveObject((T) accLoanInfo);
				AccountInfo accDepInfo = accInfo;
				accDepInfo.setAccountType("deposit");
				accDepInfo.setBalance(RandomStringUtils.randomNumeric(5));
				processQuery.saveObject((T) accDepInfo);
				AccountInfo accCurrInfo = accInfo;
				accCurrInfo.setAccountType("current");
				accCurrInfo.setBalance(RandomStringUtils.randomNumeric(5));
				processQuery.saveObject((T) accCurrInfo);
				speech = "Registered Successfully";
			} else {
				custDetails.setEmailId(regCheck.getEmailId());
				custDetails.setMobileNo(regCheck.getMobileNo());
				custDetails.setRemitterName(regCheck.getRegName());
				speech = "Hi "+ regCheck.getRegName() + " you are already registered with us...how may I help you";
			}
		} else if (paramRequest.getTranType().equalsIgnoreCase("jr001")) {
			String isRegFlag = "Y";
			if (regCheck == null) {
				isRegFlag = "N";
			} else {
				custDetails.setEmailId(regCheck.getEmailId());
				custDetails.setMobileNo(regCheck.getMobileNo());
				custDetails.setRemitterName(regCheck.getRegName());
				speech = "Hi "+ regCheck.getRegName() + ", Its nice to see you back...what can I do for you";
				custDetails.setRegFlag(isRegFlag);
			}
		} else if (paramRequest.getTranType().equalsIgnoreCase("jr003")) {
			if (paramRequest.getAccountType() == null && !"".equals(paramRequest.getAccountType())) {
				
				speech = "Please share your account type...";
			}
			AccountInfo accInfo = processQuery.getBalance(paramRequest.getMobileNo());
			speech = "Your Balance is "+ accInfo.getBalance();
		}
		AIResponse response = setResponse(speech, custDetails);
		return response;
	}
	
	
	public Registration getUserDetails(String mobileNo) {
		Registration registration = processQuery.getUserDetails(mobileNo);
		return registration;
	}
		
	public AIResponse setResponse (String msg, CustomerDetails customerDetails) {
		AIResponse response = new AIResponse();
		AIDataResponse dataResp = new AIDataResponse();
		LOGGER.info("Speech {}", msg);
		dataResp.setCustomerDetails(customerDetails);
		response.setSpeech(msg);
		response.setDisplayText(msg);
		response.setAiDataRsp(dataResp);
		return response;		
	}

}
