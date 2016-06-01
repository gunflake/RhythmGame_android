package hm.circlerhythm;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by NamHyukMin on 2016. 5. 31..
 */
public class Hurdle extends GameObject {
    private double deg;

    private double rad;
    private int circle_R;
    private Random rand = new Random();



    public Hurdle(int x, int y, int circleSize, int hurdleSize) {
        circle_R = circleSize-10;
        width = x;
        height = y;

        objectSize = hurdleSize;

        dx = 10;
        dy = 10;

        //deg=70;
        deg = rand.nextInt(95)+200;
        rad = Math.toRadians(deg);
        super.x = (int) (circle_R * Math.cos(rad));
        super.y = (int) (circle_R * Math.sin(rad));


        deg = rand.nextInt(95)+290;
        rad = Math.toRadians(deg);
        x1 = (int) (circle_R * Math.cos(rad));
        y1 = (int) (circle_R * Math.sin(rad));

        circle_R = circleSize+10;

        deg = rand.nextInt(95)+40;
        rad = Math.toRadians(deg);
        x2 = (int) (circle_R * Math.cos(rad));
        y2 = (int) (circle_R * Math.sin(rad));

        deg = rand.nextInt(130)+40;
        rad = Math.toRadians(deg);
        x3 = (int) (circle_R * Math.cos(rad));
        y3 = (int) (circle_R * Math.sin(rad));
    }

    public void update() {

        if (deg > 359)
            deg = 0;

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawCircle(x + width, y + height, objectSize, paint);
        canvas.drawCircle(x1 + width, y1 + height, objectSize, paint);
        canvas.drawCircle(x2 + width, y2 + height, objectSize, paint);
        canvas.drawCircle(x3 + width, y3 + height, objectSize, paint);
    }


}
