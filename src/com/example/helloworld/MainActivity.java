package com.example.helloworld;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		TextView textH = null;
		String hello = null;
		super.onCreate(savedInstanceState);
	    
		hello = getResources().getString(R.string.hello_world);
		hello = hello.replace("world", "les zéros");
		
	    textH = new TextView(this);
	    textH.setText(hello);
	    setContentView(textH);

	    
	  }
	
    }
