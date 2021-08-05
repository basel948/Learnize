package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ExamsActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    //Variable used for the Drawer and Navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView burger_icon;

    //Vars for the Animation
    static final float END_SCALE = 0.7f;
    LinearLayout contentView;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        mAuth = FirebaseAuth.getInstance();


        MenuHOOKS();
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

        drawerLayout.setScrimColor(getResources().getColor(R.color.lighter_white));
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(ExamsActivity.this ,HomePageActivity.class));
                break;
            case R.id.nav_messages:
                startActivity(new Intent(ExamsActivity.this ,MessagesActivity.class));
                break;
            case R.id.nav_schedule:
                startActivity(new Intent(ExamsActivity.this ,StudyScheduleActivity.class));
                break;
            case R.id.nav_grades:
                startActivity(new Intent(ExamsActivity.this ,GradesActivity.class));
                break;
            case R.id.nav_exams:
                startActivity(new Intent(ExamsActivity.this ,ExamsActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(ExamsActivity.this ,ProfileActivity.class));
                break;
            case R.id.nav_scholarship:
                startActivity(new Intent(ExamsActivity.this ,ScholarshipActivity.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ExamsActivity.this ,MainActivity.class));
                break;

        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //NAVIGATION DRAWER CASES
            case R.id.burger_icon:
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer((GravityCompat.START));
                else
                    drawerLayout.openDrawer((GravityCompat.START));
        }

    }
}