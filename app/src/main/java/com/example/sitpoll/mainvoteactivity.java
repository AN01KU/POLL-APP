package com.example.sitpoll;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sitpoll.ui.CreatePoll.CreatePollFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.parse.ParseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class mainvoteactivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainvoteactivity);
        auth=FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
      ///  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("SitApp");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);



//        String bigu= ParseUser.getCurrentUser().getUsername().toString();
        //bigu= bigu.substring(0,2);
      //  System.out.println(bigu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_createpoll, R.id.nav_showpolls)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(mainvoteactivity.this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(mainvoteactivity.this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainvoteactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== R.id.logout){
            Intent intent = new Intent(mainvoteactivity.this, MainActivity.class);
            FirebaseAuth.getInstance().signOut();
            if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                Toast.makeText(mainvoteactivity.this, "Sign Out Succesfull!", Toast.LENGTH_SHORT).show();
            }
            startActivity(intent);
        }

        /*switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(mainvoteactivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;


        }*/


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(mainvoteactivity.this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}