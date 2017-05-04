/**
 * 
 */
package com.fss.jarvis.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.jarvis.bean.AIDataResponse;
import com.fss.jarvis.bean.AIParamRequest;
import com.fss.jarvis.bean.AIRequest;
import com.fss.jarvis.bean.AIResponse;
import com.fss.jarvis.bean.AISlideArrayResponse;
import com.fss.jarvis.bean.CustomerDetails;
import com.fss.jarvis.dao.ProcessQuery;
import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.ChatTranDetails;
import com.fss.jarvis.entity.LocatorDetails;
import com.fss.jarvis.entity.Registration;
import com.fss.jarvis.entity.TransactionConfiguration;
import com.fss.jarvis.entity.TransactionDetails;
import com.fss.jarvis.service.RegistrationService;
import com.fss.jarvis.utils.JarvisConstants;
import com.fss.jarvis.utils.JarvisUtils;

/**
 * @author Abdulla
 * @param <T>
 *
 */
@Service("registrationService")
public class RegistrationServiceImpl<T> implements RegistrationService {

	@Autowired
	private ProcessQuery<T> processQuery;
	
	@Autowired
	public JarvisUtils jarvisUtils;
	
	public static Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	
	@Override
	public AIResponse process(AIRequest request) {
		ChatTranDetails chatTranDtls = new ChatTranDetails();
		chatTranDtls.setMobileNo(jarvisUtils.validateNullData(request.getResultRequest().getParameters().getMobileNo()));
		chatTranDtls.settType(jarvisUtils.validateNullData(request.getResultRequest().getParameters().getTranType()));
		chatTranDtls.setQuery(jarvisUtils.validateNullData(request.getResultRequest().getReqQuery()));
		chatTranDtls.setAiAction(jarvisUtils.validateNullData(request.getResultRequest().getReqAction()));
		processQuery.saveObject((T) chatTranDtls);
		AIResponse response = getTransactionIdentifier(request);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.fss.jarvis.service.RegistrationService#insertUserDetails(com.fss.jarvis.bean.AIRequest)
	 */
	private AIResponse insertUserDetails(AIRequest request) {
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
				accInfo.setAccountNo(RandomStringUtils.randomNumeric(14));
				processQuery.saveObject((T) accInfo);
				AccountInfo accLoanInfo = accInfo;
				accLoanInfo.setAccountType("loan");
				accLoanInfo.setBalance(RandomStringUtils.randomNumeric(5));
				accLoanInfo.setAccountNo(RandomStringUtils.randomNumeric(14));
				processQuery.saveObject((T) accLoanInfo);
				AccountInfo accDepInfo = accInfo;
				accDepInfo.setAccountType("deposit");
				accDepInfo.setBalance(RandomStringUtils.randomNumeric(5));
				accDepInfo.setAccountNo(RandomStringUtils.randomNumeric(14));
				processQuery.saveObject((T) accDepInfo);
				AccountInfo accCurrInfo = accInfo;
				accCurrInfo.setAccountType("current");
				accCurrInfo.setBalance(RandomStringUtils.randomNumeric(5));
				accCurrInfo.setAccountNo(RandomStringUtils.randomNumeric(14));
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
		} 
		AIResponse response = setResponse(speech, custDetails);
		return response;
	}
	
	
	public AIResponse getTransactionIdentifier(AIRequest request) {
		AIResponse response = null;
		String tType = request.getResultRequest().getParameters().getTranType();
		switch (tType) {
		case JarvisConstants.REGISTRATIONTYPE :
			response = insertUserDetails(request);
			break;
		case JarvisConstants.BALANCEENQUIRYTYPE :
			response = getBalanceEnquiry(request);
			break;
		case JarvisConstants.MINISTATEMENTTYPE :
			response = getBalanceEnquiry(request);
			break;
		case JarvisConstants.ATMBRANCHTTYPE :
			response = getAtmDetails(request);
			break;	
		default :
			LOGGER.info("Invalid Transaction type");
			response = new AIResponse();
			response.setSpeech("Invalid Request");
		}
		return response;
	}
	
