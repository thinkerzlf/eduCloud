package com.upload.educloud;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.activity.educloud.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class FileForderActivity extends Activity implements OnFileBrowserListener
{

	@Override
	public void onDirItemClick(String path)
	{

	}

	@Override
	public void onFileItemClick(String filename)
	{	
		String uploadUrl = "http://121.195.169.234:8080/sunrise_cloud/upload.action";
		UpLoadFile upload = new UpLoadFile();
		String parameter = "";
		//文件上传
		new UpLoadFile().doUpLoadStruct(this, uploadUrl, filename);
//		String uploadUrl = "http://localhost/upload/UploadServlet";
//		UpLoadFile upload = new UpLoadFile();
//		String parameter = "";
//		//文件上传
//		new UpLoadFile().doUpLoad(this, uploadUrl, filename, parameter);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_forder);
		FileBrowser fileBrowser = (FileBrowser) findViewById(R.id.filebrowser);
		fileBrowser.setOnFileBrowserListener(this);
	}
}