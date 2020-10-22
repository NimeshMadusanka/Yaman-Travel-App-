package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.androidtutorialshub.loginregister.R;
import android.support.v4.widget.NestedScrollView;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;

import com.androidtutorialshub.loginregister.helpers.InputValidation;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnKanneliya);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnKanneliya();
            }
        });

        button = (Button) findViewById(R.id.btnMeemure);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMeemure();
            }
        });

        button = (Button) findViewById(R.id.btnMaduriver);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMaduriver();
            }
        });


        button = (Button) findViewById(R.id.btnHikka);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHikka();
            }
        });


        button = (Button) findViewById(R.id.choice);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice();
            }
        });




    }
    public void btnKanneliya(){
        Intent intent = new Intent(this,Kanneliya.class);
        startActivity(intent);
    }

    public void btnMeemure(){
        Intent intent = new Intent(this,Meemure.class);
        startActivity(intent);
    }

    public void btnMaduriver(){
        Intent intent = new Intent(this,MaduRiver.class);
        startActivity(intent);
    }

    public void btnHikka(){
        Intent intent = new Intent(this,Hikka.class);
        startActivity(intent);
    }

    public void choice(){
        Intent intent = new Intent(this,Choice.class);
        startActivity(intent);
    }




}
