package com.zhaojin.myviews;



import com.example.mylibrary.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
 
 
/********************************************************************
 
 *******************************************************************/
 
public class CustomProgressDialog extends Dialog {
    private static CustomProgressDialog customProgressDialog = null;
     
    public CustomProgressDialog(Context context){
        super(context);
    }
     
    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }
     
    public static CustomProgressDialog createDialog(Context context){
        customProgressDialog = new CustomProgressDialog(context,R.style.CustomProgressDialog1);
        customProgressDialog.setContentView(R.layout.customprogressdialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
         
        return customProgressDialog;
    }
  
    public void onWindowFocusChanged(boolean hasFocus){
         
        if (customProgressDialog == null){
            return;
        }
         
        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }
  
    /**
     *
     * [Summary]
     *       setTitile 标题
     * @param strTitle
     * @return
     *
     */
    public CustomProgressDialog setTitile(String strTitle){
        return customProgressDialog;
    }
     
    /**
     *
     * [Summary]
     *       setMessage 提示内容
     * @param strMessage
     * @return
     *
     */
    public CustomProgressDialog setMessage(String strMessage){
        TextView tvMsg = (TextView)customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
         
        if (tvMsg != null){
            tvMsg.setText(strMessage);
        }
         
        return customProgressDialog;
    }
    
    public static CustomProgressDialog startProgressDialog(CustomProgressDialog progressDialog,Context context,String text) {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(context);
		}
        progressDialog.setMessage(text);
        progressDialog.show();
		return progressDialog;
	}
    
    public static void dismissDialog(CustomProgressDialog progressDialog) {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}
}