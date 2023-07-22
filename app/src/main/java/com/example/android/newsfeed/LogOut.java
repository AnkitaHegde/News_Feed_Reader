package com.example.android.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.BoringLayout;
import android.widget.Toast;

public class LogOut extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        SharedPreferences prefs = getSharedPreferences("logged", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean isLoggedIn= prefs.getBoolean("isLoggedIn", false);
        boolean email=prefs.getBoolean("isLoggedEmail",false);
        System.out.println("-----email: "+email+"------- isLogged: "+isLoggedIn);
        if(email==true && isLoggedIn==true){
            editor.clear();
            Intent logOutIntent=new Intent(this,welcome.class);
            startActivity(logOutIntent);

        }
        else{
            Toast.makeText(this,"No User Logged In",Toast.LENGTH_LONG).show();
        }

    }
}