package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidtutorialshub.loginregister.R;

public class Kanneliya extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanneliya);

        button = (Button) findViewById(R.id.pkgDetails);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pkgDetails();
            }
        });
    }

    public void pkgDetails() {
        Intent intent = new Intent(this, PackageDetails.class);
        startActivity(intent);
    }
}
