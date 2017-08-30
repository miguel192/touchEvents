package layuots.miguel.example.com.touchevents;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{

        Button siguiente;

        private static final String DEBUG_TAG = "Gestures";
        private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);

        // usamos la variable tipo button para hacer refrencia a uno fisico
        siguiente = (Button)findViewById(R.id.btn1);
        // esta es la funcion para cuando el usuaurio le de click al button te mande a la segunda Activity
        siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                Intent siguiente = new Intent(MainActivity.this, Gestures2.class);
                startActivity(siguiente);
            }


        } );

    }

    // funcion para cuando presiones algun lugar de pantalla te de las ccordenas exactas
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        TextView textView = (TextView) findViewById(R.id.hello_world);
        textView.setText("Touch coordinates : " +
                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
        // Be sure to call the superclass implementation

        return super.onTouchEvent(event);
    }

    // funcion cuando esta presionando la pantalla
    @Override
    public boolean onDown(MotionEvent event) {
        changeBackgroundColor(Color.BLUE);
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    // funcion cuando lo deslisas la pantalla
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        changeBackgroundColor(Color.BLACK);
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }


    // funcion para identificar cuando deljas presioando por un buen rato la pantalla
    @Override
    public void onLongPress(MotionEvent event) {
        changeBackgroundColor(Color.LTGRAY);
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    // funcion cuando deslisas la pantalla hacia arriba o abajo
    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        changeBackgroundColor(Color.RED);
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }


    @Override
    public void onShowPress(MotionEvent event) {
        changeBackgroundColor(Color.MAGENTA);
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }


    // funcion cuando presionas una sola ves la pantalla
    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        changeBackgroundColor(Color.DKGRAY);
        return true;
    }


    // funcion cuando presionas dos veces la pantalla seguido
    @Override
    public boolean onDoubleTap(MotionEvent event) {
        changeBackgroundColor(Color.GREEN);
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        changeBackgroundColor(Color.YELLOW);
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        changeBackgroundColor(Color.CYAN);
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }


    // funcion para hacer el cambio del color de la pantalla.
    public void changeBackgroundColor(int color) {
        View view  = findViewById(R.id.main_layout);
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
                View view  = findViewById(R.id.main_layout);
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();


//        view.setBackgroundColor(color);
    }


}
