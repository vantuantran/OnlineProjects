package com.setcourses;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.DataSoapObject;
import org.ksoap2.serialization.DataSoapSerializationEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.login.SampleApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SetCourses extends Activity {
	
	private static final String SOAP_SetCredits = "http://services.nestor.tei.gmele.org/SetUserCredits";
    private static final String METHOD_SetCredits = "SetUserCredits";
    private static final String NAMESPACE = "http://services.nestor.tei.gmele.org/";
    private static final String URL = "http://deepspace9.cs.teiath.gr:8101/Nestor/NestorSetterService?wsdl";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    
	    Intent mIntent = getIntent();
	    int responseWhichCourse = mIntent.getIntExtra("WhichCourse", 0);
	    System.out.println(responseWhichCourse);
	    
	    String currentTicket= SampleApplication.getTicket();
        String currentAM= SampleApplication.getAM();
        
        String[] Ids = new String[3];
        Ids[0]= "Í1-4060";
        Ids[1]= "Í1-5040";
        Ids[2]= "Í1-6040";
        System.out.println(Ids.toString());
        
	 
	     //Retrieve data from GetUserCreditIds
	     SoapObject request = new SoapObject(NAMESPACE,
              METHOD_SetCredits);
	     
	     // Add the username required by web service
        request.addProperty("Ticket", currentTicket);
        // Add the AM required by web method
        request.addProperty("AM", currentAM);
	  	// Add the CreditIds required by web service
	  	request.addProperty("CourceIds", Ids);    
	  	
	  	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
	   	  envelope.setOutputSoapObject(request);
	   	System.out.println(request);

	   	  try {                 
	   		  HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	   		  //Make the soap call.
	   		  androidHttpTransport.call(SOAP_SetCredits, envelope);
	  
	   		SoapObject credid_info = (SoapObject) envelope.bodyIn;
	   		System.out.println("The value of Credid_Resp is " + credid_info.toString());
	   		Object credid_resp = envelope.getResponse();
	   		System.out.println(credid_resp);
        
	   	  }catch (Exception aE) 
    	  {
    		  System.out.println(aE.toString());
    		  aE.printStackTrace();
    	  }
	}

}
