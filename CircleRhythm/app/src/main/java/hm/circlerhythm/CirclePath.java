package hm.circlerhythm;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by NamHyukMin on 2016. 5. 31..
 */
public class CirclePath extends GameObject {

    public int r;
    public CirclePath(int x, int y)
    {
        r = 300;
        super.x = x;
        super.y = y;
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        canvas.drawCircle(x, y, r, paint);
    }
    public int getRadius(){
        return r;
    }
}
