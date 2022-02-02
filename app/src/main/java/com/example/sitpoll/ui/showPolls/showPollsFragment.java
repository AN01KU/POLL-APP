package com.example.sitpoll.ui.showPolls;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sitpoll.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class showPollsFragment extends Fragment {





    private showpollsViewModel showpollsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        showpollsViewModel =
                new ViewModelProvider(this).get(showpollsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_showpolls, container, false);

         ArrayList<String> questions = new ArrayList<String>();
        ArrayList<String> quesUuid = new ArrayList<String>();

        ListView listView = root.findViewById(R.id.listview1);
        questions.clear();
        quesUuid.clear();
        ArrayAdapter arrayadpapter= new ArrayAdapter( getActivity(),android.R.layout.simple_list_item_1,questions);
        FirebaseDatabase.getInstance().getReference().child("Polls").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String question = snapshot.child("question").getValue().toString();
                questions.add(question);
                arrayadpapter.notifyDataSetChanged();
                quesUuid.add(snapshot.getKey().toString());


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        arrayadpapter.notifyDataSetChanged();
        listView.setAdapter(arrayadpapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getActivity(),ShowOnePoll.class);
                intent.putExtra("quesUuid",quesUuid.get(position));
                startActivity(intent);

            }
        });

        showpollsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        return root;

    }


}