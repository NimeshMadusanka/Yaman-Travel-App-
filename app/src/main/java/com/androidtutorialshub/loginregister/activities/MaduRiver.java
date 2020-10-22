package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidtutorialshub.loginregister.R;
public class MaduRiver extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madu_river);

        button = (Button) findViewById(R.id.pkgMadu);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pkgMadu();
            }
        });
    }


    public void pkgMadu() {
        Intent intent = new Intent(this, PackageDetails.class);
        startActivity(intent);
    }
}