	private AIResponse getBalanceEnquiry(AIRequest request) {
		String speech = "";
		AIResponse response = new AIResponse();
		CustomerDetails custDetails = new CustomerDetails();
		AIParamRequest paramRequest = request.getResultRequest().getParameters();
		TransactionConfiguration tranConfig = processQuery.getMpinFlag(paramRequest.getTranType());
		custDetails.setResolvedQuery(request.getResultRequest().getReqQuery());
		if (jarvisUtils.validateNullData(paramRequest.getAccountType()).equals("-")) {
			speech = "Please enter  account type...";
			custDetails.setSpeech("Please enter  account type...");
			custDetails.setStrArray(processQuery.getAccountType(paramRequest.getMobileNo()));
			custDetails.setmPinFlag(tranConfig.getMpinRequiredFlg());
			custDetails.setStrArrayKey("accountType");
			custDetails.setParamRequest(paramRequest);
		} else {
			if ((jarvisUtils.validateNullData(paramRequest.getMpin()).equals("-") && "Y".equals(tranConfig.getMpinRequiredFlg()))) {
				speech = "Please enter  valid mPin";
				custDetails.setSpeech("Please enter valid mPin");
				custDetails.setmPinFlag(tranConfig.getMpinRequiredFlg());
				custDetails.setParamRequest(paramRequest);
			} else {
				if (JarvisConstants.accountTypes.contains(paramRequest.getAccountType())) {
					if (paramRequest.getAccountType().equalsIgnoreCase("p")
							|| paramRequest.getAccountType().equalsIgnoreCase("primary")) {
						paramRequest.setAccountType("savings");
					}
					NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
					if (paramRequest.getTranType().equalsIgnoreCase("jr003")) {
						List<AccountInfo> accInfoList = processQuery.getBalance(paramRequest.getMobileNo(), paramRequest.getAccountType());
						if (accInfoList.size() == 1) {
							AccountInfo accInfo = accInfoList.get(0);
							String bal = formatter.format(Double.valueOf(accInfo.getBalance()));
							bal = bal.replace("Rs.", "\u20B9 ");
							speech = "Your Balance is " + bal;
							AISlideArrayResponse slideResponse = setArrayResponse("Balance", accInfo.getAccountType(), accInfo.getAccountNo(), bal);
							List<AISlideArrayResponse> slideArrayList = new ArrayList<AISlideArrayResponse>();
							slideArrayList.add(slideResponse);
							custDetails.setStrSlideArray(slideArrayList);
						} else {
							final List<AISlideArrayResponse> slideArrayList = new ArrayList<AISlideArrayResponse>();
							accInfoList.forEach(acc -> {
								final AISlideArrayResponse slideResponse = setArrayResponse("Balance", acc.getAccountType(), acc.getAccountNo(), formatter.format(Double.valueOf(acc.getBalance())).replace("Rs.", "\u20B9 "));
								slideArrayList.add(slideResponse);
							});
							speech = "Please find the balance of all accounts";
							custDetails.setStrSlideArray(slideArrayList);
						}
					} else if (paramRequest.getTranType().equalsIgnoreCase("jr004")) {
						final List<TransactionDetails> tranDetails = processQuery.getTxnDetails();
						final List<AISlideArrayResponse> slideArrayList = new ArrayList<AISlideArrayResponse>();
						tranDetails.forEach(acc -> {
							final AISlideArrayResponse slideResponse = setArrayResponse(acc.getTxnId(), acc.getDate(),  formatter.format(Double.valueOf(acc.getAmount())).replace("Rs.", "\u20B9 "), acc.getRemarks());
							slideArrayList.add(slideResponse);
						});
						speech = "Please find the statement..";
						custDetails.setStrSlideArray(slideArrayList);
					}
				} else {
					speech = "Invalid account Type";
				}
			}
		}
		response = setResponse(speech, custDetails);
		return response;
	}
	
	
	private AIResponse getAtmDetails(AIRequest request) {
		String speech = "";
		CustomerDetails custDetails = new CustomerDetails();
		AIParamRequest paramRequest = request.getResultRequest().getParameters();
		custDetails.setParamRequest(paramRequest);
		custDetails.setResolvedQuery(request.getResultRequest().getReqQuery());
		
		if ("Y".equalsIgnoreCase(jarvisUtils.validateNullData(paramRequest.getNearIdentifier()))) {
			custDetails.setLocationFlag("Y");
			speech = "Request you switch on locator to show nearest details";
		} else {
			List<LocatorDetails> atmAddrList = processQuery.getAtmAddress(paramRequest.getAtmBrchCity());
			custDetails.setStrMapArray(atmAddrList);
			speech = "Please find the atm details as requested";
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
	
	public AISlideArrayResponse setArrayResponse(String dataOne, String dataTwo, String dataThree, String dataFour) {
		AISlideArrayResponse response = new AISlideArrayResponse();
		response.setDataOne(dataOne);
		response.setDataTwo(dataTwo);
		response.setDataThree(dataThree);
		response.setDataFour(dataFour);
		return response;
	}


}
