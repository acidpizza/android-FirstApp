package com.example.myfirstapp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Fragments extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_test);
		// Show the Up button in the action bar.
		setupActionBar();
		
		setupActivity(savedInstanceState);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_test, menu);
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

	void setupActivity(Bundle savedInstanceState)
	{
		Fragment1 myFragment = (Fragment1) getFragmentManager().findFragmentById(R.id.fragmentContainer);
    	if(myFragment != null)
    	{
    		// fragment exists. Don't do anything.
    		Toast.makeText(getApplicationContext(), "Found!", Toast.LENGTH_SHORT).show();
    	}
    	else
    	{
    		// fragment does not exist. Create it.
    		Toast.makeText(getApplicationContext(), "NotFound!", Toast.LENGTH_SHORT).show();
    		
    		FragmentTransaction ft = getFragmentManager().beginTransaction();
        	ft.add(R.id.fragmentContainer, new Fragment1(),"fragmentTag"); 
    		ft.commit();
    	}
	}
	
    public void b1Action(View view) 
    {	
    	Fragment1 myFragment = (Fragment1) getFragmentManager().findFragmentById(R.id.fragmentContainer);
    	//Fragment1 myFragment = (Fragment1) getFragmentManager().findFragmentByTag("fragmentTag");
    	if(myFragment != null)
    	{
    		myFragment.changeText("Hello");
    		//TextView textView = (TextView) findViewById(R.id.fragment1Text);
  		  	//textView.setText("Hi");
    		Toast.makeText(getApplicationContext(), "Found!", Toast.LENGTH_SHORT).show();
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(), "NotFound!", Toast.LENGTH_SHORT).show();
    	}
    }

    public void b2Action(View view) 
    {	
    	//Fragment1 myFragment = (Fragment1) getFragmentManager().findFragmentById(R.id.fragmentContainer);
    	FragmentTransaction ft = getFragmentManager().beginTransaction();
    	ft.replace(R.id.fragmentContainer, new Fragment1(),"fragmentTag2"); 
    	ft.commit();

    }

    
}
