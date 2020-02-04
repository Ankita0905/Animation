package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private ImageView box;
    private RelativeLayout frame;
    //bools
    private boolean goToTop = true;
    private boolean goToBottom = false;
    private  boolean goToLeft = true;
    private boolean goToRight = false;
    private boolean goDiagonallyDown = false;
    private  boolean goDiagonallyUp = false;
    // Size
    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;
    // Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;
    // Position
    private int boxY;
    private int xDelta;
    private int yDelta;
    // Speed
    private int boxSpeed;
    // Initialize Class
    private Handler handler = new Handler();
    final Timer timer = new Timer();
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.frame);
        box = findViewById(R.id.box);
        //  final box Image = (Button) findViewById(R.id.my_button);
        // box.setOnTouchListener(onTouchListener());
        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
// Get screen size.
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        boxSpeed = Math.round(screenHeight / 60);
        screenWidth = size.x;
        screenHeight = size.y;
        //changePos();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random R = new Random();
                        final float dx = R.nextFloat() * displaymetrics.widthPixels;
                        final float dy = R.nextFloat() * displaymetrics.heightPixels;
                        final Timer timer = new Timer();
                        changePos();
                        //move();
//                        box.animate()
//                                .x(dx)
//                                .y(dy)
//                                .setDuration(0)
//                                .start();
                    }
                });
            }
        }, 0, 10);
    }
    public void changePos()
    {
        float yPosition = box.getY();
        float xPosition = box.getX();
        float screenHeight = frame.getHeight()-150;
        float screenWidth = frame.getWidth() - 100;
        if (goToBottom)
        {
            if(goToRight)
            {
                box.setX(xPosition+=5);
                box.setY(yPosition+=5);
                if(box.getX()>screenWidth-50)
                {
                    box.setX(screenWidth - 50);
                    goToRight = false;
                    goToLeft = true;
                }
            }
            if(goToLeft)
            {
                box.setX(xPosition-=5);
                box.setY(yPosition+=5);
                if(box.getX()<0)
                {
                    box.setX(5);
                    goToRight = true;
                    goToLeft = false;
                }
            }
            if(box.getY() > screenHeight- 50){
                goToBottom = false;
                goToTop = true;
                if(goToLeft){
                    goToRight=false;
                }else {
                    goToRight = true;
                }
            }
        }
        if (goToTop) {
            if(goToRight)
            {
                box.setX(xPosition+=5);
                box.setY(yPosition-=5);
                if(box.getX()>screenWidth-50)
                {
                    box.setX(screenWidth - 50);
                    goToRight = false;
                    goToLeft = true;
                }
            }
            if(goToLeft)
            {
                box.setX(xPosition-=5);
                box.setY(yPosition-=5);
                if(box.getX()<0)
                {
                    box.setX(5);
                    goToRight = true;
                    goToLeft = false;
                }
            }
            if(box.getY() <=0){
                goToBottom = true;
                goToTop = false;
                if(goToLeft){
                    goToRight=false;
                }else {
                    goToRight = true;
                }
            }
        }
    }
}
