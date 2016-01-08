package com.zl.canvasclip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

public class ClipView extends View {
	
	private int x,y,x1,y1;
	
	private int w,h;

	private Paint mPaint;

	private Bitmap bitmapDis, bitmapSrc;

	private PorterDuffXfermode porterDuffXfermode;

	public ClipView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);  
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        
        bitmapDis = BitmapFactory.decodeResource(context.getResources(), R.drawable.function_type_bg);  
        bitmapSrc = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);  
        bitmapDis = Bitmap.createScaledBitmap(bitmapDis, 300, 300, true);
        bitmapSrc = Bitmap.createScaledBitmap(bitmapSrc, 150, 150, true);
        w = 300;
        h = 300;
        x = 0;
        y = 0;
        x1 = 150 - bitmapSrc.getWidth()/2;
        y1 = 150 - bitmapSrc.getHeight()/2;
        
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLUE);
        int sc = canvas.saveLayer(0, 0, w, h, null, Canvas.ALL_SAVE_FLAG);  
        canvas.drawBitmap(bitmapDis, x, y, mPaint);  
        mPaint.setXfermode(porterDuffXfermode);  
        canvas.drawBitmap(bitmapSrc, x1, y1, mPaint);  
        mPaint.setXfermode(null);  
        canvas.restoreToCount(sc);  
	}

}
