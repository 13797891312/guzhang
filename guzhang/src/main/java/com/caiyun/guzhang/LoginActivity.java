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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.app.guzhang.R.id;
import com.caiyun.guzhang.fragment.Fragment1;
import com.caiyun.guzhang.util.MyAPP;
import com.caiyun.guzhang.util.SaveDate;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.StringUtils;

import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivity implements OnClickListener,
        OnFocusChangeListener {
    private Button login;
    private EditText idEdit;
    private EditText pwdEdit;
    private ImageView idClear;
    private ImageView pwdClear;
    private RequestQueue mQueue; // 网络请求队列
    private CustomProgressDialog progressDialog;
    private TextView getPasswd, regist;

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
                if (!StringUtils.isPhone(idEdit.getText().toString())) {
                    Toast.makeText(this, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!StringUtils.isGoodPWD(pwdEdit.getText().toString())) {
                    Toast.makeText(this, "请输入正确的密码由6到16位数字或字母组成", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                progressDialog = CustomProgressDialog.startProgressDialog(progressDialog, this, "正在登录...");
                login(this, mQueue, idEdit.getText().toString(), pwdEdit.getText().toString(), progressDialog);
                break;
            case id.getPwd:
                intent = new Intent(this, GetCodeActivity.class);
                intent.putExtra("type", 0);
                this.startActivity(intent);
                break;
            case id.regist:
                intent = new Intent(this, GetCodeActivity.class);
                intent.putExtra("type", 1);
                this.startActivity(intent);
                break;
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

    /**
     * 登录*
     */
    public static void login(final Context context, RequestQueue mQueue, final String id, final String pwd, CustomProgressDialog dialog) {
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("User_User_Login.mobile", new String[]{"mobile", "password"}, new String[]{id, pwd}), null, new VolleyListerner(context, dialog) {
            @Override
            public void onSucess(JSONObject response) throws Exception{
                super.onSucess(response);
                MyAPP.token=response.getJSONObject("data").getJSONObject("info").getString("token");
                SaveDate.getInstence(context).setUid(id);
                SaveDate.getInstence(context).setPwd(pwd);
                if (context instanceof LoginActivity) {
                    ((LoginActivity) context).finish();
                } else {
                    try {
                        ((Fragment1)(( (MainActivity)context).fragBaseFragments.get(1))).setIsLogin();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                JPushInterface.setAlias(context, id, new TagAliasCallback() {//设置jpush别名
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                    }
                });
            }
        }, new VolleyErrorListoner(context, dialog));

        mQueue.add(request);
    }
}
