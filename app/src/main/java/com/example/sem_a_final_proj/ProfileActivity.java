package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    //Variable used for the Drawer and Navigation
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView burger_icon;

    //Vars for the Animation
    static final float END_SCALE = 0.7f;
    LinearLayout contentView;


    EditText chguserName , chguserEmail , chguserPassword , chguserRePassword;
    RadioButton chgmale , chgfemale;
    ImageView chgprofileImage;
    Button saveChanges;

    //variables used for changing users info
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference myRef;
    Student student;




    // Variables use in Choosing an Image from gallery
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int CHOOSE_IMAGE_FROM_GALLERY = 2;
    Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        myRef = FirebaseDatabase.getInstance("https://sem-a-final-proj-learnize-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Users")
                .child(mAuth.getUid());

        Log.i("hehlnae;lgna;g" , user.getEmail());

        // another way to make a setOnClickListner , by starting it with findViewById
        findViewById(R.id.userImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        initViews();
        MenuHOOKS();
    }


    // -------------------------------------------------------------------EVERYTHING TO DO WITH UPLOADING AN IMAGE--------------------------------------------------------------------------
    // upload image to firestore
    private void uploadImage() {
        if(checkPermissions()){ // if permission is granted we move on with the code
            Intent gallery = new Intent();
            gallery.setType("image/");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery,"Select Image With: "),CHOOSE_IMAGE_FROM_GALLERY);
        }else{
            requestPermissionFromUser(); // if permission is not granted we go and request a permission from the user
        }
    }

    // checks if permission is granted or not
    private boolean checkPermissions() {
        int res = ContextCompat.checkSelfPermission(getApplicationContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (res == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    // we request permission from user to access gallery
    private void requestPermissionFromUser() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(getApplicationContext(),"READ STORAGE Allows Us To Upload Image From Your Gallery To The DataBase",Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    // Here we know if the user choose an image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOOSE_IMAGE_FROM_GALLERY:
                if(requestCode == RESULT_OK){
                    if (data != null && data.getData() != null){
                        // Here we make the code for what we want to do with the image
                    }
                }
        }
    }

    //if permission is granted we go to the uploadImage Function to upload the image we want
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  PERMISSION_REQUEST_CODE:
                uploadImage();
        }
    }


    private void initViews() {
        chguserName = findViewById(R.id.ChangeUsername);
        chguserEmail = findViewById(R.id.ChangeEmail);
        chguserPassword = findViewById(R.id.changePassword);
        chguserRePassword = findViewById(R.id.changeRePassword);
        chgmale = findViewById(R.id.changeMale);
        chgfemale = findViewById(R.id.changeFemale);
        saveChanges = findViewById(R.id.SaveChanges);
    }

    //----------------------------------------------------Every Function For The Changing User Info---------------------------------------




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
                startActivity(new Intent(ProfileActivity.this ,HomePageActivity.class));
                break;
            case R.id.nav_messages:
                startActivity(new Intent(ProfileActivity.this ,MessagesActivity.class));
                break;
            case R.id.nav_schedule:
                startActivity(new Intent(ProfileActivity.this ,StudyScheduleActivity.class));
                break;
            case R.id.nav_exams:
                startActivity(new Intent(ProfileActivity.this ,ExamsActivity.class));
                break;
            case R.id.nav_grades:
                startActivity(new Intent(ProfileActivity.this ,GradesActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(ProfileActivity.this ,ProfileActivity.class));
                break;
            case R.id.nav_scholarship:
                startActivity(new Intent(ProfileActivity.this ,ScholarshipActivity.class));
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this ,MainActivity.class));
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
