package com.example.sitpoll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ViewStudentInfo extends AppCompatActivity {
    TextView emailTextView;
    TextView nameTextView;
    TextView prnTextView;
    FirebaseAuth auth;
    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_info);
        nameTextView=findViewById(R.id.nameTextView);
        emailTextView=findViewById(R.id.emailTextView);
        prnTextView=findViewById(R.id.prnTextView);


        auth=FirebaseAuth.getInstance();

        Intent intent = getIntent();


        String from=intent.getStringExtra("from");

        String userUuid=intent.getStringExtra("username");

        FirebaseDatabase.getInstance().getReference().child("UserData").child(userUuid).child("name").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
               String name=dataSnapshot.getValue().toString();
               nameTextView.setText(name);
            }
        });


        FirebaseDatabase.getInstance().getReference().child("UserData").child(userUuid).child("email").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String email=dataSnapshot.getValue().toString();
                emailTextView.setText(email);
            }
        });

        FirebaseDatabase.getInstance().getReference().child("UserData").child(userUuid).child("prn").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String prn=dataSnapshot.getValue().toString();
                prnTextView.setText(prn);
            }
        });

        listView=findViewById(R.id.pollsCreatedListView);

        databaseReference =FirebaseDatabase.getInstance().getReference().child("Polls");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(ViewStudentInfo.this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                String tempEmail=snapshot.child("from").getValue().toString();
                if(tempEmail.equals(from)){
                    String value=snapshot.child("question").getValue().toString();
                    arrayList.add(value);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });







    }
}