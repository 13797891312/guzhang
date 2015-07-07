package com.caiyun.guzhang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class SignView extends View {
	Paint paint = new Paint();
	private float come, unCome, leave;
	private int strokeWidth = 14;
	private RectF oval2;
	private int comeColor, unComeColor, leaveColor;

	public SignView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SignView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void init() {
		paint.setStrokeWidth(strokeWidth);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		this.invalidate();
	}

	/**
	 * 
	 * @param strokeWidth
	 *            圆圈宽度
	 * @param maxProgress
	 *            最大进度
	 * @param progressColor
	 *            进度条颜�?
	 * @param textSize
	 *            中间字体大小
	 * @param textColor
	 *            字体颜色
	 */
	public void init(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	/**
	 * 
	 * @param come
	 * @param unCome
	 * @param leave
	 * @param progressColor1
	 * @param progressColor2
	 * @param progressColor3
	 */
	public void setValues(float come, float unCome, float leave,
			int progressColor1, int progressColor2, int progressColor3) {
		this.come = come;
		this.unCome = unCome;
		this.leave = leave;
		this.comeColor = progressColor1;
		this.unComeColor = progressColor2;
		this.leaveColor = progressColor3;
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (oval2 == null) {
			oval2 = new RectF(strokeWidth / 2, strokeWidth / 2, this.getWidth()
					- strokeWidth / 2, this.getHeight() - strokeWidth / 2);// 设置个新的长方形，扫描测�?
		}
		paint.setColor(comeColor);
		canvas.drawArc(oval2, -90, 360 * come, false, paint);
		paint.setColor(unComeColor);
		canvas.drawArc(oval2, 360*come-90, 360 * unCome, false, paint);
		paint.setColor(leaveColor);
		canvas.drawArc(oval2, 360*come+360*unCome-90, 360*leave, false, paint);
	}
}
