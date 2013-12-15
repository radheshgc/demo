package com.example.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class AlertBox 
{
	public static AlertDialog.Builder newAlert(final Context caller,String header,String body,String ok)
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder((Context) caller);
		dlgAlert.setMessage(body);
		dlgAlert.setTitle(header);
		dlgAlert.setPositiveButton(ok, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which)
	        {
	        	
	        }
	      });
		dlgAlert.setCancelable(true);
		return dlgAlert;
		
	}
}
