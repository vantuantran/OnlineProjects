package com.coursesexempted;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.DataSoapObject;
import org.ksoap2.serialization.DataSoapSerializationEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;



import com.courses.Courses;
import com.login.Login;
import com.login.R;
import com.login.SampleApplication;
import com.login.R.color;
import com.setcourses.SetCourses;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CoursesExempted extends ListActivity {
	
	private static final String SOAP_GetUserCreditIDs = "http://services.nestor.tei.gmele.org/GetUserCreditIDs";
    private static final String METHOD_GetUserCreditIDs = "GetUserCreditIDs";
    private static final String NAMESPACE = "http://services.nestor.tei.gmele.org/";
    private static final String URL = "http://deepspace9.cs.teiath.gr:8101/Nestor/NestorInfoService?wsdl";
    
    private static final String SOAP_GetCreditsLevels = "http://services.nestor.tei.gmele.org/GetCreditsLevels";
    private static final String METHOD_GetCreditsLevels = "GetCreditsLevels";
    private static final String NAMESPACE1 = "http://services.nestor.tei.gmele.org/";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);

	    // TODO Auto-generated method stub
	    String TAG = null;
	    //GetCreditsLevels.initMapping();
	    
	    Intent mIntent = getIntent();
	    int responseWhichCourse = mIntent.getIntExtra("WhichCourse", 0);
	    System.out.println(responseWhichCourse);
	    
	    String currentTicket= SampleApplication.getTicket();
        String currentAM= SampleApplication.getAM();
        
	     //Retrieve data from GetUserCreditIds
	     SoapObject request = new SoapObject(NAMESPACE,
               METHOD_GetUserCreditIDs);
	     
	     // Add the username required by web service
         request.addProperty("Ticket", currentTicket);
         // Add the AM required by web method
         request.addProperty("AM", currentAM);
	
   	  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
   	  envelope.setOutputSoapObject(request);

   	  try {                 
   		  HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
   		  //Make the soap call.
   		  androidHttpTransport.call(SOAP_GetUserCreditIDs, envelope);
  
   		SoapObject credid_info = (SoapObject) envelope.bodyIn;
   		System.out.println("The value of Credid_Resp is " + credid_info.toString());
   		//Object credid_resp = envelope.getResponse();
   		System.out.println ("-----");
   		Vector<SoapPrimitive> credid_resp = (Vector<SoapPrimitive>)envelope.getResponse();
   		//ArrayList<String> credid_resp = (ArrayList<String>)envelope.getResponse();
   		System.out.println ("-----");
   		//System.out.println (credid_resp.size());
   		
   		ArrayList<String> x = new ArrayList<String>();
   		
   		int i=0;
   		for (Object A  : credid_resp)
   		{
   			System.out.println ("///" + A.getClass().getName() + "  " + A.toString());
   		//	x.addElement(A.toString());
   			x.add(A.toString());
   			System.out.println("+"+ x.get(i++) + "+");
   		}
   		
   		  
   	      /*String credid_resp = envelope.getResponse().toString();
   	      System.out.println(credid_resp);*/
		  /*String newresult = result.substring(1, result.length()-1);
		  System.out.println("Result->" + newresult);

		  String[] credids= newresult.split(", ");
		  
		  for(int i=0; i<= credids.length - 1; i++)
		  {
			  System.out.println(credids[i]);
		  }*/
		  		
  		  		SoapObject request1 = new SoapObject(NAMESPACE1,
	                 METHOD_GetCreditsLevels);
  		  		// Add the Ticket required by web service
  		  		request1.addProperty("Ticket", currentTicket);
  		  		// Add the CreditIds required by web service
  		  		request1.addProperty("CreditIds", x);
  		  		System.out.println("tick"+currentTicket + "id" + x.toString());
  		  	
  		  		SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER10);
  		  		//envelope.dotNet = true;
  		  		envelope1.setOutputSoapObject(request1);
  		  		System.out.println(request1);
  		  		//androidHttpTransport1.debug=true;
  		  		
	          try {                 
	              // Make the soap call.
	        	  HttpTransportSE androidHttpTransport1 = new HttpTransportSE(URL);
	    	  	        	  
	        	  androidHttpTransport1.call(SOAP_GetCreditsLevels, envelope1);
	        	  //String result2 = envelope1.getResponse().toString();
	        	  //Object obj_exam = envelope1.bodyIn;
	        	  //DataSoapObject obj = (DataSoapObject) envelope1.bodyIn;
	        	  //SoapObject obj_resp = (SoapObject) envelope1.getResponse();
	        	  //Vector resp = (Vector)obj.getProperty(0);
	        	  //String resp = (String)obj.getPropertyAsString(obj.toString());
	        	  //Log.w(TAG, "Credit Levels Internal Working11111111!!!!");
	  			  //DataSoapObject credlev = DataSoapObject.fromSoapObject(obj);
	  			  //Log.w(TAG, "Credit Levels Internal Working!!!!");
	        	  //SoapPrimitive result1 = (SoapPrimitive)envelope1.getResponse();
	        	  System.out.println("+++++");
	        	  //Vector<Object> result2 = (Vector<Object>)envelope1.getResponse();
	        	  System.out.println("+++++");

	         		/*for (Object B  : result2)
	         		{
	         			System.out.println ("///" + B.getClass().getName() + "  " + B.toString());
	         		}
	        	  */
	  			  Log.w(TAG, "Credit Levels Internal Working!!!!");
	  			  
	  			  /*Intent newIntent = new Intent(CoursesExempted.this, SetCourses.class);
	  			  startActivityForResult(newIntent, 0); */
	              //Log.w(TAG, "The value of Result is " + credlev.toString());
	              //System.out.println("The value of Result is " + resp);
	              
	       		  /*String result1 = envelope1.getResponse().toString();
	    		  String newresult1 = result1.substring(1, result.length()-1);
	    		  
	    		  Log.w(TAG, "Credit Levels Internal Working!!!!");
	    		  
	    		  String[] creditlevels= newresult1.split(", ");
	    		  for(int i=0; i<= creditlevels.length - 1; i++)
	    		  {
	    			  System.out.println(creditlevels[i]);
	    		  } */ 
	          }catch (Exception aE) 
	    	  {
	    		  System.out.println(aE.toString());
	    		  aE.printStackTrace();
	    	  }
	          /*catch(org.xmlpull.v1.XmlPullParserException ex2)
	          {               
	              System.out.println("Request"+ androidHttpTransport1.requestDump.toString());

	          }  catch (Exception e)
	          {
	              e.printStackTrace();
	              System.out.println("Request" + androidHttpTransport1.requestDump.toString());
	          }*/

   	  } catch (Exception aE) 
	  {
		  System.out.println(aE.toString());
		  aE.printStackTrace();
	  }
   	 
   	  
	}

	
}
