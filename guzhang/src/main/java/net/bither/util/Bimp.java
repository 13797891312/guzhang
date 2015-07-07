package net.bither.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.bither.util.NativeUtil;

import com.caiyun.guzhang.util.Cantent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bimp {

//	public static Bitmap revitionImageSize(String path) throws IOException {
//		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
//				new File(path)));
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeStream(in, null, options);
//		in.close();
//		int i = 0;
//		Bitmap bitmap = null;
//		while (true) {
//			if ((options.outWidth >> i <= 1000)
//					&& (options.outHeight >> i <= 1000)) {
//				in = new BufferedInputStream(
//						new FileInputStream(new File(path)));
//				options.inSampleSize = (int) Math.pow(2.0D, i);
//				options.inJustDecodeBounds = false;
//				bitmap = BitmapFactory.decodeStream(in, null, options);
//				break;
//			}
//			i += 1;
//		}
//		return bitmap;
//	}
	
//	/**
//	 * 
//	 * @param path
//	 * @return 压缩后的临时图片地址
//	 * @throws IOException
//	 */
//	public static String revitionImageSize1(String path) throws IOException {
//		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
//				new File(path)));
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		BitmapFactory.decodeStream(in, null, options);
//		in.close();
//		int i = 0;
//		Bitmap bitmap = null;
//		while (true) {
//			if ((options.outWidth >> i <= 1000)
//					&& (options.outHeight >> i <= 1000)) {
//				in = new BufferedInputStream(
//						new FileInputStream(new File(path)));
//				options.inSampleSize = (int) Math.pow(2.0D, i);
//				options.inJustDecodeBounds = false;
//				bitmap = BitmapFactory.decodeStream(in, null, options);
//				break;
//			}
//			i += 1;
//		}
//		return saveCroppedImage(bitmap);
//	}
	
	
	public static String revitionImageSize2(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		Bitmap bit=BitmapFactory.decodeStream(in, null, options);
		in.close();
		 File file = new File(Cantent.CACHEPATH);
	        if (!file.exists())
	            file.mkdir();
	        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".jpg";
	        // /sdcard/myFolder/temp_cropped.jpg
	        String newFilePath = Cantent.CACHEPATH + "/" +fileName;
		 NativeUtil.compressBitmap(bit, 40, newFilePath, true);
		 return newFilePath;
	}
//	
//	private static String saveCroppedImage(Bitmap bmp) {
//        File file = new File(Cantent.CACHEPATH);
//        if (!file.exists())
//            file.mkdir();
//        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".jpg";
//        // /sdcard/myFolder/temp_cropped.jpg
//        String newFilePath = Cantent.CACHEPATH + "/" +fileName;
//        file = new File(newFilePath);
//        try {
//            file.createNewFile();
//            FileOutputStream fos = new FileOutputStream(file);
//            bmp.compress(CompressFormat.JPEG, 80, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        bmp.recycle();
//		return newFilePath;
//    }
}
