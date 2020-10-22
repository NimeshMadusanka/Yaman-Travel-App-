package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidtutorialshub.loginregister.R;

public class PackageDetails extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);


        button = (Button) findViewById(R.id.book);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book();
            }
        });

        button = (Button) findViewById(R.id.bokk);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bokk();
            }
        });

    }

    public void book() {
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    }


    public void bokk() {
        Intent intent = new Intent(this, Payment.class);
        startActivity(intent);
    }
}
