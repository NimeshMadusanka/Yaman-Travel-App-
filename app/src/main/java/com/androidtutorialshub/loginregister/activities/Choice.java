package com.androidtutorialshub.loginregister.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Booking;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.regex.Pattern;

public class Choice extends AppCompatActivity {


    DatabaseHelper db;
    EditText location,persons,days,email;
    Button btn123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        location = (EditText) findViewById(R.id.booking_loc);
        persons = (EditText) findViewById(R.id.booking_per);
        days = (EditText) findViewById(R.id.booking_days);
        email = (EditText) findViewById(R.id.booking_status);
        db = new DatabaseHelper(this);
        addDetail();

    }


    public void addDetail() {
        btn123 = (Button) findViewById(R.id.admin_approved);
        btn123.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                btn123.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (location.getText().toString().equals("")) {
                            location.setError("Enter the location");
                            Toast.makeText(getApplicationContext(), "Choice not successfull", Toast.LENGTH_LONG).show();
                        } else if (persons.getText().toString().equals("")) {
                            persons.setError("Enter your persons");
                            Toast.makeText(getApplicationContext(), "Enter your persons", Toast.LENGTH_LONG).show();
                        } else if (days.getText().toString().equals("")) {
                            days.setError("Enter your days");
                            Toast.makeText(getApplicationContext(), "Enter your days", Toast.LENGTH_LONG).show();
                        } else if (email.getText().toString().equals("")) {
                            email.setError("Enter your email");
                            Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_LONG).show();

                        } else {

                            Booking booking = new Booking();

                            booking.setLocation(location.getText().toString());
                            booking.setPerson(Integer.parseInt(persons.getText().toString()));
                            booking.setDays(Integer.parseInt(days.getText().toString()));
                            booking.setEmail(email.getText().toString());

                            if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                                boolean isInserted = db.insert_booking(booking);
                                if (isInserted) {
                                    Toast.makeText(Choice.this, "Your Choice successfully Add", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(Choice.this, MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(Choice.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                                }
                            }else {
                                Toast.makeText(Choice.this, "wrong email", Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                });


            }

        });
    }
}
