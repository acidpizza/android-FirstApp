package com.example.myfirstapp;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class DragAndDrop extends Activity {

	private TextView option1, option2, option3, choice1, choice2, choice3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_and_drop);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//views to drag
		option1 = (TextView)findViewById(R.id.option_1);
		option2 = (TextView)findViewById(R.id.option_2);
		option3 = (TextView)findViewById(R.id.option_3);
		
		//views to drop onto
		choice1 = (TextView)findViewById(R.id.choice_1);
		choice2 = (TextView)findViewById(R.id.choice_2);
		choice3 = (TextView)findViewById(R.id.choice_3);
		
		//set touch listeners
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());
		
		//set drag listeners
		choice1.setOnDragListener(new ChoiceDragListener());
		choice2.setOnDragListener(new ChoiceDragListener());
		choice3.setOnDragListener(new ChoiceDragListener());
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
		getMenuInflater().inflate(R.menu.drag_and_drop, menu);
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

	
	private final class ChoiceTouchListener implements OnTouchListener 
	{
		public boolean onTouch(View view, MotionEvent motionEvent) 
		{
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) 
			{
			    //setup drag
				ClipData data = ClipData.newPlainText("", "");
//				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				
				Point offset = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
				DragShadowBuilder shadowBuilder = new DragShadowBuilderAtTouch(view, offset);
				//start dragging the item touched
				view.startDrag(data, shadowBuilder, view, 0);
			    return true;
			}
			else 
			{
			    return false;
			}
		}
	}
	
	private class ChoiceDragListener implements OnDragListener 
	{
		@Override
		public boolean onDrag(View v, DragEvent event) 
		{
		    //handle drag events
			switch (event.getAction()) 
			{
			case DragEvent.ACTION_DRAG_STARTED:
				//no action necessary
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				//no action necessary
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				//no action necessary
				break;
			case DragEvent.ACTION_DROP:
				//view dragged item is being dropped on
				TextView dropTarget = (TextView) v;
				
				//if an item has already been dropped here, there will be a tag
				Object tag = dropTarget.getTag();
				
				//if there is already an item here, set it back visible in its original place
				if(tag!=null)
				{
				    //the tag is the view id already dropped here
				    int existingID = (Integer)tag;
				    //set the original view visible again
				    findViewById(existingID).setVisibility(View.VISIBLE);
				}
				
				//view being dragged and dropped
				TextView dropped = (TextView) event.getLocalState();

				//set the tag in the target view to the ID of the view being dropped
				dropTarget.setTag(dropped.getId());

				//stop displaying the view where it was before it was dragged
				dropped.setVisibility(View.INVISIBLE);
				
				//update the text in the target view to reflect the data being dropped
				dropTarget.setText(dropped.getText());
				
				//make it bold to highlight the fact that an item has been dropped
				dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
				
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				//no action necessary
				break;
			default:
				break;
			}
			
		    return true;
		}
	}
	
	private final class DragShadowBuilderAtTouch extends View.DragShadowBuilder 
	{
		int x, y;
		
		// Defines the constructor for myDragShadowBuilder
		public DragShadowBuilderAtTouch(View v, Point offset) 
		{
			// Stores the View parameter passed to myDragShadowBuilder.
			super(v);
			x = offset.x;
			y = offset.y;
		}

		// Defines a callback that sends the drag shadow dimensions and touch point back to the
		// system.
		@Override
		public void onProvideShadowMetrics (Point size, Point touch)
		{
			// Defines local variables
			int width;
			int height;

			// Sets the width of the shadow to half the width of the original View
			width = getView().getWidth();

			// Sets the height of the shadow to half the height of the original View
			height = getView().getHeight();

			// Sets the size parameter's width and height values. These get back to the system
			// through the size parameter.
			size.set(width, height);

			// Sets the touch point's position to be in the middle of the drag shadow
			touch.set(x, y);
		}
		
	}
}
