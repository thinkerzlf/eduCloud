package com.activity.educloud;

import android.widget.ImageView;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;

//引导页面，第一次进入时引导用户使用的页面
public class GuideActivity extends Activity implements OnGestureListener{

	private static final int FLING_MIN_DISTANCE = 100;  
    private ViewFlipper flipper;  
    private GestureDetector detector; 
    private static int count = 0;
    private SharedPreferences preferences; //页面标识符
	private Intent localIntent = null;
    
  //放照片的ID号
    private int[] slideImageIDs = {
            R.drawable.guide1,
            R.drawable.guide2
           						};
    //放照片对象
    private ImageView[] slideImages = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		preferences = getSharedPreferences("count",MODE_WORLD_READABLE);
        int count = preferences.getInt("count", 0);
        Editor editor = preferences.edit();
              
        //不是第一次进入跳转到欢迎页
        if(count != 0)
        {
        	localIntent = new Intent(this, WelcomeActivity.class); 
        	startActivity(localIntent);
        	
        }
        editor.putInt("count", ++count);
        //提交修改
        editor.commit();
 
		setContentView(R.layout.activity_guide);
		
		detector = new GestureDetector(this,this);
        flipper = (ViewFlipper) findViewById(R.id.vfp_guidepic);  
        
        slideImages = new ImageView[slideImageIDs.length];
        for(int i = 0;i < slideImageIDs.length;i++)
        {
        	slideImages[i] = new ImageView(this);  
        	slideImages[i].setBackgroundResource(slideImageIDs[i]);  
            flipper.addView(slideImages[i]);  
        }
		
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return this.detector.onTouchEvent(event);
	}


	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && count < slideImages.length-1) { 
			//设置View进入和退出的动画效果 
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, 
			R.anim.left_in)); 
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 
			R.anim.left_out)); 
			this.flipper.showNext(); 
			count++;
			//到最后一个页面的时候可以响应点击事件
			if(this.flipper.getCurrentView() == slideImages[slideImages.length-1])
			{
				this.flipper.setOnClickListener(new OnClickListener(){  

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub    
		            	Intent tmpIntent = new Intent(GuideActivity.this,LoginActivity.class); 
		        		startActivity(tmpIntent);  
					}  
		              
		        }); 
			}
			return true;
		}
		
		if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE && count > 0) { 
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this, 
			R.anim.right_in)); 
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 
			R.anim.right_out)); 
			this.flipper.showPrevious(); 
			count--;
			return true; 
		}
		
		return false;
	}
	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
