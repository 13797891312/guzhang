package com.caiyun.guzhang.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.caiyun.guzhang.MainActivity;
import com.zhaojin.utils.LogUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

public abstract class UploadUtil extends AsyncTask<String, Integer, Integer> {
	static final int SUCESS_STATE = 1;
	static final int FIELD_STATE = -1;
	static final int NONET_STATE = 0;
	private String json;
	private Context context;
	private String url;
	private String path;

	/**
	 * 
	 * @param url
	 *            上传地址
	 * @param path
	 *            文件路径
	 * @param context
	 *            上下文
	 */
	public UploadUtil(String url, String path, Context context) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.context = context;
		this.execute(url);
		this.path = path;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		httpPostStart();
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		if (context == null) {
			this.cancel(true);
		}
		if (!isNetwork(context)) {
			return 0;
		}
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// 设置通信协议版本
			httpclient.getParams().setParameter(
					CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			httpclient.getParams().setParameter(
					CoreProtocolPNames.HTTP_CONTENT_CHARSET, "utf-8");
			ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE,
					HTTP.UTF_8);

			HttpPost httppost = new HttpPost(url);
			File file = new File(path);

			FileBody fileBody;
			fileBody = new FileBody(file);
			MultipartEntity mpEntity = new MultipartEntity(); // 文件传输

			mpEntity.addPart("file", fileBody); // 对应的
//			mpEntity.addPart("uid",
//					new StringBody(MainActivity.contextUser.getUid(),
//							contentType));//添加文本参数
//			mpEntity.addPart("token",
//					new StringBody(MainActivity.contextUser.getToken(),
//							contentType));//添加文本参数
//			mpEntity.addPart("unit_code", new StringBody(
//					MainActivity.contextUser.getUnitId(), contentType));//添加文本参数
			httppost.setEntity(mpEntity);

			System.out
					.println("executing request " + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			System.out.println(response.getStatusLine());// 通信Ok

			if (resEntity != null) {
				json = EntityUtils.toString(resEntity, "utf-8");
			}
			LogUtils.e("json", json + "****");
			httpclient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return FIELD_STATE;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return FIELD_STATE;
		}
		return SUCESS_STATE;
	}

	

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Integer state) {
		// TODO Auto-generated method stub
		super.onPostExecute(state);
		if (state == SUCESS_STATE) {
			httpPostSucess(state, json);
		} else {
			httpPostField(state);
		}
	}

	/***** 是否有可用网络 ****/
	public static boolean isNetwork(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {

			return false;
		}
		if (connectivityManager.getActiveNetworkInfo() == null) {
			return false;
		}
		return connectivityManager.getActiveNetworkInfo().isAvailable();
	}

	/**
	 * 开始上传
	 */
	public void httpPostStart() {
	};

	/**
	 * 
	 * @param state
	 *            0无网络 -1请求失败
	 */
	public abstract void httpPostField(Integer state);

	/**
	 * 
	 * @param json
	 *            返回的字符串数据
	 */
	public abstract void httpPostSucess(Integer state, String json);
}
