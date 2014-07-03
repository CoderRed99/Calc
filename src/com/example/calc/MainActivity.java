package com.example.calc;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*  This is a calculator app that will use two numbers entered on entry fields
 *  and either add, subtract, multiply or divide.  A menu is created to allow
 *  the user to perform the calculation or clear the previous calculation info.
 *  A toast is displayed when the user selects the result menu item.
 * 
 *  I've commented out the various changes made to the calculator rather than
 *  deleting to leave a trail of how I got to the resulting code.  Please let
 *  me know where I can do better.
 */

public class MainActivity extends Activity implements OnClickListener 
{
	// initialize menu, display and calculation variables
	final int MENU_EQUALS_ID = 1;
	final int MENU_CLEAR_ID = 2;
	String oper = ""; //$NON-NLS-1$
	double num1 = 0.0;
	double num2 = 0.0;
	double answer = 0.0;
	
	//  define the key for the intent's extra
	public final static String EXTRA_MESSAGE = "com.example.calc.MESSAGE";
	private static final String TAG = "myLogs";  //  define debug tag
	
/*	Context context = getApplicationContext();
	CharSequence text = "Answer toast!";
	int duration = Toast.LENGTH_SHORT;
    toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);*/
	
	// declare text (entry and display) variables
	EditText firstEntry;  
	EditText secondEntry;
	TextView result;
	
	// declare buttons and textview msg
	Button buttonAdd;  
	Button buttonSubtract;
	Button buttonMultiply;
	Button buttonDivide;
/*	Button buttonEquals;	  
	Button buttonClear;*/
	  
    @SuppressLint("NewApi")
    
    /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    // find the elements
	    this.firstEntry = (EditText) findViewById(R.id.firstEntry);
	    this.secondEntry = (EditText) findViewById(R.id.secondEntry);
	    this.buttonAdd = (Button) findViewById(R.id.buttonAdd);
	    this.buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
	    this.buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
	    this.buttonDivide = (Button) findViewById(R.id.buttonDivide);
/*	    this.buttonEquals = (Button) findViewById(R.id.buttonEquals);
	    this.buttonClear = (Button) findViewById(R.id.buttonClear);*/
	    this.result = (TextView) findViewById(R.id.result);

	    // set listeners
	    this.buttonAdd.setOnClickListener(this);
	    this.buttonSubtract.setOnClickListener(this);
	    this.buttonMultiply.setOnClickListener(this);
	    this.buttonDivide.setOnClickListener(this);
/*	    this.buttonEquals.setOnClickListener(this);
	    this.buttonClear.setOnClickListener(this);  */
/*	
		// get the message from the intent
		Log.d(TAG, "get the message from the intent");
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		// set the text view result to the incoming message
		Log.d(TAG, "set the text view result to the incoming message");
		this.result.setTextSize(40);
		this.result.setText(message);*/
	}

	// Create the menu with 2 options, one that displays the calculated result msg
	//  and one that clears the previous entries and results
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		menu.add(0, this.MENU_EQUALS_ID, 0, "Result"); //$NON-NLS-1$
		menu.add(0, this.MENU_CLEAR_ID, 0, "Clear"); //$NON-NLS-1$
		return super.onCreateOptionsMenu(menu);
	}
    
	// process the menu selections
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) 
	  {
	    switch (item.getItemId()) 
	    {
	    case MENU_CLEAR_ID:
	    	// clears the display text and re-initializes the entry fields and numbers
			this.result.setText(""); //$NON-NLS-1$
			this.firstEntry.setText(""); //$NON-NLS-1$
		    this.secondEntry.setText(""); //$NON-NLS-1$
		    this.oper = ""; //$NON-NLS-1$
	        this.num1 = 0;
	        this.num2 = 0;
	        this.answer = 0;
	        break;
	    case MENU_EQUALS_ID:
/*	    	Instantiate a toast object, set the application context, display msg and duration of display
	    	and display the the newly created toast object  */	
	    	// Toast.makeText(context, text, duration).setGravity(Gravity.TOP|Gravity.LEFT, 0, 0).show();
        	// For some reason I couldn't get the gravity settings to work in the combined msg
	        Toast.makeText(getApplicationContext(),
			 "Answer toast!", Toast.LENGTH_SHORT).show(); //$NON-NLS-1$
            // displays the operands and operator, and the answer that was previously calculated
			Log.d(TAG, "displays the operands and operator");
	        this.result.setText(this.num1 + " " + this.oper + " " + this.num2 + " = " + this.answer); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	    	sendMessage(this.result);
	        break;
	    default:
	        break;
	    }
	    return super.onOptionsItemSelected(item);    // return the boolean
	  }

	  /** Called when the user chooses the result menu option */
	  public void sendMessage(View view) 
	  {
	      /*Create the intent to display the result in a new activity, 
	       * convert the text view into a string and start the new activity */
	      Log.d(TAG, "Create the intent to display the result in a new activity");
		  Intent intent = new Intent(this, DisplayResultActivity.class);
		  String message = this.result.getText().toString();
		  intent.putExtra(EXTRA_MESSAGE, message);
		  startActivity(intent);
	  }
	  
	  @Override
	  public void onClick(View v) 
	  {
	    // check if the fields are empty, gets the entries and converts to a string
	    if (TextUtils.isEmpty(this.firstEntry.getText().toString())
	        || TextUtils.isEmpty(this.secondEntry.getText().toString())) 
	    {
	      return;
	    }

	    // sets the appropriate variables with the numbers entered in the first and second edit text fields
	    this.num1 = Double.parseDouble(this.firstEntry.getText().toString());
	    this.num2 = Double.parseDouble(this.secondEntry.getText().toString());

/*	    determines the button that has been clicked and performs the corresponding operation:
	    clears any existing result text, places the appropriate operator character into oper, 
	    and calculates the answer, both of which will be used in the result display msg
*/	    switch (v.getId()) 
	    {
	    case R.id.buttonAdd:
			this.result.setText(""); //$NON-NLS-1$
  	        this.oper = "+"; //$NON-NLS-1$
	        this.answer = this.num1 + this.num2;
	        break;
	    case R.id.buttonSubtract:
			this.result.setText(""); //$NON-NLS-1$
	        this.oper = "-"; //$NON-NLS-1$
            this.answer = this.num1 - this.num2;
	        break;
	    case R.id.buttonMultiply:
			this.result.setText(""); //$NON-NLS-1$
   	        this.oper = "*"; //$NON-NLS-1$
	        this.answer = this.num1 * this.num2;
	        break;
	    case R.id.buttonDivide:
			this.result.setText(""); //$NON-NLS-1$
	        this.oper = "/"; //$NON-NLS-1$
	        this.answer = this.num1 / this.num2;
	        break;
/*	    case R.id.buttonEquals:
		    // displays the operands and operator and the answer that was previously calculated
		    result.setText(num1 + " " + oper + " " + num2 + " = " + answer);
	        break;
	    case R.id.buttonClear:
		    // clears the display text and re-initializes the entry fields and numbers
			result.setText("");
			firstEntry.setText("");
		    secondEntry.setText("");
		    oper = "";
	        num1 = 0;
	        num2 = 0;
	        answer = 0;
	        break;*/
	    default:
	        break;
	    }
	}
}
