/*
 * Copyright 2014 http://Bither.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bither.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class NativeUtil {
	private static int DEFAULT_QUALITY = 95;

	public static void compressBitmap(Bitmap bit, String fileName,
			boolean optimize) {
		compressBitmap(bit, DEFAULT_QUALITY, fileName, optimize);

	}

	public static void compressBitmap(Bitmap bit, int quality, String fileName,
			boolean optimize) {
		Log.d("native", "compress of native");

		// if (bit.getConfig() != Config.ARGB_8888) {
		Bitmap result = null;
		int width=0;
		int height=0;
		if (bit.getWidth()<bit.getHeight()) {
			if (bit.getWidth()>700) {
				width=700;
				float scale=(float)bit.getWidth()/700;
				height=(int) (bit.getHeight()/scale);
			}else {
				width=bit.getWidth();
				height=bit.getHeight();
			}
		}else {
			if (bit.getHeight()>700) {
				height=700;
				float scale=(float)bit.getHeight()/700;
				width=(int) (bit.getWidth()/scale);
			}else {
				width=bit.getWidth();
				height=bit.getHeight();
			}
			
		}
		result = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		Canvas canvas = new Canvas(result);
		Rect rect = new Rect(0, 0, bit.getWidth(), bit.getHeight());// original
		rect = new Rect(0, 0, width, height);
		canvas.drawBitmap(bit, null, rect, null);
		saveBitmap(result, quality, fileName, optimize);
		result.recycle();
		bit.recycle();
	}

	private static void saveBitmap(Bitmap bit, int quality, String fileName,
			boolean optimize) {

		compressBitmap(bit, bit.getWidth(), bit.getHeight(), quality,
				fileName.getBytes(), optimize);

	}

	private static native String compressBitmap(Bitmap bit, int w, int h,
			int quality, byte[] fileNameBytes, boolean optimize);

	static {
		System.loadLibrary("jpegbither");
		System.loadLibrary("bitherjni");

	}

}
