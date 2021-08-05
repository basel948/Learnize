package com.example.sem_a_final_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    //HOOKS
    TextView appName , slogan;
    ImageView graduationImg;

    //Animations vars
    Animation middleAnimation, bottomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);



        middleAnimation = AnimationUtils.loadAnimation(this , R.anim.middle_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this , R.anim.bottom_animation);

        appName = findViewById(R.id.appName);
        slogan = findViewById(R.id.appSlogan);
        graduationImg = findViewById(R.id.graduationImg);

        appName.setAnimation(middleAnimation);
        slogan.setAnimation(middleAnimation);

        graduationImg.setAnimation(bottomAnimation);

        //Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}