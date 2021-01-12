package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    //Variable used for the FAB button
    FloatingActionButton socialMedia_fab, top_fab, left_fab;
    Animation fabOpen , fabClose , rotateForward , rotateBackwards;
    boolean isOpen = false;


    //Variable used for the Drawer and Navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView burger_icon;
    //Vars for the Animation
    static final float END_SCALE = 0.7f;
    LinearLayout contentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //this contains all (findViewById & setOnClickListener & setAlpha)
        //to make the onCreate cleaner
        initFabMenu();
        MenuHOOKS();
    }

    //---------------------------------------------Every Function For The FAB Buttons------------------------------
    private void initFabMenu() {
        socialMedia_fab = findViewById(R.id.socialMedia_fab_btn);
        top_fab = findViewById(R.id.top_fab_btn);
        left_fab = findViewById(R.id.left_fab_btn);

        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackwards = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);


        socialMedia_fab.setOnClickListener(this);
        top_fab.setOnClickListener(this);
        left_fab.setOnClickListener(this);
    }

    private  void animateFab()
    {
        if(isOpen)
        {
            socialMedia_fab.startAnimation(rotateForward);
            top_fab.startAnimation(fabClose);
            left_fab.startAnimation(fabClose);
            top_fab.setClickable(false);
            left_fab.setClickable(false);
            isOpen = false;
        }else{
            socialMedia_fab.startAnimation(rotateBackwards);
            top_fab.startAnimation(fabOpen);
            left_fab.startAnimation(fabOpen);
            top_fab.setClickable(true);
            left_fab.setClickable(true);
            isOpen = true;
        }
    }




    //----------------------------------------------------Every Function For The Drawer Layout & Navigation View---------------------------------------
    //this contains all (findViewById)
    //to make the onCreate cleaner
    private void MenuHOOKS() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        burger_icon = findViewById(R.id.burger_icon);
        contentView = findViewById(R.id.content);

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        burger_icon.setOnClickListener(this);

        //Animation Function
        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.black));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //Scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the View,accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }
        });
    }

    //if true then it means there item which has been selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    //this will close the drawer bu clicking on the back button in the phone
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible((GravityCompat.START)))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }







    //------------------------------------------------------------------OnClick Function------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //FAB BUTTON CASES
            case R.id.socialMedia_fab_btn:
                animateFab();
                break;
            case R.id.top_fab_btn:
                animateFab();
                break;
            case R.id.left_fab_btn:
                animateFab();
                break;

            //NAVIGATION DRAWER CASES
            case R.id.burger_icon:
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer((GravityCompat.START));
                else
                    drawerLayout.openDrawer((GravityCompat.START));
        }
    }

}
