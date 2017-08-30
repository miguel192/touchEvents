package layuots.miguel.example.com.touchevents;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Gestures2 extends AppCompatActivity {

    private static final String DEBUG_TAG = "MotionEvent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures2);
    }


    // funcion para ver el tipo de Motion es un case con cada tipo
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TextView textView = (TextView) findViewById(R.id.hello_world);
        textView.setText("Touch coordinates : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                changeBackgroundColor2(Color.GREEN);
                Log.d(DEBUG_TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                changeBackgroundColor2(Color.BLUE);
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                changeBackgroundColor2(Color.CYAN);
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                changeBackgroundColor2(Color.MAGENTA);
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                changeBackgroundColor2(Color.WHITE);
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

// funcion para hacer el cambio de color de la layout cuando identica el tipo de motion que es
    public void changeBackgroundColor2(int color) {
        View view  = findViewById(R.id.main_layout2);
        Drawable background = view.getBackground();
        int colorFrom = Color.TRANSPARENT;
        if (background instanceof ColorDrawable)
            colorFrom = ((ColorDrawable) background).getColor();
        int colorTo = color;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                View view  = findViewById(R.id.main_layout2);
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();


//        view.setBackgroundColor(color);
    }

}
