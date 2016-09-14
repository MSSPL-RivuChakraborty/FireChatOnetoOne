package com.massoftind.rnd.firechatonetoone.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundedImageView extends ImageView {

public RoundedImageView(Context context) {
    super(context);
    // TODO Auto-generated constructor stub
}

public RoundedImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
}

public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
}

@Override
protected void onDraw(Canvas canvas) {


    Drawable drawable = getDrawable();

    if (drawable == null) {
        return;
    }

    if (getWidth() == 0 || getHeight() == 0) {
        return;
    }
    Bitmap b = ((BitmapDrawable) drawable).getBitmap();
    Bitmap bitmap = b.copy(Config.ARGB_8888, true);

    int w = getWidth(), h = getHeight();
    int radius = w < h ? w : h;
    // //Log.d"titas","hh : "+h);
    // //Log.d"titas","ww : "+w);

   // Bitmap roundBitmap = getCroppedBitmap(bitmap, radius, w, h);
    Bitmap roundBitmap = getCroppedBitmap(bitmap, radius,w);
    // roundBitmap= ImageUtils.setCircularInnerGlow(roundBitmap, 0xFFBAB399,
    // 4, 1);
    canvas.drawBitmap(roundBitmap, 0, 0, null);

    /*Drawable drawable = getDrawable();

    if (drawable == null) {
        return;
    }

    if (getWidth() == 0 || getHeight() == 0) {
        return; 
    }
    Bitmap b =  ((BitmapDrawable)drawable).getBitmap() ;
    Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

    int w = getWidth(), h = getHeight();


    Bitmap roundBitmap =  getCroppedBitmap(bitmap, w);
//    Bitmap roundBitmap =  getRound(bitmap, w);
    canvas.drawBitmap(roundBitmap, 0,0, null);*/

}

	/*public static Bitmap getRound(Bitmap bitmap, int radius)
	{
		Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

		BitmapShader shader = new BitmapShader (bitmap,  TileMode.CLAMP, TileMode.CLAMP);
		Paint paint = new Paint();
		paint.setShader(shader);

		Canvas c = new Canvas(circleBitmap);
		c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);
		
		 return circleBitmap;
	}*/

    public Bitmap getCroppedBitmap(Bitmap bmp, int radius, int w, int h) {

        //Bitmap sbmp;
        /*if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float _w_rate = 1.0f * radius / bmp.getWidth();
            float _h_rate = 1.0f * radius / bmp.getHeight();

          */
           /* if (bmp.getWidth() >= bmp.getHeight()){

                sbmp = Bitmap.createBitmap(
                        bmp,
                        bmp.getWidth()/2 - bmp.getHeight()/2,
                        0,
                        bmp.getHeight(),
                        bmp.getHeight()
                );

            }else{

                sbmp = Bitmap.createBitmap(
                        bmp,
                        0,
                        bmp.getHeight()/2 - bmp.getWidth()/2,
                        bmp.getWidth(),
                        bmp.getWidth()
                );
            }


        }
        else
            sbmp = bmp;*/

        Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bmp.getWidth()+3, bmp.getHeight()+3);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
      //  canvas.drawCircle(w / 2, h / 2, (w < h ? w : h) / 2, paint);

        //canvas.drawCircle((w < h ? w : h) / 2, (w < h ? w : h) / 2, (w < h ? w : h) / 2, paint);
        // //Log.d"titas","x : "+bmp.getHeight()/2);
        // //Log.d"titas", "y : " + bmp.getWidth() / 2);
        // //Log.d"titas", "r : " + bmp.getWidth() / 2);
        if(bmp.getHeight()==bmp.getWidth()) {
            canvas.drawCircle(bmp.getHeight() / 2, bmp.getWidth() / 2, bmp.getWidth() / 2, paint);// //Log.d"titas", "1");
        }else if(bmp.getHeight()>bmp.getWidth())
        {
            canvas.drawCircle( bmp.getWidth() / 2,bmp.getHeight() / 2, bmp.getWidth() / 2, paint);// //Log.d"titas", "2");
        }else
        {
            canvas.drawCircle( bmp.getWidth() / 2,bmp.getHeight() / 2, bmp.getHeight() / 2, paint);// //Log.d"titas", "3");
        }

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);

        return output;
    }

public static Bitmap getCroppedBitmap(Bitmap bmp, int radius, int w) {
    Bitmap sbmp;


    if (bmp.getWidth() >= bmp.getHeight()){

        sbmp = Bitmap.createBitmap(
                bmp,
                bmp.getWidth()/2 - bmp.getHeight()/2,
                0,
                bmp.getHeight(),
                bmp.getHeight()
        );

    }else{

        sbmp = Bitmap.createBitmap(
                bmp,
                0,
                bmp.getHeight()/2 - bmp.getWidth()/2,
                bmp.getWidth(),
                bmp.getWidth()
        );
    }

    //if(bmp.getWidth() != radius || bmp.getHeight() != radius)
        sbmp = Bitmap.createScaledBitmap(sbmp, w, w, false);
   /* else
        sbmp = bmp;*/


    Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
            sbmp.getHeight(), Config.ARGB_8888);
    Canvas canvas = new Canvas(output);

    final int color = 0xffa19774;
    final Paint paint = new Paint();
    final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
    
    final Rect rect1 = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

    paint.setAntiAlias(true);
    paint.setFilterBitmap(true);
    paint.setDither(true);
    canvas.drawARGB(0, 0, 0, 0);
    paint.setColor(Color.parseColor("#BAB399"));
    canvas.drawCircle(sbmp.getWidth() / 2f, sbmp.getHeight() / 2f,
            sbmp.getWidth() / 2f, paint);
    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
    canvas.drawBitmap(sbmp, rect, rect1, paint);


            return output;
}

}
