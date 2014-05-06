package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button bcalcIMC, grosButton, bRAZ = null;
	TextView resultIMC = null;
	EditText edTaille, edPoids = null; 

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		bcalcIMC = (Button) findViewById(R.id.butCalcIMC);
		bRAZ = (Button) findViewById(R.id.butRAZ);
		resultIMC = (TextView) findViewById(R.id.imcResult);
		edPoids = (EditText) findViewById(R.id.editPoids);
		edTaille = (EditText) findViewById(R.id.editTaille);
//		grosButton = (Button) findViewById(R.id.grosBoutton);
//
//		b1.setOnTouchListener(touchListenerButton1);
//		b1.setOnClickListener(clickListenerButton1);
//
//		grosButton.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				// return false;
//				float posx = event.getX();
//				float posy = event.getY();
//				int width = grosButton.getWidth();
//				int height = grosButton.getHeight();
//				grosButton.setTextSize(Math.abs(posx - width / 2)
//						+ Math.abs(posy - height / 2));
//				return true;
//				// return onTouch(v, event);
//			}
//		});

		
		bcalcIMC.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//resultIMC
				float calcRes, t, p;
				t = Float.valueOf(edTaille.getText().toString());
				p = Float.valueOf(edPoids.getText().toString());
				calcRes = p / (float)Math.pow(t, 2);
				resultIMC.setText(String.valueOf(calcRes));
			}
		});
	}
	
}
