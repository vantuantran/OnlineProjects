package com.courses1;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;

import com.coursesexempted.CoursesExempted;
import com.login.Login;
import com.login.R;
import com.login.SampleApplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Courses1 extends ListActivity {

	private static final String SOAP_GetUserCreditIDs = "http://services.nestor.tei.gmele.org/GetUserCreditIDs";
    private static final String METHOD_GetUserCreditIDs = "GetUserCreditIDs";
    private static final String NAMESPACE = "http://services.nestor.tei.gmele.org/";
    private static final String URL = "http://deepspace9.cs.teiath.gr:8101/Nestor/NestorInfoService?wsdl";
	
    private static final String SOAP_GetCreditsLevels = "http://services.nestor.tei.gmele.org/GetCreditsLevels";
    private static final String METHOD_GetCreditsLevels = "GetCreditsLevels";
    

	
	/** Called when the activity is first created. */
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        String TAG = null;

        String currentTicket= SampleApplication.getTicket();
        String currentAM= SampleApplication.getAM();
        
	     //Retrieve data from GetUserCreditIds
	     SoapObject request = new SoapObject(NAMESPACE,
                METHOD_GetUserCreditIDs);
	   
	     
	     // Add the username required by web service
         request.addProperty("Ticket", currentTicket);
         // Add the password required by web service
         request.addProperty("AM", currentAM);
        
         SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
         envelope.setOutputSoapObject(request);
                
         /*System.out.println(request);
         System.out.println(request1);*/
                  
        
         try {                 
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            // Make the soap call.
            androidHttpTransport.call(SOAP_GetUserCreditIDs, envelope);
            //androidHttpTransport.call(SOAP_GetCreditsLevels, envelope1);

            //Log.w(TAG, "This is working!");
            
            String result = envelope.getResponse().toString();
            String newresult = result.substring(1, result.length()-1);

            //String result1 = envelope1.getResponse().toString();
            //String newresult1 = result1.substring(1, result1.length()-1);
            
            String ids[]= newresult.split(", ");

            //String Credits[]= newresult1.split(", ");
            
            Log.w(TAG, "Courses1 working!!!!!!!!!!!!!");


      	     //Retrieve data from GetCreditLevels
      	     SoapObject request1 = new SoapObject(NAMESPACE,
      	    		METHOD_GetCreditsLevels);
               // Add the username required by web service
      	     request1.addProperty("Ticket", currentTicket);
               // Add the List required by web service
             request1.addProperty("CreditIds", envelope.getResponse());
               
               SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER10);
               envelope1.setOutputSoapObject(request1);
               
               System.out.println(request1);
               
               
               try {                 
                   HttpTransportSE androidHttpTransport1 = new HttpTransportSE(URL);
                   // Make the soap call.
                   //androidHttpTransport.call(SOAP_GetUserCreditIDs, envelope);
                   androidHttpTransport1.call(SOAP_GetCreditsLevels, envelope1);

                   //SoapPrimitive result1 = (SoapPrimitive)envelope1.getResponse();
                   //String result1 = envelope1.getResponse().toString();
                   Log.w(TAG, "Before 'result' working!!!!!!!!!!!!!");
                   SoapObject result1 = (SoapObject) envelope1.getResponse();
                   Log.w(TAG, "After 'result' working!!!!!!!!!!!!!");
                   //Log.w(TAG, "This is working!");
                   
                   //Log.w(TAG, "The value of Result is " + result1);
                   //String result1 = envelope1.getResponse().toString();
                   Log.w(TAG, "This is working!");
                   //String newresult1 = result1.substring(1, result1.length()-1);

                   //String Credits[]= newresult1.split(", ");
                   
                   Log.w(TAG, "Courses1 part2 working!!!!!!!!!!!!!");

        			/*ArrayAdapter<String> adapter_Credits = new ArrayAdapter<String>(this,
        					android.R.layout.simple_list_item_1, Credits);
        			setListAdapter(adapter_Credits);*/
        			
                   } catch (Exception aE) 
                     {
                      System.out.println(aE.toString());
                      aE.printStackTrace();
          	          }


            
            } catch (Exception aE) 
              {
               System.out.println(aE.toString());
               aE.printStackTrace();
   	          }
         			
	}
	
		protected void onListItemClick(ListView l, View v, int position, long id) {
				//String item = (String) getListAdapter().getItem(position);
				//Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
				
				Intent myIntent = new Intent(Courses1.this, CoursesExempted.class);
				myIntent.putExtra("WhichCourse", position);
				startActivity(myIntent);
				/*Bundle bundle = new Bundle();
                bundle.putString("Course", position);
                //bundle.putString("Username", username.substring(2).toString());
                Intent newIntent = new Intent(Courses.this, CoursesExempted.class);
                newIntent.putExtras(bundle);
                startActivityForResult(newIntent, 0); */
			}
}
