package com.example.sitpoll.ui.showPolls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sitpoll.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ShowOnePoll extends AppCompatActivity {
    ListView listView;
    FirebaseAuth auth;
    ArrayAdapter arrayAdapter;
    Intent intent;

    ArrayList<String> results = new ArrayList<>();
    ArrayAdapter arrayAdapter1;



    ArrayList<String> options = new ArrayList<>();

    public void selectans(int position){

        listView.setItemChecked(position,true);

        String quesUuid= intent.getStringExtra("quesUuid");
        String user_uid= auth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference().child("UserData").child(user_uid).child("pollsanswered").child(quesUuid).setValue(position);

    }

    public void saveanswers(int position){

        String user_uid= auth.getCurrentUser().getUid();
        String quesUuid= intent.getStringExtra("quesUuid");

        switch (position){
            case 0:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("A").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("A").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("A").setValue(1);
                        }


                    }
                });


                break;
            }
            case 1:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("B").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("B").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("B").setValue(1);
                        }


                    }
                });

                break;
            }
            case 2:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("C").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("C").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("C").setValue(1);
                        }


                    }
                });


                break;
            }
            case 3:{
                FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").child("D").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot snapshot) {
                        if(snapshot.getValue()!=null) {
                            long values = (long) snapshot.getValue();
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("D").setValue(values + 1);
                        }
                        else {
                            FirebaseDatabase.getInstance()
                                    .getReference().child("Polls").child(quesUuid).child("VoteCount").child("D").setValue(1);
                        }


                    }
                });

                break;

            }
            default: {
                System.out.println("no values saved");
            break;
            }


        }


}

    public  void  disablelist(){
        listView.setOnItemClickListener(null);


    }

    public void showresult(){
        String user_uid= auth.getCurrentUser().getUid();
        String quesUuid= intent.getStringExtra("quesUuid");
        results.clear();

        FirebaseDatabase.getInstance().getReference().child("Polls").child(quesUuid).child("VoteCount").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                long totalvote=0;
                results.add(0,"Total Votes are 0 ");
                results.add(1,"Votes for option A is 0 ");
                results.add(2,"Votes for option B is 0 ");
                results.add(3,"Votes for option C is 0 ");
                results.add(3,"Votes for option D is 0 ");

                if(snapshot.child("A").getValue()!=null ){
                    String vote= "Votes for option A is  "+snapshot.child("A").getValue().toString();
                    totalvote +=(long) snapshot.child("A").getValue();
                    results.set(1,vote);
                }
                if(snapshot.child("B").getValue()!=null){
                    String vote= "Votes for option B is  "+snapshot.child("B").getValue().toString();
                    totalvote += (long) snapshot.child("B").getValue();
                    results.set(2,vote);

                }
                if(snapshot.child("C").getValue()!=null){
                    String vote= "Votes for option C is  "+snapshot.child("C").getValue().toString();
                    totalvote +=(long) snapshot.child("C").getValue();
                    results.set(3,vote);


                }
                if(snapshot.child("D").getValue()!=null) {
                    String vote1= "Votes for option D is  "+snapshot.child("D").getValue().toString();
                    totalvote += (long) snapshot.child("D").getValue();
                    results.set(4,vote1);

                }
                String votes= "Total Votes are  "+ totalvote;

                results.set(0,votes);

                arrayAdapter1.notifyDataSetChanged();

            }
        });


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_poll);
        TextView questiontopic= findViewById(R.id.textView5);


        TextView from= findViewById(R.id.textView7);
        listView = findViewById(R.id.listview1);
        intent = getIntent();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        ArrayList<String> options = new ArrayList<>();
        auth= FirebaseAuth.getInstance();
        String user_uid= auth.getCurrentUser().getUid();
        ListView listView1= findViewById(R.id.resultlist);

        arrayAdapter1= new ArrayAdapter(this, android.R.layout.simple_list_item_1,results);
        listView1.setAdapter(arrayAdapter1);
        Intent intent = getIntent();
        String quesUuid= intent.getStringExtra("quesUuid");
         arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice,options);
        FirebaseDatabase.getInstance().getReference()
                .child("Polls").child(quesUuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                options.clear();
                questiontopic.setText(Objects.requireNonNull(snapshot.child("question").getValue()).toString());
                from.setText("From- "+Objects.requireNonNull(snapshot.child("from").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option1").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option2").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option3").getValue()).toString());
                options.add(Objects.requireNonNull(snapshot.child("option4").getValue()).toString());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

        FirebaseDatabase.getInstance().getReference().child("UserData").child(user_uid).child("pollsanswered").child(quesUuid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    long option= (long) snapshot.getValue();
                    listView.setItemChecked((int) option,true);
                    listView.getChildAt((int) option).setBackground(getResources().getDrawable(R.drawable.optionbg));


                    disablelist();


                }

            }
        });

        Button button= findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showresult();


            }
        });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    listView.getChildAt(position).setBackground(getResources().getDrawable(R.drawable.mainscreen));
                    selectans(position);
                    saveanswers(position);
                    disablelist();


                }
            });




    }

}
