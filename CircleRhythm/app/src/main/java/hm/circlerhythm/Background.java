package hm.circlerhythm;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res)
    {
        image = res;
    }
    public void update()
    {

    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);

    }

}
