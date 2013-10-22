package com.upload.educloud;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class UpLoadFile {
	public static boolean doUpLoadStruct(Activity activity, String server_url, String file_name){
		try{
			HttpClient httpClient = new DefaultHttpClient(); 
			httpClient.getParams().setParameter( CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1 );
			HttpPost httpPost = new HttpPost(server_url);

//			httpPost.addHeader("charset", HTTP.UTF_8);

			MultipartEntity postEntity = new MultipartEntity();

			// 字符用StringBody
			ContentBody cbUserName = new StringBody( "ggyy" );
			ContentBody cbPassWord = new StringBody( "密码" );
			ContentBody cbSavePath = new StringBody( "ggyy" );
			String fileName = "newUpload";//文件名.后缀名
			ContentBody cbFileName = new StringBody( fileName );

			// 文件用FileBody，并指定文件类型
//			File file = new File( "文件的绝对路径");
			File file = new File(file_name);
			String length = file.getPath();
			ContentBody cbFileData = new FileBody(file, "image/jpg" );

			// 把上面创建的这些Body全部加到Entity里面去。
			// 注意他们的key，这些key在Struts2服务器端Action的代码里必须保持一致！！
			postEntity.addPart( "userName_ad", cbUserName );
//			postEntity.addPart( "passWord", cbPassWord );
			postEntity.addPart( "uploadFileName", cbFileName );
			postEntity.addPart( "upload", cbFileData );
			postEntity.addPart("savePath", cbSavePath);

			httpPost.setEntity( postEntity );
			// 下面这句话就把数据提交到服务器去了
			HttpResponse response = httpClient.execute( httpPost );
			// 打开response的数据流，就可以读取服务器端的回执数据
			InputStream reader = response.getEntity().getContent();
			Toast.makeText(activity, "传送成功", Toast.LENGTH_LONG).show();
		}catch (Exception e){
			Toast.makeText(activity, "很抱歉，你所在网络不是教育网", Toast.LENGTH_LONG).show();
		}
		return true;
	}
	//文件名，服务器Url，参数
	public static boolean doUpLoad(Activity activity, String file_name, String server_url, String parameter){
		String filename = file_name;
		String uploadUrl = server_url;
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		try
		{
			URL url = new URL(uploadUrl);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			
			DataOutputStream dos = new DataOutputStream(httpURLConnection
					.getOutputStream());
			dos.writeBytes(twoHyphens + boundary + end);
			dos
					.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
							+ filename.substring(filename.lastIndexOf("/") + 1)
							+ "\"" + end);
			dos.writeBytes(end);

			FileInputStream fis = new FileInputStream(filename);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			while ((count = fis.read(buffer)) != -1)
			{
				dos.write(buffer, 0, count);

			}
			fis.close();

			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
			dos.flush();

			InputStream is = httpURLConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String result = br.readLine();

			Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
			dos.close();
			is.close();
			
		}
		catch (Exception e)
		{
			activity.setTitle(e.getMessage());
		}

		return true;
	}
}
