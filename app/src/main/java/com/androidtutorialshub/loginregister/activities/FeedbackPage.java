package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Feedback;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

public class FeedbackPage extends AppCompatActivity {

    Button button;
    DatabaseHelper db;
    EditText email,feedback;
    Button Submit;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        feedback = (EditText) findViewById(R.id.editText5);
        email = (EditText) findViewById(R.id.editText7);
        back = (Button) findViewById(R.id.back);

        db = new DatabaseHelper(this);
        addDetails();

        button = (Button) findViewById(R.id.skip);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skip();
            }
        });



    }

    public void addDetails() {
        Submit = (Button) findViewById(R.id.admin_delete_feed);
        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Feedback feedbacks = new Feedback();

                feedbacks.setUser_email(email.getText().toString());
                feedbacks.setFeedback(feedback.getText().toString());

                if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    if (!(email.getText().toString().equals("")&&feedback.getText().toString().equals(""))){
                        boolean isInserted = db.insert_feedback(feedbacks);
                        if (isInserted) {
                            Toast.makeText(FeedbackPage.this,"Thank u for your feedback", Toast.LENGTH_LONG).show();
                        } else if (email.getText().toString().equals("")) {
                            email.setError("Enter your email");
                            Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(FeedbackPage.this,"Data is not inserted", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(FeedbackPage.this,"required!", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(FeedbackPage.this,"Wrong Email", Toast.LENGTH_LONG).show();
                }

            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackPage.this, Final.class);
                startActivity(intent);
            }
        });


    }

    public void skip() {
        Intent intent = new Intent(this, Final.class);
        startActivity(intent);
    }


}