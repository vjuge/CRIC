package com.eugenegames.cric;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eugenegames.cric.Util.IpCalculator;

/**
 * @author 323145
 * 
 */
public class MainActivity extends Activity {

	private static Button goButton = null;
	private static EditText edIPInput, edIPMask = null;
	private static TextView tIPresult = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.cric);

		goButton = (Button) findViewById(R.id.goButton);
		edIPInput = (EditText) findViewById(R.id.editIPInput);
		edIPMask = (EditText) findViewById(R.id.editIpMask);
		tIPresult = (TextView) findViewById(R.id.textIPResult);

		TextWatcherIPandMask twIPandMaskValid = new TextWatcherIPandMask(
				goButton);

		edIPInput.addTextChangedListener(twIPandMaskValid);

		edIPMask.addTextChangedListener(twIPandMaskValid);

		goButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				IpCalculator ipcalc = new Util.IpCalculator(edIPInput.getText()
						.toString(), edIPMask.getText().toString());
				affiche(ipcalc);

			}
		});
	}

	private void affiche(IpCalculator ipcalc) {
		tIPresult.setText("adresse du réseau : " + ipcalc.getNetworkAddress()
				+ "\n" + "adresse broadcast : " + ipcalc.getBroadCast() + "\n"
				+ "masque de sous-réseau : " + ipcalc.getMask() + "\n"
				+ "masque inverse (wildcard): " + ipcalc.getWildcard() + "\n"
				+ "nombre de machines : "
				+ String.valueOf(ipcalc.getNumOfMachines()) + "\n" + ""

		);
	}

	private class TextWatcherIPandMask implements TextWatcher {

		private final Button but;

		public TextWatcherIPandMask(Button b) {
			but = b;
		}

		@Override
		public void afterTextChanged(Editable s) {
			boolean status = Util.isIPMask(edIPMask.getText().toString())
					&& Util.isIPAddress(edIPInput.getText().toString());
			but.setEnabled(status);

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}
	}

}
