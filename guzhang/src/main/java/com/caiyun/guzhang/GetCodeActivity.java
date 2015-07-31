package com.caiyun.guzhang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.app.guzhang.R.id;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.StringUtils;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class GetCodeActivity extends BaseActivity implements OnClickListener,
        OnFocusChangeListener {
    private Button next, getCode;
    private EditText idEdit;
    private EditText codeEdit;
    private ImageView idClear;
    private ImageView codeClear;
    private RequestQueue mQueue; // 网络请求队列
    private CustomProgressDialog progressDialog;
    /**
     * 0代表找回密码，1代表注册,2代表绑定手机*
     */
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpwd);
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            setTitle(R.id.title, "找回密码");
        } else if (type == 1) {
            setTitle(R.id.title, "注册");
        } else {
            setTitle(R.id.title, "绑定手机");
            findViewById(id.otherLayout).setVisibility(View.GONE);
            ((Button)(findViewById(R.id.login_btn))).setText("确认绑定");
        }
        mQueue = Volley.newRequestQueue(this);// 网络请求队列
        initLoginView();// 初始化登陆框
        initSMSSDK();
    }


    /**
     * 短信验证回调
     */

    /**
     * 由于第三方SDK会重复反馈，所以加个bool值判断下**
     */
    boolean isGetCode = false;//是否获取到
    boolean isSubmit = false;//是否验证过
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CustomProgressDialog.dismissDialog(progressDialog);
            switch (msg.what) {
                case 2:
                    if (!isGetCode) {
                        isGetCode = true;
                        Toast.makeText(GetCodeActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                        getCode.setEnabled(false);
                        set60S();
                    }
                    break;
                case 1:
                    if (!isSubmit) {
                        isSubmit = true;
                        if (type == 2) {//如果是绑定手机

                        } else {
                            Intent intent = new Intent(GetCodeActivity.this, GetPwd2Activity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("phone", idEdit.getText().toString());
                            GetCodeActivity.this.startActivity(intent);
                            GetCodeActivity.this.finish();
                        }
                    }
                    break;
                case 100:
                    Toast.makeText(GetCodeActivity.this, "短信验证失败", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(GetCodeActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    /**
     * 倒计时60秒**
     */
    public void set60S() {
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                getCode.setText(millisUntilFinished / 1000 + "秒后再次获取");
            }

            public void onFinish() {
                getCode.setText("获取验证码");
                getCode.setEnabled(true);
                isGetCode = false;
            }
        }.start();
    }

    /**
     * *初始化短信平台***
     */
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    hd.sendEmptyMessage(1);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    hd.sendEmptyMessage(2);
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                ((Throwable) data).printStackTrace();
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码
                    hd.sendEmptyMessage(100);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    hd.sendEmptyMessage(101);
                }
            }
        }
    };

    private void initSMSSDK() {
        try {
            SMSSDK.initSDK(this.getApplicationContext(), "87a1152cab0c", "bc36fa71bf30f2ac6bd7dc882d545f9b");
            SMSSDK.registerEventHandler(eh); //注册短信回调
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initLoginView() {
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findViewById(R.id.layout_id).setEnabled(false);
        getCode = (Button) findViewById(id.getCode);
        next = (Button) findViewById(R.id.login_btn);
        idEdit = (EditText) findViewById(R.id.login_uid_input);
        idEdit.requestFocus();
        codeEdit = (EditText) findViewById(R.id.login_passwd_input);
        idClear = (ImageView) findViewById(R.id.login_uid_clear);
        codeClear = (ImageView) findViewById(R.id.login_passwd_clear);
        codeEdit.setOnFocusChangeListener(this);
        codeEdit.setOnFocusChangeListener(this);
        idClear.setOnClickListener(this);
        codeClear.setOnClickListener(this);
        next.setOnClickListener(this);
        getCode.setOnClickListener(this);
        if (idEdit.getText().toString().length() == 0) {
            idClear.setVisibility(View.GONE);
        }
        if (codeEdit.getText().toString().length() == 0) {
            codeClear.setVisibility(View.GONE);
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
                // TODO Auto-generated method stub

            }
        });
        codeEdit.addTextChangedListener(new TextWatcher() {

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
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login_uid_clear:
                idEdit.setText("");
                break;
            case R.id.login_passwd_clear:
                codeEdit.setText("");
                break;
            case R.id.getCode:
                if (StringUtils.isPhone(idEdit.getText().toString())) {
                    SMSSDK.getVerificationCode("86", idEdit.getText().toString());
                    progressDialog = CustomProgressDialog.startProgressDialog(progressDialog, this, "正在获取...");
                } else {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.login_btn://下一步
                // login(idEdit.getText().toString(), pwdEdit.getText().toString(),
                // "3");//
                if (!StringUtils.isPhone(idEdit.getText().toString()) || codeEdit.getText().toString().length() < 4) {
                    Toast.makeText(this, "请输入正确的手机号或验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                SMSSDK.submitVerificationCode("86", idEdit.getText().toString(), codeEdit.getText().toString());
                progressDialog = CustomProgressDialog.startProgressDialog(progressDialog, this, "正在验证...");
                break;
        }
    }

    /**
     *
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
                .hideSoftInputFromWindow(GetCodeActivity.this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        this.finish();
    }
}
