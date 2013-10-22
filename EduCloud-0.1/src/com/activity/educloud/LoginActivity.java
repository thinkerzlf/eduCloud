package com.activity.educloud;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity {

	private Intent localIntent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		localIntent = new Intent(this, MainActivity.class); 
		
		Timer timer=new Timer();
		TimerTask tast=new TimerTask()
		{
		   @Override
		   public void run(){
		   startActivity(localIntent);
		   }
		};
		timer.schedule(tast,1000);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		//super.finish();
		MyApplication mApp = (MyApplication)getApplication();
		if (mApp.isExit()) {
			int pid = android.os.Process.myPid();
			android.os.Process.killProcess(pid);
			finish();
		}
	}
	
	
}
