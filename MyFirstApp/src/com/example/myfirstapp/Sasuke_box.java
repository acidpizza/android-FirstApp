package com.example.myfirstapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Sasuke_box extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sasuke_box);
		// Show the Up button in the action bar.
		setupActionBar();
        setDrag();
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
		getMenuInflater().inflate(R.menu.sasuke_box, menu);
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

	public void setDrag()
	{
		ImageView box1 = (ImageView) findViewById(R.id.box1);
		box1.setOnDragListener(new Box1_DragListener());

		ImageView box2 = (ImageView) findViewById(R.id.box2);
		box2.setOnDragListener(new Box2_DragListener());

		ImageView sasukeImageView = (ImageView) findViewById(R.id.sasuke);
		sasukeImageView.setOnLongClickListener(new SasukeLongClickListener());

	}
	
	class SasukeLongClickListener implements OnLongClickListener
	{
		// Defines the one method for the interface, which is called when the View is long-clicked
		@Override
		public boolean onLongClick(View v) 
		{
			v.setVisibility(View.INVISIBLE);
			
			// Create a new ClipData.
			// This is done in two steps to provide clarity. The convenience method
			// ClipData.newPlainText() can create a plain text ClipData in one step.

			ClipData dragData = ClipData.newPlainText(String.valueOf(v.getTag()), "i reject this box!");
			
			// Create a new ClipData.Item from the ImageView object's tag
			//ClipData.Item item = new ClipData.Item(String.valueOf(v.getTag()));

			// Create a new ClipData using the tag as a label, the plain text MIME type, and
			// the already-created item. This will create a new ClipDescription object within the
			// ClipData, and set its MIME type entry to "text/plain"
			//ClipData dragData = new ClipData(String.valueOf(v.getTag()),new String[] {ClipDescription.MIMETYPE_TEXT_PLAIN},item);

			// Instantiates the drag shadow builder.
			//View.DragShadowBuilder myShadow = new MyDragShadowBuilder(sasukeImageView, getApplicationContext() );
			View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

			// Starts the drag
			v.startDrag(dragData,  // the data to be dragged
					myShadow,  // the drag shadow builder
					v,      // no need to use local data
					0          // flags (not currently used, set to 0)
					);
			
			
			
			
			return true;
		}
	};

	class Box1_DragListener implements OnDragListener 
	{	
		@Override
		public boolean onDrag(View v, DragEvent event)
		{
			int action = event.getAction();
			
			switch (action) 
			{
			case DragEvent.ACTION_DRAG_STARTED:
			{
				ImageView view = (ImageView) event.getLocalState();
				if(event.getClipDescription().getLabel().equals(String.valueOf(view.getTag())))
				{
					// Check if we accept the data
					// return false;
				}
				
				if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
				{
					// acceptable data  
					((ImageView) v).setColorFilter(Color.parseColor("#3f0000ff"));
					v.invalidate(); // force redraw of v
					return true;
				}
				else
				{
					// not acceptable data
					return false;
				}
			}
			case DragEvent.ACTION_DRAG_ENTERED:
				// expand box
				v.animate().scaleX(1.2f);
				v.animate().scaleY(1.2f);
				break;

			case DragEvent.ACTION_DRAG_EXITED:
				// box back to normal
				v.animate().scaleX(1.0f);
				v.animate().scaleY(1.0f);
				break;

			case DragEvent.ACTION_DROP:
				Toast.makeText(getApplicationContext(), String.valueOf(event.getClipData().getItemAt(0).getText()), Toast.LENGTH_SHORT).show();
				break;
			
			case DragEvent.ACTION_DRAG_ENDED:
				// box back to normal
				v.animate().scaleX(1.0f);
				v.animate().scaleY(1.0f);
				((ImageView) v).clearColorFilter();
				v.invalidate(); // force redraw of v

			default:
				break;
			}
			return true;
		}
	};

	class Box2_DragListener implements OnDragListener 
	{	
		boolean droppedHere;
		
		@Override
		public boolean onDrag(View v, DragEvent event) 
		{
			int action = event.getAction();
			
			switch (action) 
			{

			case DragEvent.ACTION_DRAG_STARTED:
				droppedHere = false;
				
				if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
				{					
					// acceptable data
					return true;
				}
				else
				{
					// not acceptable data
					return false;
				}

			case DragEvent.ACTION_DRAG_ENTERED:
				// expand 
				v.animate().scaleX(1.2f);
				v.animate().scaleY(1.2f);
				break;

			case DragEvent.ACTION_DRAG_EXITED:
				// box back to normal				
				v.animate().scaleX(1.0f);
				v.animate().scaleY(1.0f);
				break;

			case DragEvent.ACTION_DROP:
				droppedHere = true;
				Toast.makeText(getApplicationContext(), "Let me out!!!", Toast.LENGTH_SHORT).show();
				break;

			case DragEvent.ACTION_DRAG_ENDED:
				// box back to normal
				v.animate().scaleX(1.0f);
				v.animate().scaleY(1.0f);
				
				// Restore Sasuke
				if(!droppedHere)
				{
					final ImageView view = (ImageView) event.getLocalState();

					view.post(new Runnable() 
					{
						public void run() 
						{
							view.setVisibility(View.VISIBLE);
						}
					});
				}
				
			default:
				break;
			}
			return true;
		}
	};
	
}
