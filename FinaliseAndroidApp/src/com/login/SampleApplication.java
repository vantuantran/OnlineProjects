package com.login;

import android.app.Application;;

public class SampleApplication extends Application {
	
	private static String ticket;
	private static String AM;

	@Override
	public void onCreate() {
	    super.onCreate();
	    ticket="";
	    AM="";
	}

	public static String getTicket() {
	    return ticket;
	}

	public static void setTicket(String ticket) {
	    SampleApplication.ticket = ticket;
	}

	public static String getAM() {
	    return AM;
	}

	public static void setAM(String AM) {
	    SampleApplication.AM = AM;
	}


}
