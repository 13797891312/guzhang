package com.caiyun.guzhang.view;

import java.util.Map;
import java.util.Set;

import com.caiyun.app.guzhang.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners.UMAuthListener;
import com.umeng.socialize.controller.listener.SocializeListeners.UMDataListener;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OtherLoginLayout extends LinearLayout implements
        View.OnClickListener {
    private Context context;
    private ImageView qq, weibo, weixin;
    private UMSocialService mController;

    public OtherLoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public OtherLoginLayout(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public void initView() {
        if (!isInEditMode()) {
            mController = UMServiceFactory
                    .getUMSocialService("com.umeng.login");
            this.addView(View.inflate(this.getContext(),
                    R.layout.includ_otherlogin, null));
            qq = (ImageView) this.findViewById(R.id.qq);
            weibo = (ImageView) this.findViewById(R.id.weibo);
            weixin = (ImageView) this.findViewById(R.id.weixin);
            qq.setOnClickListener(this);
            weibo.setOnClickListener(this);
            weixin.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qq:
                //参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
                UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) context, "1104707878",
                        "wVeFeVjXioAJjg3z");
                qqSsoHandler.addToSocialSDK();
                otherLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.weibo:
                otherLogin(SHARE_MEDIA.SINA);
                break;
            case R.id.weixin:
                // 添加微信平台
                UMWXHandler wxHandler = new UMWXHandler(context, "appId", "appSecret");
                wxHandler.addToSocialSDK();
                otherLogin(SHARE_MEDIA.WEIXIN);
                break;
        }
    }

    public void otherLogin(final SHARE_MEDIA type) {
        mController.doOauthVerify(this.getContext(), type,
                new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                        Toast.makeText(context, "授权开始", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onError(SocializeException e,
                                        SHARE_MEDIA platform) {
                        Toast.makeText(context, "授权错误", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onComplete(Bundle value, SHARE_MEDIA platform) {
                        Toast.makeText(context, "授权完成", Toast.LENGTH_SHORT)
                                .show();
                        getInfo(type);

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(context, "授权取消", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    public void getInfo(SHARE_MEDIA type) {
        // 获取相关授权信息
        mController.getPlatformInfo(context, type, new UMDataListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onComplete(int status, Map<String, Object> info) {
                if (status == 200 && info != null) {
                    StringBuilder sb = new StringBuilder();
                    Set<String> keys = info.keySet();
                    for (String key : keys) {
                        sb.append(key + "=" + info.get(key).toString() + "\r\n");
                    }
                    Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG)
                            .show();
                } else {
                    Log.d("TestData", "发生错误：" + status);
                }
            }
        });
    }
}
