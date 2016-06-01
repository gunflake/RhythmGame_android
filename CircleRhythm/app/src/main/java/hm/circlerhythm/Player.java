package hm.circlerhythm;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Player extends GameObject {

    private int score;
    private double dya;
    private boolean up;
    private boolean playing = false;
    private Animation animation = new Animation();
    private long startTime;
    private int circle_R;
    private double deg;
    private double rad;


    private int r;
    private GamePanel gamePanel;

    private float yy = 0;

    public Player(int x, int y, int r) {

        circle_R = r + 10;

        width = x;
        height = y;

        this.r = 10; //player 크기
        score = 0;

        deg = 180;

        dx = this.r;
        dy = this.r;

        startTime = System.nanoTime();
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setCircle() {
        if (circle_R > 300)
            circle_R = 290;
        else
            circle_R = 310;
    }

    public void update(){

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }

        System.out.println(deg);


        rad = Math.toRadians(deg);
        x = (int) (circle_R * Math.cos(rad));
        y = (int) (circle_R * Math.sin(rad));
        deg += 3;

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(x + width, y + height, r, paint);
    }

    public void resetDeg0(){
        deg = 0;
    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public int getDeg() {
        return (int) deg;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetPosition() {
        deg = 180;
    }

    public void resetScore() {
        score = 0;
    }
}
