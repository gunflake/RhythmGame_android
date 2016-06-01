package hm.circlerhythm;

import android.graphics.Rect;

/**
 * Created by NamHyukMin on 2016. 5. 31..
 */
public abstract class GameObject {
    protected int x;
    protected int y;

    protected int objectSize;


    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int x3;
    protected int y3;


    protected int dx;
    protected int dy;

    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }



    public Rect getRectangle(){
        return new Rect(x, y, x+dx, y+dy);
    }
    public Rect getRectangle1(){
        return new Rect(x1, y1, x1+dx, y1+dy);
    }
    public Rect getRectangle2(){
        return new Rect(x2, y2, x2+dx, y2+dy);
    }
    public Rect getRectangle3(){
        return new Rect(x3, y3, x3+dx, y3+dy);
    }

}
