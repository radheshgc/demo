package com.example.demo;


import java.io.IOException;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayPage extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		String headPath = "file:///android_asset/";
		setContentView(R.layout.activity_display_page);
		Intent intent = getIntent();
		String manual;
		String section;
		String path;
		manual = intent.getStringExtra(MainActivity.MANUAL);
		section = intent.getStringExtra(MainActivity.SECTION);
		if(section.equals(""))
		{
			try
			{
				section = findSection(manual);
			}
			catch (IllegalArgumentException e)
			{
				showError();
				
				
			}
		}
		path = headPath +"htmlman" + section+"/"+manual+"."+ section + ".html";			
		final WebView mywebview = (WebView) findViewById(R.id.webview);
		mywebview.loadUrl(path);
		mywebview.setWebViewClient(new WebViewClient() 
		{
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
            	mywebview.loadUrl("file:///android_asset/err.html");
            	showError();
            	
            }
        });
	}
	private String findSection(String manual) throws IllegalArgumentException
	{
		String section = null;
		for(int i = 1; i<=8; i++)
		{
			String tailPath = "htmlman" +Integer.toString(i)+"/"+manual+"."+ Integer.toString(i) + ".html";
			try 
			{
				AssetManager mg = getResources().getAssets();
				mg.open(tailPath);
				section = Integer.toString(i);
				break;
			} catch (IOException ex)
			{
				continue;
			}			
			
		}
		if(section == null)
		{
			throw new IllegalArgumentException();
		}
		return section;
	}
	private void showError()
	{
		
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
		dlgAlert.setMessage("No such manual page exists!");
		dlgAlert.setTitle("Problem!");
		dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which)
	        {
	        	finish();
	        }
	      });
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_page, menu);
		return true;
	}
	

}
