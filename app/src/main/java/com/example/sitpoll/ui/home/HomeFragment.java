package com.example.sitpoll.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sitpoll.MainActivity;
import com.example.sitpoll.R;
import com.example.sitpoll.ViewStudentInfo;
import com.example.sitpoll.ui.CreatePoll.CreatePollFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    public void userlist1(){

        Intent intent1= new Intent(getActivity(),ViewStudentInfo.class);
        intent1.putExtra("username",FirebaseAuth.getInstance().getCurrentUser().getUid());
        intent1.putExtra("from",FirebaseAuth.getInstance().getCurrentUser().getEmail());
        startActivity(intent1);


    }



    public void onBackPressed() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);

                    }
                }).create().show();

    }




    private HomeViewModel homeViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.bigusername);
        final TextView textView1 = root.findViewById(R.id.username);
        final Button userinfo = root.findViewById(R.id.button7);
        userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlist1();
            }
        });

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),createpollActivity.class);
                startActivity(intent);

            }
        });

        root.requestFocus();
        root.setFocusableInTouchMode(true);



        root.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        onBackPressed();
                        return true;
                    }
                }
                return false;
            }
        });









     /*   FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance()
                .getCurrentUser().getUid()).child("name").get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(!task.isSuccessful()){
                            Log.i("Firebase",""+ task.getException());}               /// COde to get Info from realtime database
                        else {
                            String username= task.getResult().getValue().toString();
                            System.out.println(username);
                            System.out.println("heloooo");
                            textView.setText(username.substring(0,1));
                            textView1.setText(username);



                        }

                    }
                }
                );*/

        FirebaseDatabase.getInstance().getReference().child("UserData").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username= (String) snapshot.getValue();
                System.out.println(username);
                assert username != null;
                textView.setText(username.substring(0,1));
                textView1.setText(username);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


                //textView.setText(s);
            }
        });
        return root;
    }
}