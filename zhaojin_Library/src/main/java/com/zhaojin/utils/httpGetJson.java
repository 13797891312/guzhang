package com.zhaojin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

public abstract class httpGetJson extends AsyncTask<String, Integer, Integer> {
	static final int SUCESS_STATE=1;
	static final int FIELD_STATE=-1;
	static final int NONET_STATE=0;
	private String json;
	private Context context;
	private String url;

	public httpGetJson(String url ,Context context) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.context=context;
		this.execute(url);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		httpGetStart();
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
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(30000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String line = "";
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			json=sb.toString();
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
		if (state==SUCESS_STATE) {
			httpGetSucess(state, json);
		}else{
			httpGetField(state);
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
	 * ��ʼ֮ǰ
	 */
	public abstract void httpGetStart();
	/**
	 * 
	 * @param state 0无网络 -1请求失败
	 */
	public abstract void httpGetField(Integer state);

	/**
	 *
	 * @param json
	 *            返回的字符串数据
	 */
	public abstract void httpGetSucess(Integer state, String json);
}
