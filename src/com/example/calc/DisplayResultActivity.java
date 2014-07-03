package com.example.calc;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DisplayResultActivity extends Activity  
{
	public final static String EXTRA_MESSAGE = "com.example.calc.MESSAGE";
	String returnMsg;
	TextView textView1;
	private static final String TAG = "myLogs";
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_result);

		// Set the activity_display_result as the activity layout
		Log.d(TAG, "Set the activity_display_result as the activity layout");
		setContentView(R.layout.activity_display_result);
		
		// get the intent from the main activity and extract the message
		Log.d(TAG, "get the intent from the main activity and extract the message");
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		this.returnMsg = message;
		
		// setup the text view
		Log.d(TAG, "setup the text view");
	    this.textView1 = (TextView) findViewById(R.id.textView1);
		this.textView1.setTextSize(40);
		this.textView1.setText(message);
	
		 // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
        {
		    // Show the Up button in the action bar.
		    getActionBar().setDisplayHomeAsUpEnabled(true);
        }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
	    default:
	        break;
		}
		return super.onOptionsItemSelected(item);
	}
	
    /** Called when the user clicks the go back button */
    // public void goBackMessage(String this.returnMsg) 
    public void goBackMessage(View view)    
    {
        /*Create the intent to display the result in the main activity  */
        Log.d(TAG, "Create the intent to display the result in the main activity");
        Intent intent = new Intent(this, MainActivity.class);
	    intent.putExtra(EXTRA_MESSAGE, this.returnMsg);
	    startActivity(intent);
    }
}
