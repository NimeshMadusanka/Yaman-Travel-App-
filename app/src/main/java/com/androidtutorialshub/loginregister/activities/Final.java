package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidtutorialshub.loginregister.R;
public class Final extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        button = (Button) findViewById(R.id.page);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page();
            }
        });
    }

    public void page() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
