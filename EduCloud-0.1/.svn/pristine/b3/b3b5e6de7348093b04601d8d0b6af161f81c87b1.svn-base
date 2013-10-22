package com.activity.educloud;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

	private Intent localIntent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		localIntent = new Intent(this, LoginActivity.class); 
		
		Timer timer=new Timer();
		TimerTask tast=new TimerTask()
		{
		   @Override
		   public void run(){
		   startActivity(localIntent);
		   }
		};
		timer.schedule(tast,3000);
	}

	//按返回键直接退出
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
