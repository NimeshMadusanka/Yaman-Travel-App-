package com.androidtutorialshub.loginregister.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Booking;
import com.androidtutorialshub.loginregister.model.Payments;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class BookingManage extends AppCompatActivity {

    DatabaseHelper db;

    Spinner spinner;

    TextView location,person,days,email,status;

    Button approved,reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_manage);

        spinner = (Spinner) findViewById(R.id.spinnerBooking);

        location = (TextView) findViewById(R.id.booking_loc);
        person = (TextView) findViewById(R.id.booking_per);
        days = (TextView) findViewById(R.id.booking_days);
        email = (TextView) findViewById(R.id.booking_email);
        status = (TextView) findViewById(R.id.booking_status);

        approved = (Button) findViewById(R.id.admin_approved);
        reject = (Button) findViewById(R.id.admin_reject);

        db = new DatabaseHelper(this);

        ArrayList<Booking> allModels1 = db.readAllBookings();

        final List<String> list1 = new ArrayList<String>();

        for(int i=0;allModels1.size()>i;i++){

            list1.add(Integer.toString(allModels1.get(i).getID()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Booking> allModels = db.selectedBookings(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++){

                    location.setText(allModels.get(x).getLocation());
                    person.setText(Integer.toString(allModels.get(x).getPerson()));
                    days.setText(Integer.toString(allModels.get(x).getDays()));
                    email.setText(allModels.get(x).getEmail());
                    status.setText(allModels.get(x).getStatus());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.updateBooking(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){

                    if(db.bookingCancel(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
