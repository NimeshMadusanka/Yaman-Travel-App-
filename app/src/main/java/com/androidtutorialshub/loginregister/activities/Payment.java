package com.androidtutorialshub.loginregister.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.view.View;
import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.Payments;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.regex.Pattern;

public class Payment extends AppCompatActivity {

    DatabaseHelper db;
    EditText name,address,NIC,amount,cardNo;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        name = (EditText) findViewById(R.id.namePay);
        address = (EditText) findViewById(R.id.addressPay);
        NIC = (EditText) findViewById(R.id.payNic);
        amount = (EditText) findViewById(R.id.payAmounts);
        cardNo = (EditText) findViewById(R.id.pay_status);
        db = new DatabaseHelper(this);
        addDetail();
    }


    public void addDetail() {
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (name.getText().toString().equals("")) {
                            name.setError("Enter the name");
                            Toast.makeText(getApplicationContext(), "Payment not successfull", Toast.LENGTH_LONG).show();

                        } else if (address.getText().toString().equals("")) {
                            address.setError("Enter your address");
                            Toast.makeText(getApplicationContext(), "Enter your address", Toast.LENGTH_LONG).show();
                        } else if (NIC.getText().toString().length() != 10) {
                            NIC.setError("Enter the NIC number");
                            Toast.makeText(getApplicationContext(), "payment not successful(NIC should have 10 charachters) ", Toast.LENGTH_LONG).show();
                        } else if (amount.getText().toString().equals("")) {
                            amount.setError("Enter your Amount");
                            Toast.makeText(getApplicationContext(), "Enter your address", Toast.LENGTH_LONG).show();
                        } else if (cardNo.getText().toString().length() != 12) {
                            cardNo.setError("Enter a valid card number");
                            Toast.makeText(getApplicationContext(), "Payment not successful", Toast.LENGTH_LONG).show();


                        } else {

                            Payments payment = new Payments();

                            payment.setUser_email(User.getUserEmail());
                            payment.setName(name.getText().toString());
                            payment.setAddress(address.getText().toString());
                            payment.setNic(NIC.getText().toString());
                            payment.setAmount(Double.parseDouble(amount.getText().toString()));
                            payment.setCard(cardNo.getText().toString());

                            boolean isInserted = db.insert_payment(payment);
                            if (isInserted) {
                                Toast.makeText(Payment.this, "Payment successful", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Payment.this, FeedbackPage.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Payment.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                            }


                        }
                    }
                });


            }

        });
    }
}
