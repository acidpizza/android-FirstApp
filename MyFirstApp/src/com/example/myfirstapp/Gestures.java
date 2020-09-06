package com.example.myfirstapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Gestures extends Activity  
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gestures);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		ImageView imageView = (ImageView) findViewById(R.id.rotImage);
//		FrameLayout frame = (FrameLayout) findViewById(R.id.rotFrame);	
//		frame.setBackgroundColor(Color.argb(255,0,255,0));
//		frame.setLayoutParams(new LinearLayout.LayoutParams(209,150));
		
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setBackgroundColor(Color.argb(255,255,0,0));
		imageView.setImageResource(R.drawable.cneutral_op1);
		
//		frame.setTranslationX(130);
		
		imageView.setPivotX(0f);
		imageView.setPivotY(0f);
		imageView.setRotation(-90f);
		imageView.setTranslationY(150);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(150,209));
//imageView.setLayoutParams(new FrameLayout.LayoutParams(150,209));
		
		Log.e("1", "width: " + imageView.getWidth() + " height:" + imageView.getHeight());
		//imageView.setLayoutParams(new LinearLayout.LayoutParams(209,150));
		
		//imageView.setAdjustViewBounds(true);
		
		//imageView.setLayoutParams(new RelativeLayout.LayoutParams(300, 418));
		//imageView.setLayoutParams(new RelativeLayout.LayoutParams(150, 108));
	}

	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestures, menu);
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

	
/*	
	// Detecting custom gestures using gesturelibrary (and gesturebuilder from sdk sample)
	// Activity implements OnGesturePerformedListener
	private GestureLibrary gestureLib;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_gestures);
		// Show the Up button in the action bar.
		setupActionBar();
		
	    GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
	    View inflate = getLayoutInflater().inflate(R.layout.activity_gestures, null);
	    gestureOverlayView.addView(inflate);
	    gestureOverlayView.addOnGesturePerformedListener(this);
	    
	    gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
	    if (!gestureLib.load()) 
	    {
	      finish();
	    }
	    setContentView(gestureOverlayView);
	}
	
	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) 
	{
		ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
		for (Prediction prediction : predictions) 
		{
			if (prediction.score > 4.0) 
			{
				Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
				.show();
			}
		}
	}
*/
}
