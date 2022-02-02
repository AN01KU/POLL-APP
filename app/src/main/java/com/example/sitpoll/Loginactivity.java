package com.example.sitpoll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sitpoll.ui.showPolls.showPollsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;

public class Loginactivity extends AppCompatActivity {
    EditText email1;
    EditText password1;
    FirebaseAuth auth;
    Switch users;




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

       try {
           FirebaseUser currentUser = auth.getCurrentUser();
           if (currentUser != null) {
               currentUser.reload();
           }
       }catch (Exception q){
           Log.i("Login Error","Account Not Found");

       }
    }


   /* public void login(View view){


        if(username2.getText().toString().matches("") || password.getText().toString().matches("") ){
            Toast.makeText(this, "Username or Password required", Toast.LENGTH_SHORT).show();

        }
        else {
            ParseUser.logInInBackground(username2.getText().toString(), password.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(Loginactivity.this, "Logged In", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),mainvoteactivity.class);
                        startActivity(intent);

                    } else Toast.makeText(Loginactivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });


        }
    }*/


    public void loginUser(View view){





            String email1_txt = email1.getText().toString();
            String pass1_txt = password1.getText().toString();

            if (email1_txt.equals("") || pass1_txt.equals(""))
                Toast.makeText(this, "Empty password or email", Toast.LENGTH_SHORT).show();
            else if (pass1_txt.length() < 6) {
                Toast.makeText(this, "Password to short", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    auth.signInWithEmailAndPassword(email1_txt, pass1_txt).addOnCompleteListener(Loginactivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Loginactivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                if (users.isChecked()) {
                                    if (email1_txt.equals("Admin@gmail.com")) {
                                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                        startActivity(intent);
                                    } else
                                        Toast.makeText(Loginactivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                                } else {
                                    if (!email1_txt.equals("Admin@gmail.com")) {
                                        Intent intent = new Intent(getApplicationContext(), mainvoteactivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(Loginactivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        email1= findViewById(R.id.personName2);
        password1= findViewById(R.id.password2);
        auth=FirebaseAuth.getInstance();
        users= findViewById(R.id.switch1);

        users.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(users.isChecked()){
                    users.setText("Admin");
                }

            }
        });











    }
}