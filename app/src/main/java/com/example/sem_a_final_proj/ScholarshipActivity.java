package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ScholarshipActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "ScholarshipActivity";

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
        setContentView(R.layout.activity_scholarship);

        mAuth = FirebaseAuth.getInstance();


        MenuHOOKS();

        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.scholarshipList);

        //Create the Scholarships objects
        Scholarship_detailes scholarship = new Scholarship_detailes("Scholarship from the Ministry of Education" , "15/3/2021" , "https://loans.education.gov.il");
        Scholarship_detailes scholarship1 = new Scholarship_detailes("Governor's Scholarship for the entire degree" , "1/6/2021" , "https://milgapo.co.il");
        Scholarship_detailes scholarship2 = new Scholarship_detailes("High-tech achievement scholarship" , "10/9/2021" , "https://aluma.org.il/program/");
        Scholarship_detailes scholarship3 = new Scholarship_detailes("irtiqaa scholarship" , "4/4/2021" , "https://www.tolab48.net/scholarships/ba/irteka/");
        Scholarship_detailes scholarship4 = new Scholarship_detailes("Islamic Endowment Scholarship" , "16/7/2021" , "https://www.tolab48.net/scholarships/ba/wakf-islami/");
        Scholarship_detailes scholarship5 = new Scholarship_detailes("Coronavirus scholarship" , "25/12/2021" , "https://www.gov.il/en/service/grant-self-employed/");
        Scholarship_detailes scholarship6 = new Scholarship_detailes("Sahlab scholarship" , "27/1/2021" , "http://www.sachlav-edu.co.il/");
        Scholarship_detailes scholarship7 = new Scholarship_detailes("Milgapo Scholarship for Students" , "31/11/2021" , "https://milgapo.co.il/?milga=_milgapo");
        Scholarship_detailes scholarship8 = new Scholarship_detailes("Pagaya Scholarship" , "10/10/2021" , "https://www.pagaya.com/pagaya-");
        Scholarship_detailes scholarship9 = new Scholarship_detailes("Civic Center Scholarship" , "12/6/2021" , "https://milgapo.co.il/?");
        Scholarship_detailes scholarship10 = new Scholarship_detailes("Lenovo scholarship for students ₪4,000" , "9/10/2021" , "https://milgapo.co.il/?milga= lenovo");
        Scholarship_detailes scholarship11 = new Scholarship_detailes("Flower Scholarship >> 5,200 to 10,000 ₪" , "2/2/2021" , "https://www.perach.org.il/");
        Scholarship_detailes scholarship12 = new Scholarship_detailes("irtiqaa scholarship" , "4/4/2021" , "https://www.tolab48.net/scholarships/ba/irteka/");
        Scholarship_detailes scholarship13 = new Scholarship_detailes("Islamic Endowment Scholarship" , "16/7/2021" , "https://www.tolab48.net/scholarships/ba/wakf-islami/");
        Scholarship_detailes scholarship14 = new Scholarship_detailes("Coronavirus scholarship" , "25/12/2021" , "https://www.gov.il/en/service/grant-self-employed/");
        Scholarship_detailes scholarship15 = new Scholarship_detailes("Coronavirus scholarship" , "25/12/2021" , "https://www.gov.il/en/service/grant-self-employed/");
        Scholarship_detailes scholarship16 = new Scholarship_detailes("Sahlab scholarship" , "27/1/2021" , "http://www.sachlav-edu.co.il/");
        Scholarship_detailes scholarship17 = new Scholarship_detailes("Milgapo Scholarship for Students" , "31/11/2021" , "https://milgapo.co.il/?milga=_milgapo");
        Scholarship_detailes scholarship18 = new Scholarship_detailes("Islamic Endowment Scholarship" , "16/7/2021" , "https://www.tolab48.net/scholarships/ba/wakf-islami/");
        Scholarship_detailes scholarship19 = new Scholarship_detailes("Coronavirus scholarship" , "25/12/2021" , "https://www.gov.il/en/service/grant-self-employed/");
        Scholarship_detailes scholarship20 = new Scholarship_detailes("Coronavirus scholarship" , "25/12/2021" , "https://www.gov.il/en/service/grant-self-employed/");
        Scholarship_detailes scholarship21 = new Scholarship_detailes("Sahlab scholarship" , "27/1/2021" , "http://www.sachlav-edu.co.il/");
        Scholarship_detailes scholarship22 = new Scholarship_detailes("Milgapo Scholarship for Students" , "31/11/2021" , "https://milgapo.co.il/?milga=_milgapo");




        ArrayList<Scholarship_detailes> Scholarship = new ArrayList<>();
        Scholarship.add(scholarship);
        Scholarship.add(scholarship1);
        Scholarship.add(scholarship2);
        Scholarship.add(scholarship3);
        Scholarship.add(scholarship4);
        Scholarship.add(scholarship5);
        Scholarship.add(scholarship6);
        Scholarship.add(scholarship7);
        Scholarship.add(scholarship8);
        Scholarship.add(scholarship9);
        Scholarship.add(scholarship10);
        Scholarship.add(scholarship11);
        Scholarship.add(scholarship12);
        Scholarship.add(scholarship13);
        Scholarship.add(scholarship14);
        Scholarship.add(scholarship15);
        Scholarship.add(scholarship16);
        Scholarship.add(scholarship17);
        Scholarship.add(scholarship18);
        Scholarship.add(scholarship19);
        Scholarship.add(scholarship20);
        Scholarship.add(scholarship21);
        Scholarship.add(scholarship22);



        ScholarshipListAdapter adapter = new ScholarshipListAdapter(this, R.layout.adapter_view_layout , Scholarship);
        mListView.setAdapter(adapter);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                startActivity(new Intent(ScholarshipActivity.this ,HomePageActivity.class));
                break;
            case R.id.nav_messages:
                startActivity(new Intent(ScholarshipActivity.this ,MessagesActivity.class));
                break;
            case R.id.nav_schedule:
                startActivity(new Intent(ScholarshipActivity.this ,StudyScheduleActivity.class));
                break;
            case R.id.nav_grades:
                startActivity(new Intent(ScholarshipActivity.this ,GradesActivity.class));
                break;
            case R.id.nav_exams:
                startActivity(new Intent(ScholarshipActivity.this ,ExamsActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(ScholarshipActivity.this ,ProfileActivity.class));
                break;
            case R.id.nav_scholarship:
                startActivity(new Intent(ScholarshipActivity.this ,ScholarshipActivity.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ScholarshipActivity.this ,MainActivity.class));
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
        //NAVIGATION DRAWER CASES
        switch (v.getId()) {
            case R.id.burger_icon:
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer((GravityCompat.START));
                else
                    drawerLayout.openDrawer((GravityCompat.START));
        }
    }
}