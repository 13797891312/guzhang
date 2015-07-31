package com.caiyun.guzhang;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.util.Cantent;
import com.caiyun.guzhang.view.CircleImageView;
import com.zhaojin.activity.BaseActivity;

import java.io.File;

/**
 * Created by Administrator on 2015/7/9. 更换头像
 */
public class ChangeImageActivity extends BaseActivity implements View.OnClickListener{
    private Button button_pz,button_xc;
    private CircleImageView headerIcon;
    private TextView txt_submit;
    File mOutputFile;
    Bitmap bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeimage);
        setTitle(R.id.title, "更换头像");
        findView();
    }

    private void findView() {
        headerIcon = (CircleImageView) findViewById(R.id.iv);
        button_pz = (Button) findViewById(R.id.button_pz);
        button_xc = (Button) findViewById(R.id.button_xc);
        txt_submit = (TextView) findViewById(R.id.txt_submit);
        txt_submit.setOnClickListener(this);
        button_xc.setOnClickListener(this);
        button_pz.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String sdPath = Cantent.CACHEPATH;
        mOutputFile = new File(sdPath, System.currentTimeMillis() + ".jpg");
        if (v.getId()==R.id.button_pz) {
            // ##########拍照##########
            Intent newIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            newIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(mOutputFile));
            startActivityForResult(newIntent, Cantent.REQUST_CAMARE);
        }else if (v.getId()==R.id.button_xc) {
            // ######### 调到图片选择界面##########
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, Cantent.REQUST_PHOTOSELECT);
        }else if (v.getId()==R.id.txt_submit) {
            this.finish();
        }
    }

    // 剪切界面
    private void cropBitmap(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true"); // 开启剪裁
        intent.putExtra("aspectX", 1); // 宽高比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150); // 宽高
        intent.putExtra("outputY", 150);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mOutputFile));
        startActivityForResult(intent, Cantent.REQUST_CLIP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Cantent.REQUST_PHOTOSELECT) {
            try {
                Uri uri = data.getData();
                if (uri!=null) {
                    cropBitmap(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (requestCode == Cantent.REQUST_CLIP) {
            if (bm!=null&&!bm.isRecycled()) {
                bm.recycle();
                bm=null;
            }
            bm = BitmapFactory.decodeFile(mOutputFile.getAbsolutePath());
            if (bm!=null&&bm.getHeight()>=15) {
                headerIcon.setImageBitmap(bm);
//                imageData=new ImageData(mOutputFile.getAbsolutePath(), true);
//                postFile(imageData.getUri());
            }
        }else if (requestCode == Cantent.REQUST_CAMARE) {
            Uri uri=Uri.fromFile(mOutputFile);
            if (uri!=null&&mOutputFile.length()>=10) {
                cropBitmap(Uri.fromFile(mOutputFile));
            }
        }
    }
}
