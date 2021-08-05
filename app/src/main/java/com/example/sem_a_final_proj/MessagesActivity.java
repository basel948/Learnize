package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MessagesActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

      //Messages
    TableRow msg1 , msg2 , msg3 , msg4 , msg5 , msg6 , msg7;

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
        setContentView(R.layout.activity_messages);

        mAuth = FirebaseAuth.getInstance();


        MenuHOOKS();
        msgHOOKS();
    }

    private void msgHOOKS() {

        msg1 = findViewById(R.id.msg1);
        msg2 = findViewById(R.id.msg2);
        msg3 = findViewById(R.id.msg3);
        msg4 = findViewById(R.id.msg4);
        msg5 = findViewById(R.id.msg5);
        msg6 = findViewById(R.id.msg6);
        msg7 = findViewById(R.id.msg7);

        msg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("Dear students,\n" +
                        "\n" +
                        "Following the closure from 7.1.2021 until further notice, the administration offices will move to a work-from-home format.\n" +
                        "\n" +
                        "You are asked to contact each request by e-mail through the student website = contact the secretariat\n" +
                        "\n" +
                        "The offices that will work partially are:\n" +
                        "\n" +
                        "Student Manager:\n" +
                        "\n" +
                        "Orly / Neta Phone: 09-8303539 / 504\n" +
                        "\n" +
                        "Systems and exams:\n" +
                        "\n" +
                        "Rachel and Abigail 09-8303536 / 537\n" +
                        "\n" +
                        "We wish you all good health.\n" +
                        "\n" +
                        "College management.\n").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        msg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("Course : Systems Analysis\n" +
                        "There is a submission of a new work on the website of the course Analysis Systems\n" +
                        "There is a submission of a new work on the website of the course Analysis Systems\n" +
                        "Exercise 5").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        msg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("SQL Databases\n" +
                        "Work submission permit, Student: Username\n" +
                        "Topic: 40003 SQL Databases\n" +
                        "Name of the assignment to be submitted: Assignment no. 5 - Triggers - Create a tracking table\n" +
                        "Reference number: 35028\n" +
                        "Original file location: Group - University Exercise.sql\\n").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        msg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("An online lesson reminder that will start in a few minutes - SQL Databases\n" +
                        "You can enter an online class with the lecturer\n" +
                        "Topic: SQL Databases\n" +
                        "Today - 13/01/2021\n" +
                        "Hours: 11:30 - 13:00\n").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        msg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("Course : Full Stack-React-UI\n" +
                        "Invitation to an online meeting on Full Stack-React-UI\n" +
                        "An online meeting on Full Stack-React-UI has been scheduled\n" +
                        "To enter and participate in the meeting, go to the course website at the student information station for the link \n" +
                        "Go to online meetings\n" +
                        "The meeting will take place on: 12/01/2021 at: 10:00\n").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        msg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("A reminder of an online lesson that will begin in a few more minutes - developing applications for Android\n" +
                        "You can enter an online class with the lecturer\n" +
                        "Topic: Developing applications for Android\n" +
                        "Today - 08/01/2021\n" +
                        "Hours: 10:00 - 14:00\n").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        msg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessagesActivity.this);

                builder.setMessage("Hi Basel Qhawiesh,\n" +
                        "Mosaab Abo Rkia has joined your Personal Meeting Room.\n" +
                        "Thank you for choosing Zoom.\n" +
                        "-The Zoom Team").setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

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
                startActivity(new Intent(MessagesActivity.this ,HomePageActivity.class));
                break;
            case R.id.nav_messages:
                startActivity(new Intent(MessagesActivity.this ,MessagesActivity.class));
                break;
            case R.id.nav_schedule:
                startActivity(new Intent(MessagesActivity.this ,StudyScheduleActivity.class));
                break;
            case R.id.nav_exams:
                startActivity(new Intent(MessagesActivity.this ,ExamsActivity.class));
                break;
            case R.id.nav_grades:
                startActivity(new Intent(MessagesActivity.this ,GradesActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(MessagesActivity.this ,ProfileActivity.class));
                break;
            case R.id.nav_scholarship:
                startActivity(new Intent(MessagesActivity.this ,ScholarshipActivity.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MessagesActivity.this ,MainActivity.class));
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