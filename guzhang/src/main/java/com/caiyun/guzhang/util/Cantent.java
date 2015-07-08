package com.caiyun.guzhang.util;

import android.os.Environment;

public class Cantent {
	public static final int VIEWPAGER_ID_GUIDE=100;
	public static final int VIEWPAGER_ID_TRANSACITION=101;
	public static final int VIEWPAGER_ID_RANK=102;
	public static final int VIEWPAGER_ID_MYSTOCK=103;
	public static final int VIEWPAGER_ID_NEWS=104;
	public static final int VIEWPAGER_ID_NOTICE=105;
	public static final String CACHEPATH=Environment.getExternalStorageDirectory()
			.getAbsolutePath()+"/guzhang";
	public static final String SDCARD=Environment.getExternalStorageDirectory()
			.getAbsolutePath();
}
