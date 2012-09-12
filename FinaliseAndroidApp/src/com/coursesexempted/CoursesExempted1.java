package com.coursesexempted;

import android.app.AlertDialog;
import android.os.Bundle;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;

import com.login.SampleApplication;

import android.view.View;
import android.widget.ListView;
import android.content.DialogInterface;
import android.content.Intent;

import android.app.ListActivity;
import android.graphics.Color;

public class CoursesExempted1 extends ListActivity {
	
	private static final String SOAP_GetExempted = "http://services.nestor.tei.gmele.org/GetExempted";
    private static final String METHOD_GetExempted = "GetExempted";
    private static final String NAMESPACE = "http://services.nestor.tei.gmele.org/";
    private static final String URL = "http://deepspace9.cs.teiath.gr:8101/Nestor/NestorInfoService?wsdl";
    
    private static final String SOAP_GetTotalCourses = "http://services.nestor.tei.gmele.org/GetTotalCourses";
    private static final String METHOD_GetTotalCourses = "GetTotalCourses";
    private static final String NAMESPACE1 = "http://services.nestor.tei.gmele.org/";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	
	    // TODO Auto-generated method stub
	    
	    Intent mIntent = getIntent();
	    int responseWhichCourse = mIntent.getIntExtra("WhichCourse", 0);
	    System.out.println(responseWhichCourse);
	    
	    String currentTicket= SampleApplication.getTicket();
        String currentAM= SampleApplication.getAM();
        
	     //Retrieve data from GetUserCreditIds
	     SoapObject request = new SoapObject(NAMESPACE,
               METHOD_GetExempted);
	     
	     // Add the username required by web service
         request.addProperty("Ticket", currentTicket);
         // Add the AM required by web method
         request.addProperty("AM", currentAM);
         
    	 SoapObject request1 = new SoapObject(NAMESPACE1,
                 METHOD_GetTotalCourses);
          // Add the Ticket required by web service
          request1.addProperty("Ticket", currentTicket);
          // Add the AM required by web service
          request1.addProperty("AM", currentAM);
         
          SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER10);
          //envelope.dotNet = true;
          envelope1.setOutputSoapObject(request1);
         
          try {                 
              HttpTransportSE androidHttpTransport1 = new HttpTransportSE(URL);
              // Make the soap call.
              androidHttpTransport1.call(SOAP_GetTotalCourses, envelope1);

              SoapPrimitive total_courses = (SoapPrimitive)envelope1.getResponse();
              System.out.println("The value of Result is " + total_courses);
              
              if (Integer.decode(total_courses.toString()) >= 40)
              {
            	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
            	  builder.setTitle("Απαλλαγές Μαθημάτων")
            	  		 .setMessage("Έχετε ολοκληρώσει με επιτυχία τον αριθμό μαθημάτων για λήψη πτυχίου. " +
            	  		 			 "Δεν χρειάζεται να προβείτε σε δήλωση απαλλαγής από μαθήματα με χρήση πιστωτικών μονάδων.")
            	         .setCancelable(false)
            	         .setNeutralButton("Exit", new DialogInterface.OnClickListener() {
            	             public void onClick(DialogInterface dialog, int id) {
            	                  //Intent newIntent = new Intent(CoursesExempted1.this, Login.class);
            	                  //startActivityForResult(newIntent, 0);
            	                  finish();
            	             }
            	         });
            	  AlertDialog alert = builder.create();
            	  alert.show();
            	  
              }else
              {
            	  
            	  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            	  envelope.setOutputSoapObject(request);
         
            	  try {                 
            		  HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            		  //Make the soap call.
            		  androidHttpTransport.call(SOAP_GetExempted, envelope);
             

            		  //Log.w(TAG, "This is working!");
             
            		  String result = envelope.getResponse().toString();
            		  String newresult = result.substring(1, result.length()-1);

             
            		  String[] C_Exempted= newresult.split(", ");
            		  String[][] C_Exempted_2d = new String [C_Exempted.length/3][3];
             
            		  int k= 0;
            		  String[] C_Exempted_show = new String[C_Exempted.length/3];
            		  for(int i=0; i<= C_Exempted.length/3 - 1; i++)
            		  {
            			  for(int j=0; j<3; j++)
            			  {
            				  C_Exempted_2d[i][j] = C_Exempted[k];
            				  k++;
            				  //System.out.println(C_Exempted_2d[i][j]);
            			  };
            			  C_Exempted_show[i] = C_Exempted_2d[i][2]+C_Exempted_2d[i][1];
            		  };
            
            		  setListAdapter(new MySimpleArrayAdapter(this, C_Exempted_show));

            	  } catch (Exception aE) 
            	  {
            		  System.out.println(aE.toString());
            		  aE.printStackTrace();
            	  }
              }
          } catch (Exception aE) {
         System.out.println(aE.toString());
         aE.printStackTrace();
     	}

         
    	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//String item = (String) getListAdapter().getItem(position);
		//v.setSelected(true);
		v.setBackgroundColor(Color.DKGRAY);
		//Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}

}
