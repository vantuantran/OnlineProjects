package com.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.*;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.transport.HttpTransportSE;

import com.coursesexempted.CoursesExempted;
import com.coursesexempted.CoursesExempted1;

public class Login extends Activity {
	
	private static final String SOAP_ACTION = "http://services.athena.tei.gmele.org/Login";
    private static final String METHOD_NAME = "Login";
    private static final String NAMESPACE = "http://services.athena.tei.gmele.org/";
    private static final String URL = "http://deepspace9.cs.teiath.gr:8101/Athena/LoginServiceService?wsdl";
    
	// Declare our Views, so we can access them later
	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;
	private Button btnCancel;
	private TextView lblResult;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Get the EditText and Button References
        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        lblResult = (TextView)findViewById(R.id.result);
        
        
     // Set Click Listener
        btnLogin.setOnClickListener(new OnClickListener() {
            //@Override
            public void onClick(View v) {

            	String TAG = null;
                // Check Login
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                
                SoapObject request = new SoapObject(NAMESPACE,
                        METHOD_NAME);
                 // Add the username required by web service
                 request.addProperty("LogName", username);
                 // Add the password required by web service
                 request.addProperty("Password", password);
                 Log.w(TAG, "First Part working!");
                
                 SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                 envelope.setOutputSoapObject(request);
                 
                 System.out.println(request);
                 
                 Log.w(TAG, "This is working!");
                 try {          
                     HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                     // Make the soap call.
                     androidHttpTransport.call(SOAP_ACTION, envelope);
       
                     SoapPrimitive result = (SoapPrimitive)envelope.getResponse();
                     
                     String result1 = result.toString();
                     System.out.println("result1: " +result1);
      
                     
                     Log.w(TAG, "The value of Result is " + result);
                     
                     if(result.toString().matches("!1001Invalid Logname or Password"))
                     {
                  	   lblResult.setText("Incorrect Username or Password");
                     }else
                     {
                    	 SampleApplication.setTicket(result.toString());
                    	 SampleApplication.setAM(username.substring(2).toString());
                         Intent newIntent = new Intent(Login.this, CoursesExempted1.class);
                         //newIntent.putExtras(bundle);
                         startActivityForResult(newIntent, 0);
                     }
                     
            } catch (Exception aE) {
                System.out.println(aE.toString());
                aE.printStackTrace();
            	}
                                  
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            //@Override
            public void onClick(View v) {
                // Close the application
                finish();
            }
        });
        
    }

}
    