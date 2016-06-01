package hm.circlerhythm;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {


    public static int WIDTH;
    public static int HEIGHT;

    private MainThread thread;
    private Background bg;
    private Player player;
    private CirclePath circlePath;
    private long hurdleStartTime;

    private boolean started;
    private boolean reset;
    private boolean newGameCreated;
    private int best = 0;
    private int circleRadius;
    private Hurdle hurdle;

    private SoundMusic music;
    private int objectSize;
    Context context;

    public GamePanel(Context context, int w, int h) {
        super(context);

        WIDTH = w;
        HEIGHT = h;

        circleRadius = (WIDTH/2)-60;
        objectSize = circleRadius/30;

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);


        this.context=context;
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.whitebg));
        player = new Player(WIDTH / 2, HEIGHT / 2, circleRadius, objectSize);
        circlePath = new CirclePath(WIDTH / 2, HEIGHT / 2);
        hurdle = new Hurdle(WIDTH / 2, HEIGHT / 2, circleRadius, objectSize);


        hurdleStartTime = System.nanoTime();

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!player.getPlaying() && newGameCreated && reset) {
                player.setPlaying(true);
                music = new SoundMusic(context);
                music.startMusic();
                player.setCircle();
            }
            if (player.getPlaying()) {

                if (!started) started = true;
                reset = false;
                player.setCircle();
            }
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            player.setCircle();
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update() {
        if (player.getPlaying()) {
            bg.update();
            player.update();
            circlePath.update();

            //hurdle.update();

            if(player.getDeg()==180)
                hurdle = new Hurdle(WIDTH / 2, HEIGHT / 2, circleRadius, objectSize);

            if(player.getDeg()>359) {
                player.resetDeg0();
            }

            if (player.getScore() > best) {
                best = player.getScore();

            }

            if (collision(player, hurdle)) {
                player.setPlaying(false);
                music.stopMusic();

            }

        } else {
            if (!reset) {
                newGameCreated = false;
                reset = true;
                newGame();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);

        if (canvas != null) {
            final int savedState = canvas.save();
            //    canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            circlePath.draw(canvas);
            hurdle.draw(canvas);
            drawText(canvas);

            canvas.restoreToCount(savedState);
        }
    }

    public boolean collision(GameObject a, GameObject b) {
        if (Rect.intersects(a.getRectangle(), b.getRectangle())) {
            return true;
        } else if (Rect.intersects(a.getRectangle(), b.getRectangle1()))
            return true;
        else if (Rect.intersects(a.getRectangle(), b.getRectangle2()))
            return true;
        else if (Rect.intersects(a.getRectangle(), b.getRectangle3()))
            return true;

        return false;
    }

    public void newGame() {
        player.resetPosition();
        player.resetScore();
        player.setY(HEIGHT / 2);

        hurdle = new Hurdle(WIDTH / 2, HEIGHT / 2, circleRadius, objectSize);
        newGameCreated = true;
    }

    public void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("DISTANCE: " + player.getScore(), 10, HEIGHT - 10, paint);
        canvas.drawText("BEST: " + best, WIDTH - 215, HEIGHT - 10, paint);

        if (!player.getPlaying() && newGameCreated && reset) {
            Paint paint1 = new Paint();
            paint1.setTextSize(40);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("PRESS TO START", WIDTH / 2 - 50, HEIGHT / 2, paint1);

            paint1.setTextSize(20);
            canvas.drawText("PRESS AND HOLD TO GO UP", WIDTH / 2 - 50, HEIGHT / 2 + 20, paint1);
            canvas.drawText("RELEASE TO GO DOWN", WIDTH / 2 - 50, HEIGHT / 2 + 40, paint1);
        }
    }

}

