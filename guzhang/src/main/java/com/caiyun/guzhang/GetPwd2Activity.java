package com.caiyun.guzhang;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.app.guzhang.R.id;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.StringUtils;

public class GetPwd2Activity extends BaseActivity implements OnClickListener,
		OnFocusChangeListener {
	private Button next;
	private EditText edit1;
	private EditText edit2;
	private ImageView idClear;
	private ImageView codeClear;
	private RequestQueue mQueue; // 网络请求队列
	private CustomProgressDialog progressDialog;
	/**0代表找回密码，1代表注册**/
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getpwd2);
		type=getIntent().getIntExtra("type", 0);
		if (type==0) {
			setTitle(R.id.title, "找回密码");
		}else {
			setTitle(R.id.title, "注册");
		}
		mQueue = Volley.newRequestQueue(this);// 网络请求队列
		initLoginView();// 初始化登陆框
	}

	private void initLoginView() {
		findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
		findViewById(R.id.layout_id).setEnabled(false);
		next = (Button) findViewById(R.id.login_btn);
		edit1 = (EditText) findViewById(R.id.login_uid_input);
		edit1.requestFocus();
		edit2 = (EditText) findViewById(R.id.login_passwd_input);
		idClear = (ImageView) findViewById(R.id.login_uid_clear);
		codeClear = (ImageView) findViewById(R.id.login_passwd_clear);
		edit2.setOnFocusChangeListener(this);
		edit2.setOnFocusChangeListener(this);
		idClear.setOnClickListener(this);
		codeClear.setOnClickListener(this);
		next.setOnClickListener(this);

		if (edit1.getText().toString().length() == 0) {
			idClear.setVisibility(View.GONE);
		}
		if (edit2.getText().toString().length() == 0) {
			codeClear.setVisibility(View.GONE);
		}
		edit1.addTextChangedListener(new TextWatcher() {
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
				// TODO Auto-generated method stub

			}
		});
		edit2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!StringUtils.isNullOrBlanK(s.toString())) {
					codeClear.setVisibility(View.VISIBLE);
				} else {
					codeClear.setVisibility(View.GONE);
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

		if (type==0) {
			setTitle(R.id.title, "找回密码");
			edit1.setHint("新密码");
			edit1.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
			edit2.setHint("确认新密码");
			edit2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
			next.setText("确认");
		}else {
			setTitle(R.id.title, "注册");
			edit1.setHint("昵称");
			edit1.setInputType(InputType.TYPE_DATETIME_VARIATION_NORMAL);
			edit2.setHint("设置密码");
			edit2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
			next.setText("确认注册");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case id.login_uid_clear:
			edit1.setText("");
			break;
		case id.login_passwd_clear:
			edit2.setText("");
			break;
		case id.login_btn://下一步
			// login(idEdit.getText().toString(), pwdEdit.getText().toString(),
			// "3");//
			break;
		}
	}

	/**
	 *
	 * @param //id
	 *            用户名
	 * @param //pwd
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
				.hideSoftInputFromWindow(GetPwd2Activity.this.getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		this.finish();
	}
}