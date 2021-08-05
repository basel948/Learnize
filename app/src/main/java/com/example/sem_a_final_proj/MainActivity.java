package com.example.sem_a_final_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText userEmail , userPassword;
    Button btn_login , btn_register;
    private  FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this,HomePageActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //Button
        btn_login = findViewById(R.id.btnLogin_login_page);
        btn_register = findViewById(R.id.btnSignUp_login_page);
        userEmail = findViewById(R.id.loginEmail);
        userPassword = findViewById(R.id.loginPassword);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPassword.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(MainActivity.this , HomePageActivity.class));
                                }
                                else{
                                    Toast.makeText(MainActivity.this , "Email Or PassWord Is Incorrect" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , SingupActivity.class));
            }
        });
    }
}