package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    //CardView Vars
    CardView exams_card , schedule_card , profile_card , scholarship_card , messages_card , grades_card;


    ImageView userImg;

    //Variable used for the Drawer and Navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView burger_icon;

    //Vars for the Animation
    static final float END_SCALE = 0.7f;
    LinearLayout contentView;


    private FirebaseAuth mAuth;
    private FirebaseUser user;
    TextView userName_Name;
    Student student;
    DatabaseReference myRef;

    //to retrieve an image from firebase storage
    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance("https://sem-a-final-proj-learnize-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Users")
                .child(mAuth.getUid());

        userName_Name = findViewById(R.id.userName_Name);


        mStorageReference = FirebaseStorage.getInstance().getReference().child(mAuth.getUid()).child("Images/");
        Log.i("heeeeerrrreeeeee" , mStorageReference.toString());
        try {
            final File localFile = File.createTempFile("photo_1623519618629" , "jpg");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ((ImageView)findViewById(R.id.userImg)).setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) { // if user has no profile image the function enters here (addOnFailureListener)
//                    Toast.makeText(HomePageActivity.this,"Error in Retrieving the Profile Image",Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                student = snapshot.getValue(Student.class);
                userName_Name.setText("Welcome Back " + student.getUserName());
//                Log.i("heeereeeeee222222222",student.getImage());

            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });

        userImg = findViewById(R.id.userImg);


        CardsHooks();
        MenuHOOKS();

    }

    private void CardsHooks() {
        exams_card = findViewById(R.id.exams_card);
        grades_card = findViewById(R.id.grades_card);
        messages_card = findViewById(R.id.messages_card);
        profile_card = findViewById(R.id.profile_card);
        schedule_card = findViewById(R.id.schedule_card);
        scholarship_card = findViewById(R.id.scholarship_card);

        exams_card.setOnClickListener(this);
        grades_card.setOnClickListener(this);
        messages_card.setOnClickListener(this);
        profile_card.setOnClickListener(this);
        schedule_card.setOnClickListener(this);
        scholarship_card.setOnClickListener(this);

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

    // Animation when Opening & Closing The Drawer
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
    //NAVIGATION DRAWER CASES
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(HomePageActivity.this ,HomePageActivity.class));
                break;
            case R.id.nav_messages:
                startActivity(new Intent(HomePageActivity.this ,MessagesActivity.class));
                break;
            case R.id.nav_schedule:
                startActivity(new Intent(HomePageActivity.this ,StudyScheduleActivity.class));
                break;
            case R.id.nav_grades:
                startActivity(new Intent(HomePageActivity.this ,GradesActivity.class));
                break;
            case R.id.nav_exams:
                startActivity(new Intent(HomePageActivity.this ,ExamsActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(HomePageActivity.this ,ProfileActivity.class));
                break;
            case R.id.nav_scholarship:
                startActivity(new Intent(HomePageActivity.this ,ScholarshipActivity.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomePageActivity.this ,MainActivity.class));
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



//----------------------------------------------------Every Function For The Cards Layout View-------------------------------------------------------------------------

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.burger_icon:
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer((GravityCompat.START));
                else
                    drawerLayout.openDrawer((GravityCompat.START));
                break;
            case R.id.exams_card:
                startActivity(new Intent(HomePageActivity.this , ExamsActivity.class));
                break;
            case R.id.grades_card:
                startActivity(new Intent(HomePageActivity.this , GradesActivity.class));
                break;
            case R.id.profile_card:
                startActivity(new Intent(HomePageActivity.this , ProfileActivity.class));
                break;
            case R.id.schedule_card:
                startActivity(new Intent(HomePageActivity.this , StudyScheduleActivity.class));
                break;
            case R.id.scholarship_card:
                startActivity(new Intent(HomePageActivity.this , ScholarshipActivity.class));
                break;
            case R.id.messages_card:
                startActivity(new Intent(HomePageActivity.this , MessagesActivity.class));
                break;
        }

    }


}