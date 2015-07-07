package com.caiyun.guzhang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.app.guzhang.R.id;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.StringUtils;

public class LoginActivity extends BaseActivity implements OnClickListener,
		OnFocusChangeListener {
	private Button login;
	private EditText idEdit;
	private EditText pwdEdit;
	private ImageView idClear;
	private ImageView pwdClear;
	private RequestQueue mQueue; // 网络请求队列
	private CustomProgressDialog progressDialog;
	private TextView getPasswd,regist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle(R.id.title, "登录");
		mQueue = Volley.newRequestQueue(this);// 网络请求队列
		initLoginView();// 初始化登陆框
	}

	private void initLoginView() {
		findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
		findViewById(R.id.layout_id).setEnabled(false);
		getPasswd = (TextView) findViewById(R.id.getPwd);
		regist = (TextView) findViewById(R.id.regist);
		login = (Button) findViewById(R.id.login_btn);
		idEdit = (EditText) findViewById(R.id.login_uid_input);
		idEdit.requestFocus();
		pwdEdit = (EditText) findViewById(R.id.login_passwd_input);
		idClear = (ImageView) findViewById(R.id.login_uid_clear);
		pwdClear = (ImageView) findViewById(R.id.login_passwd_clear);
		pwdEdit.setOnFocusChangeListener(this);
		pwdEdit.setOnFocusChangeListener(this);
		getPasswd.setOnClickListener(this);
		regist.setOnClickListener(this);
		idClear.setOnClickListener(this);
		pwdClear.setOnClickListener(this);
		login.setOnClickListener(this);

		if (idEdit.getText().toString().length() == 0) {
			idClear.setVisibility(View.GONE);
		}
		if (pwdEdit.getText().toString().length() == 0) {
			pwdClear.setVisibility(View.GONE);
		}
		idEdit.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!StringUtils.isNullOrBlanK(s.toString())) {
					idClear.setVisibility(View.VISIBLE);
				} else {
					idClear.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		pwdEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!StringUtils.isNullOrBlanK(s.toString())) {
					pwdClear.setVisibility(View.VISIBLE);
				} else {
					pwdClear.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case id.login_uid_clear:
			idEdit.setText("");
			break;
		case id.login_passwd_clear:
			pwdEdit.setText("");
			break;
		case id.login_btn:
			// login(idEdit.getText().toString(), pwdEdit.getText().toString(),
			// "3");// 登陆
			intent = new Intent(this, MainActivity.class);
			 this.startActivity(intent);
			 this.finish();
			break;
		case id.getPwd:
			 intent = new Intent(this, GetPwdActivity.class);
			 intent.putExtra("type", 0);
			 this.startActivity(intent);
			break;
		case id.regist:
			 intent = new Intent(this, GetPwdActivity.class);
			 intent.putExtra("type", 1);
			 this.startActivity(intent);
			break;
		}
	}

	/**
	 * 
	 * @param id
	 *            用户名
	 * @param pwd
	 *            密码
	 * 
	 */
	// private void login(final String id, final String pwd) {
	// // TODO Auto-generated method stub
	// if (!StringUtils.isPhone(id)) {
	// Toast.makeText(this, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
	// return;
	// }
	// if (!StringUtils.isGoodPWD(pwd)) {
	// Toast.makeText(this, "请输入正确的密码由6到16位数字或字母组成", Toast.LENGTH_LONG)
	// .show();
	// return;
	// }
	// progressDialog=startProgressDialog(progressDialog, this, "正在登陆...");
	// JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
	// UrlUtils.getHttpsUrl("/user/login", new String[] { "uname",
	// "passwd", "role" }, new String[] { id, pwd, role }),
	// null, new VolleyListerner(LoginActivity.this,progressDialog) {
	// @Override
	// public void onSucess(JSONObject response) {
	// // TODO Auto-generated method stub
	// AppUser user = JsonUtils.objectFromJson(
	// response.toString(), AppUser.class);
	// user.setType(3);
	// MainActivity.contextUser = user;
	// SaveDate.getInstence(LoginActivity.this).setUid(id);
	// SaveDate.getInstence(LoginActivity.this).setPwd(pwd);
	// SaveDate.getInstence(LoginActivity.this).setRole(role);
	//
	// LoginActivity.dismissDialog(progressDialog);
	// Intent intent = new Intent(LoginActivity.this,
	// MainActivity.class);
	// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	// LoginActivity.this.startActivity(intent);
	// LoginActivity.this.finish();
	// }
	//
	// @Override
	// public void onRet_0(JSONObject response) {
	// // TODO Auto-generated method stub
	// dismissDialog(progressDialog);
	// }
	// }, new VolleyErrorListoner(LoginActivity.this,progressDialog));
	// mQueue.add(jsonObjectRequest);
	// }

	public static CustomProgressDialog startProgressDialog(
			CustomProgressDialog progressDialog, Context context, String text) {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(context);
			progressDialog.setMessage(text);
		}
		progressDialog.show();
		return progressDialog;
	}

	public static void dismissDialog(CustomProgressDialog progressDialog) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch (v.getId()) {
		case R.id.login_uid_input:
			if (hasFocus) {
				findViewById(R.id.layout_id).setEnabled(false);
				findViewById(R.id.layout_pwd).setEnabled(true);
			} else {
				findViewById(R.id.layout_id).setEnabled(true);
				findViewById(R.id.layout_pwd).setEnabled(false);
			}
			break;
		case R.id.login_passwd_input:
			if (!hasFocus) {
				findViewById(R.id.layout_id).setEnabled(false);
				findViewById(R.id.layout_pwd).setEnabled(true);
			} else {
				findViewById(R.id.layout_id).setEnabled(true);
				findViewById(R.id.layout_pwd).setEnabled(false);
			}
			break;
		}
	}

	public void backClick(View v) {
		((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		this.finish();
	}
}
