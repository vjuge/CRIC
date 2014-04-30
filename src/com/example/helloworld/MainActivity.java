package com.example.helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
