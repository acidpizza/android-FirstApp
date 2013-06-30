package com.example.myfirstapp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment1 extends Fragment
{	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	  {
	    View view = inflater.inflate(R.layout.fragment1, container, false);
	    return view;
	  }
	  
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState)
	  {
		  super.onActivityCreated(savedInstanceState);
		  if(savedInstanceState!=null)
		  {
			  String savedText = savedInstanceState.getString("HEADTEXT");
			  Log.e("1",savedText);		
			  ((TextView)getActivity().findViewById(R.id.fragment1Text)).setText(savedText);
		  }
	  }

	@Override
	  public void onSaveInstanceState(Bundle savedInstanceState) 
	  {
		  super.onSaveInstanceState(savedInstanceState);
		  String savedText = ((TextView)getActivity().findViewById(R.id.fragment1Text)).getText().toString();
		  Log.e("1",savedText);
		  savedInstanceState.putString("HEADTEXT", savedText);
	  }
	  
	  public void changeText(String text)
	  {
		  TextView textView = (TextView) getActivity().findViewById(R.id.fragment1Text);
		  textView.setText(text);
	  }

	
}
