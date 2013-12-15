package com.example.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public static final String MANUAL = "MANUAL";
	public static final String SECTION = "SECTION";
	public static final String CONTEXT = "CONTEXT";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	public void fetchMan(View view)
	{
		String manual = null;
		String section = "";
		Intent intent = new Intent(this, DisplayPage.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		CharSequence message = editText.getText();
		String[] split;
		split = message.toString().split("\\s+");
		if(split.length <=2 && message.length()!=0)
		{
				manual = split[0];
				if(split.length == 2)
				{
					section = split[1];
				}
				intent.putExtra(MANUAL, manual);
				intent.putExtra(SECTION, section);
				
				startActivity(intent);
		}
		else
		{
			AlertDialog.Builder dlgAlert  = AlertBox.newAlert(this, "Problem!", "Invalid manual option", "OK");
			dlgAlert.create().show();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
