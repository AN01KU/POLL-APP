package com.example.sitpoll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText email;
    Intent intent;
    FirebaseAuth auth;

    EditText prn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username= findViewById(R.id.personName);
        password= findViewById(R.id.password);
        prn= findViewById(R.id.prn1);
        email = findViewById(R.id.email);
        auth=FirebaseAuth.getInstance();

        intent= new Intent(getApplicationContext(),Loginactivity.class);



    }


    public void signUser(View view){

        String email_txt=email.getText().toString();
        String pass_txt=password.getText().toString();
        String name_txt=username.getText().toString();
        String prn_txt=prn.getText().toString();
        HashMap<String ,Object> map=new HashMap<>();

        if(email_txt.equals("") || pass_txt.equals("") || name_txt.equals("") || prn_txt.equals(""))
            Toast.makeText(this,"Enter all details",Toast.LENGTH_SHORT).show();
        else if(pass_txt.length()<6){
            Toast.makeText(this,"Password too short",Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                auth.createUserWithEmailAndPassword(email_txt, pass_txt).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            map.put("name", name_txt);
                            map.put("prn", prn_txt);
                            map.put("email", email_txt);
                            String user_uid = auth.getCurrentUser().getUid();
                            FirebaseDatabase.getInstance().getReference().child("UserData").child(user_uid).updateChildren(map);
                        }
                    }
                });
            }catch (Exception e){
                Toast.makeText(SignupActivity.this, "Account not Created", Toast.LENGTH_SHORT).show();
            }
        }

    }

  /*  public void signup(View view){

        if(username.getText().toString().matches("") || password.getText().toString().matches("") ){
            Toast.makeText(SignupActivity.this, "Username or Password required", Toast.LENGTH_SHORT).show();


        }
        else{
            ParseUser user= new ParseUser();
            System.out.println("Signup code");
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.put("PRN",prn.getText().toString());
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        System.out.println("PRN updated");
                    }
                    else  e.printStackTrace();
                }
            });

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Toast.makeText(SignupActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(SignupActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());

                    }
                }
            });
        }


    }*/



}