package com.activity.educloud;

import java.util.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.file.educloud.File;
import com.file.educloud.fileFactory;

public class FileActivity extends Activity {
    static final String TAG ="FileActivity";

	private ListView mListView;
    private FileListAdapter filelistadapter;
    private List<File> files;
    private String rootUuid;                     //用户根目录 uuid
    private String curUuid;                       //用户当前目录 uuid
    private FileActivity act;
    /**
     * 初始化文件数据
     */
    private void initFileData() {
        act = this;
        rootUuid = "root";
        curUuid = rootUuid;
        List<File> files = new ArrayList<File>();
        //测试模拟文件
        int i = 0;
        for (i = 0; i < 6; i++) {
            File f = new File("", 0, i + "", R.drawable.documnet, R.drawable.not_selected, "u1");
            files.add(f);
            Log.i(TAG, f.toString());
        }
        this.files = files;
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);


        initFileData();   //初始化数据
        initUI();
	}

    private void initUI() {
        mListView = (ListView) findViewById(R.id.ltv_file);
        filelistadapter = new FileListAdapter(this, files); //创建适配器
        mListView.setAdapter(filelistadapter);
    }

    class FileListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;  
        private Context mContext = null;
        private android.widget.Spinner fileselect = null;
        private List<File> mlistItems;
        public FileListAdapter(Context context,List<File> lt) {
            mContext = context;  
            mlistItems = lt;
            mInflater = LayoutInflater.from(mContext);  
        }  
        public Object getItem(int arg0) {  
            // TODO Auto-generated method stub  
            return null;  
        }  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return 0;  
        }  
        public int getCount() {  
            // TODO Auto-generated method stub  
            return mlistItems.size();  
        }  
        public View getView(final int position, View convertView,
                android.view.ViewGroup parent) {  
            final ImageView indexImage;  
            final TextView indexText;
            final ImageButton indexButton;
            if (convertView == null) {  
                // 和item_custom.xml脚本关联  
                convertView = mInflater.inflate(R.layout.listview_fileitem, null);  
            }  
            indexImage = (ImageView) convertView.findViewById(R.id.imv_filetype);  
            indexText = (TextView) convertView.findViewById(R.id.tv_filename); 
            indexButton = (ImageButton)convertView.findViewById(R.id.imbtn_select); 
            
            indexText.setText(mlistItems.get(position).getName());
            //indexText.setTextColor(Color.RED);    
            indexImage.setBackgroundResource(mlistItems.get(position).getImgId());
            indexButton.setBackgroundResource(mlistItems.get(position).getBtnImgId());

            indexImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.i(TAG,mlistItems.get(position).toString());
                    File f = mlistItems.get(position);
                    if(f.getType()==0){                     //如果点击了目录 则进入目录    首个目录为返回

                        goinCategroy(f);
                    }
                }
            });
            indexButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(FileActivity.this)
					.setTitle("文件选项")
	                .setMultiChoiceItems(new String[] {"删除","共享","重命名"}, null, null)
	                .setPositiveButton("确定", null)
	                .setNegativeButton("取消", null)
	                .show();
				}
            	
            });
            
            indexButton.setOnTouchListener(new View.OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						indexButton.setBackgroundResource(R.drawable.selected);
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						indexButton.setBackgroundResource(R.drawable.not_selected);
					}
					return false;
				}
            	
            });
            return convertView;  
        }  
    }


    private void goinCategroy(File fl) {
        files.clear();
        if(fl.getUuid()!=rootUuid){      //如果点击的不为根目录则添加返回文件夹
            files.add(new File(curUuid, 0, "返回上级...", R.drawable.documnet, R.drawable.not_selected, "u1"));
        }
        curUuid = fl.getUuid();
        //此处需要发送请求 查询进入 目录下的所有文件信息


        //测试模拟文件
        int i = 0;
        for (i = 0; i < 3; i++) {
            File f = new File(i+"", 0, i + "tttt", R.drawable.documnet, R.drawable.not_selected, "u1");
            files.add(f);
            Log.i(TAG, f.toString());
        }
        mListView = (ListView) findViewById(R.id.ltv_file);
        filelistadapter = new FileListAdapter(this, files); //创建适配器
        mListView.setAdapter(filelistadapter);
    }

}
