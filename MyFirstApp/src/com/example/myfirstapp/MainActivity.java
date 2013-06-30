package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity 
{	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
  
    public void startSasukeBox(View view) 
    {
    	Intent intent = new Intent(this, Sasuke_box.class);
    	startActivity(intent);
    }
    
    public void startQuestionAnswer(View view) 
    {	
    	Intent intent = new Intent(this, QuestionAnswer.class);
    	startActivity(intent);
    }
    
    public void startDragAndDrop(View view) 
    {	
    	Intent intent = new Intent(this, DragAndDrop.class);
    	startActivity(intent);
    }
    
    public void startFragments(View view) 
    {	
    	Intent intent = new Intent(this, Fragments.class);
    	startActivity(intent);
    }
    
    public void startGestures(View view) 
    {	
    	Intent intent = new Intent(this, Gestures.class);
    	startActivity(intent);
    }
}

