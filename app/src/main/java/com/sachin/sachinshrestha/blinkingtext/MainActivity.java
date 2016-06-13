package com.sachin.sachinshrestha.blinkingtext;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    TextView tvHelloWorld;
    Button btnBlink;
    Animation animationTextBlink;
    Boolean blinkFLAG;
    FloatingActionButton fabPlus, fabMinus;
    private int speedBlink = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHelloWorld = (TextView) findViewById(R.id.tvHelloWorld);
        btnBlink = (Button) findViewById(R.id.btnBlink);
        fabPlus = (FloatingActionButton) findViewById(R.id.fabPlus);
        fabMinus = (FloatingActionButton) findViewById(R.id.fabMinus);
        blinkFLAG = true;

        // different setup associated with the animation of the text
        animationSetup();

        // Keep screen ON
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Display text and maintain its size
        tvHelloWorld.setText("Hello World!");
        tvHelloWorld.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

        // To keep the view in the Center|Horizontal in the layout
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tvHelloWorld.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tvHelloWorld.setLayoutParams(lp);

        // Blink button
        btnBlink.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if (blinkFLAG){
                    tvHelloWorld.setText("Hello World Blinking!");
                    btnBlink.setText("STOP");
                    startBlinking(tvHelloWorld);
                    blinkFLAG = false;
                } else {
                    tvHelloWorld.setText("Hello World not Blinking!");
                    btnBlink.setText("BLINK");
                    stopBlinking(tvHelloWorld);
                    blinkFLAG = true;
                }
            }
        });

        // Floating action button - Increase blinking rate
        fabPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                speedBlink = speedBlink - 100;
                if(speedBlink < 100){
                    speedBlink = 100;
                    Toast.makeText(getApplicationContext(),"Max blinking rate achieved!", Toast.LENGTH_SHORT).show();
                }
                animationTextBlink.setDuration(speedBlink);
            }
        });

        // Floating action button - Decrease blinking rate
        fabMinus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                speedBlink = speedBlink + 100;
                if(speedBlink > 1000){
                    speedBlink = 1000;
                    Toast.makeText(getApplicationContext(),"Min blinking rate achieved!", Toast.LENGTH_SHORT).show();
                }
                animationTextBlink.setDuration(speedBlink);
            }
        });
    }

    private void animationSetup(){
        animationTextBlink = new AlphaAnimation(0.0f, 1.0f);        //
        animationTextBlink.setDuration(speedBlink);                        // Manage the time of the blink
        animationTextBlink.setStartOffset(20);
        animationTextBlink.setRepeatMode(Animation.REVERSE);
        animationTextBlink.setRepeatCount(Animation.INFINITE);
    }

    private void startBlinking(View tv){
        tv.setAnimation(animationTextBlink);
    }

    private void stopBlinking(View tv){
        tv.clearAnimation();
    }
}
