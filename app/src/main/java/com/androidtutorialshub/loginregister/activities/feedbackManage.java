package com.androidtutorialshub.loginregister.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Feedback;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class feedbackManage extends AppCompatActivity {

    Spinner spinner;

    TextView email,feedback,reply;

    EditText adminReply;

    Button replyAdmin,adminDelete;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_manage);

        spinner = (Spinner) findViewById(R.id.spinnerBooking);

        email = (TextView) findViewById(R.id.feed_emails);
        feedback = (TextView) findViewById(R.id.feed_users);
        reply = (TextView) findViewById(R.id.admin_reply);

        adminReply = (EditText) findViewById(R.id.feed_rply);

        replyAdmin = (Button) findViewById(R.id.admin_replay_feed);
        adminDelete = (Button) findViewById(R.id.admin_delete_feed);

        db = new DatabaseHelper(this);

        ArrayList<Feedback> allModels1 = db.readAllFeedbacks();

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
                ArrayList<Feedback> allModels = db.selectedFeedback(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++){

                    email.setText(allModels.get(x).getUser_email());
                    feedback.setText(allModels.get(x).getFeedback());
                    reply.setText(allModels.get(x).getReply());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        replyAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!adminReply.getText().toString().equals("")){

                    if (!spinner.getSelectedItem().toString().equals("")){

                        if(db.feedbackReply(spinner.getSelectedItem().toString(),adminReply.getText().toString())){
                            Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Please Type !",Toast.LENGTH_SHORT).show();
                }

            }
        });

        adminDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (!spinner.getSelectedItem().toString().equals("")){

                    if(db.feedbackDelete(spinner.getSelectedItem().toString())){
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
