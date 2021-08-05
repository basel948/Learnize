package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
//import android.util.Log;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
//import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SingupActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    // To change profile image
//    private CircleImageView ProfileImage;
//    private static final int PICK_IMAGE = 1;
//    Uri imageUri;

    EditText userName , userEmail , userPassword , userRePassword;
    RadioButton male , female;
    Button btn_SignUp;
    ImageView profileImage;
//    String uri2Show;
//    String url;


    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;


    // Variables use in Choosing an Image from gallery
    StorageReference myStorageRef;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int CHOOSE_IMAGE_FROM_GALLERY = 2;
    Uri ImageUri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        database = FirebaseDatabase.getInstance("https://sem-a-final-proj-learnize-default-rtdb.europe-west1.firebasedatabase.app/");

        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("Users");
        myStorageRef = FirebaseStorage.getInstance().getReference();

        // another way to make a setOnClickListner , by starting it with findViewById
        findViewById(R.id.ProfileImg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        initViews();

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }


    // -------------------------------------------------------------------EVERYTHING TO DO WITH UPLOADING AN IMAGE--------------------------------------------------------------------------
    // upload image to firestore
    private void uploadImage() {
        if(checkPermissions()){ // if permission is granted we move on with the code
            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery,"Select Image With: "),CHOOSE_IMAGE_FROM_GALLERY); // StartActivityFromResult means it will Intent to an External Storage/Store (Gallery)
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
    // requestCode = What we sent ,,,, resultCode = what the user did (choose an image , canceled) ,,,, data = the data the user choose (the image in this case)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHOOSE_IMAGE_FROM_GALLERY:
                if(resultCode == RESULT_OK){ // if resultCode is RESULLT_OK it means the user has chosen an image
                    if (data != null && data.getData() != null){
                        // Here we make the code for what we want to do with the image
                        Toast.makeText(getApplicationContext(),"Image Has Been Chosen",Toast.LENGTH_LONG).show();
                        ImageUri = data.getData(); // Here We Save The Image We Choose In The Variabele ImageUri
                        Picasso.get().load(ImageUri).into(profileImage);


                        profileImage.buildDrawingCache();
                        Bitmap bitmap = profileImage.getDrawingCache();

                        Intent intent = new Intent(this, HomePageActivity.class);
                        intent.putExtra("BitmapImage", bitmap);
                    }
                }else if(resultCode == RESULT_CANCELED){ // if requestCode is RESULT_CANCELED it means the user has canceled
                    Toast.makeText(getApplicationContext(),"User Canceled",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    // This Function Will Upload The Image To FireStorage
    private void UploadToFireStorage() {

        //Progress Dialog means it will show a loading dialog until the image is uploaded
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        // This Child is the name of the File + the Milli Second So we can upload more than one image with different titles
        myStorageRef = myStorageRef.child(mAuth.getUid()).child("Images").child("photo_" + System.currentTimeMillis() + "." + getFileExtension());
        if (ImageUri != null){
            myStorageRef.putFile(ImageUri)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<UploadTask.TaskSnapshot> task) {
                            myStorageRef.getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String url = uri.toString();
//                                            uri2Show = uri.toString();
                                        }
                                    });
                        }
                    });
        }
    }

    // This Function Returns The Name Of The File Without Choosing it (Png , Jpg . ....)
    private String getFileExtension() {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(ImageUri));
    }

    //if permission is granted we go to the uploadImage Function to upload the image we want
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  PERMISSION_REQUEST_CODE:
                uploadImage();
        }
    }



    // ----------------------------------------------------------------------------ALL THE FIND_VIEW_BY_ID-----------------------------------------------------------------------------------------
    private void initViews() {
        userName = findViewById(R.id.userName_SignUp);
        userEmail = findViewById(R.id.userEmail_SignUp);
        userPassword = findViewById(R.id.userPassword_SignUp);
        userRePassword = findViewById(R.id.userRePassword_SignUp);
        male = findViewById(R.id.male_SignUp);
        female = findViewById(R.id.female_SignUp);
        btn_SignUp = findViewById(R.id.btnSignUp_SignUp_page);
        profileImage = findViewById(R.id.ProfileImg);

    }


    // -------------------------------------------------------------------EVERYTHING TO DO WITH SIGNING UP-----------------------------------------------------------------------------------------


    private void signup() {

        if (userPassword.getText().toString().equals(userRePassword.getText().toString())){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(getApplicationContext(), "sucess",
                            Toast.LENGTH_SHORT).show();

                    String gender;
                                if(male.isChecked())
                                    gender = "Male";
                                else
                                    gender = "Female";
                                Log.i("getOneeee","2");
                               Student student = new Student(userName.getText().toString(),
                                        userEmail.getText().toString(),
                                        userPassword.getText().toString(),
                                        gender );
                                Log.i("getOneeee","3");

                                FirebaseUser user = mAuth.getCurrentUser();
                                myRef.child(user.getUid()).setValue(student);
                                Log.i("getOneeee","before upload");

                                UploadToFireStorage();

                                Log.i("getOneeee","after upload");

                                Toast.makeText(SingupActivity.this, "Account Created.",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext() , MainActivity.class));

                }
            });
        }


//        if (userPassword.getText().toString().equals(userRePassword.getText().toString())){
//            mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString())
//                    .addOnCompleteListener(SingupActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            Log.i("getOneeee",task.toString());
//
//                            if (task.isSuccessful()) {
//                                Log.i("getOneeee","1");
//                                // Sign in success, update UI with the signed-in user's information
//                                String gender;
//                                if(male.isChecked())
//                                    gender = "Male";
//                                else
//                                    gender = "Female";
//                                Log.i("getOneeee","2");
//                                Student student = new Student(userName.getText().toString(),
//                                        userEmail.getText().toString(),
//                                        userPassword.getText().toString(),
//                                        gender );
//                                Log.i("getOneeee","3");
//
//                                FirebaseUser user = mAuth.getCurrentUser();
//                                myRef.child(user.getUid()).setValue(student);
//                                Log.i("getOneeee","before upload");
//
//                                UploadToFireStorage();
//
//                                Log.i("getOneeee","after upload");
//
//                                Toast.makeText(SingupActivity.this, "Account Created.",
//                                        Toast.LENGTH_SHORT).show();
//                                //finish();
//                                //startActivity(new Intent(SingupActivity.this , MainActivity.class));
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Toast.makeText(SingupActivity.this, "Authentication failed.",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//
//                            // ...
//                        }
//                    });
//        }else{
//            Toast.makeText(SingupActivity.this, "Passwords Do Not Match!",
//                    Toast.LENGTH_SHORT).show();
//        }
    }
}