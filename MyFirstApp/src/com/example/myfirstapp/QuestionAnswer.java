package com.example.myfirstapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionAnswer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_answer);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_answer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendMessage(View view) 
    {
        // Do something in response to button
    	
    	//Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE, message);
    	//startActivity(intent);
    	   	
    	
    	CustomAlertDialog myAlert = new CustomAlertDialog();
    	Bundle args = new Bundle();
    	args.putString("TITLE", "VERDICT");
    	
    	Log.e("testing1", message);
    	
    	CharSequence text;
    	if(message.trim().equals("2"))
    	{
    		message = "You are RIGHT!";
    		text = "Wow you are so awesome!";
    	}
    	else
    	{
    		message = "You are WRONG!";
    		text = "STUPID!";	
    	}
    
    	args.putString("MESSAGE", message);
    	myAlert.setArguments(args);
    	myAlert.show(getFragmentManager(), "alertDialog");

    	Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
    	toast.show();
    }

	@SuppressLint("ValidFragment")
	public class CustomAlertDialog extends DialogFragment 
	{		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState)
		{
			String message = getArguments().getString("MESSAGE");
			String title = getArguments().getString("TITLE");
			
	    	// 1. Instantiate an AlertDialog.Builder with its constructor
	    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

	    	// 2. Chain together various setter methods to set the dialog characteristics
	    	builder.setMessage(message)
	    	       .setTitle(title);

	    	// 3. Get the AlertDialog from create()
	    	return builder.create();
		}

	}
}
