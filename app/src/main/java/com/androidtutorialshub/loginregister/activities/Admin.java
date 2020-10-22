package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidtutorialshub.loginregister.R;

public class Admin extends AppCompatActivity {

    TextView userManage,adminPay,feedAdmin,bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminPay = (TextView) findViewById(R.id.admin_pay);
        userManage = (TextView) findViewById(R.id.userManage);
        feedAdmin = (TextView) findViewById(R.id.feedAdmin);
        bookings =(TextView) findViewById(R.id.BookingManage);

        userManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, ManageUsers.class));

            }
        });

        adminPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, PaymentManage.class));

            }
        });

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, BookingManage.class));

            }
        });

        feedAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, feedbackManage.class));

            }
        });

    }
}
