package com.coursesexempted;

import com.login.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;

	public MySimpleArrayAdapter(Context context, String[] Exempted_show) {
		super(context, R.layout.listview1, Exempted_show);
		this.context = context;
		this.values = Exempted_show;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.listview1, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.text1);
		textView.setText(values[position]);
 
		// Change icon based on name
		String s = values[position];
 
		//System.out.println(s);
		if(s.startsWith("1")){
			textView.setTextColor(Color.RED);
			textView.setText(s.substring(1));
		}else if(s.startsWith("2")){
			textView.setTextColor(Color.BLUE);
			textView.setText(s.substring(1));
		}else if(s.startsWith("3")){
			textView.setTextColor(Color.GREEN);
			textView.setText(s.substring(1));
		};
		textView.setBackgroundColor(Color.GRAY);
		return rowView;
	}
}