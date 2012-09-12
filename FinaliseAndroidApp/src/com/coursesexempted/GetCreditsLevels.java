package com.coursesexempted;

import org.ksoap2.serialization.DataSoapObject;

public class GetCreditsLevels extends DataSoapObject {


	private static final String DATA_NAMESPACE = "http://services.nestor.tei.gmele.org/";
	private static final String CLASS_NAME_GetCreditLevels = "GetCreditsLevels";

	public static void initMapping() {
		System.setProperty(CLASS_NAME_GetCreditLevels,
				"com.coursesexempted.GetCreditsLevels");
	}
	
	public String Ticket= null;
	public String CreditIds = null;

	public GetCreditsLevels() {
		super(DATA_NAMESPACE, CLASS_NAME_GetCreditLevels);
	}
	
	public GetCreditsLevels(String Ticket, String CreditIds) {
		super(DATA_NAMESPACE, CLASS_NAME_GetCreditLevels);
		setTicket(CreditIds);
		setCreditIds(CreditIds);
	}

	public GetCreditsLevels(String namespace) {
		super(namespace, CLASS_NAME_GetCreditLevels);
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String Ticket) {
		this.CreditIds = Ticket;
	}
	
	public String getCreditIds() {
		return CreditIds;
	}

	public void setCreditIds(String CreditIds) {
		this.CreditIds = CreditIds;
	}
	
	public String toString() {
		String tmp = "CreditLev : {";
		tmp += "CreditIds=" + CreditIds + " } ";
		return tmp;
	}
		
}
