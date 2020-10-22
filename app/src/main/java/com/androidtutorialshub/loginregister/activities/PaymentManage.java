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
import com.androidtutorialshub.loginregister.model.Payments;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PaymentManage extends AppCompatActivity {

    DatabaseHelper db;

    Spinner spinner;

    TextView name,address,nic,amount,cardNo,status;

    Button verify,refund;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_manage);

        spinner = (Spinner) findViewById(R.id.spinnerPayments);

        name = (TextView) findViewById(R.id.pay_name);
        address = (TextView) findViewById(R.id.pay_address);
        nic = (TextView) findViewById(R.id.pay_Nic);
        amount = (TextView) findViewById(R.id.pay_amount);
        cardNo = (TextView) findViewById(R.id.pay_card);
        status = (TextView) findViewById(R.id.pay_status);

        verify = (Button) findViewById(R.id.pay_verify);
        refund = (Button) findViewById(R.id.pay_refund);

        db = new DatabaseHelper(this);

        spinnerClass();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Payments> allModels = db.selectedPayment(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++){

                    name.setText(allModels.get(x).getName());
                    address.setText(allModels.get(x).getAddress());
                    nic.setText(allModels.get(x).getNic());
                    amount.setText(Double.toString(allModels.get(x).getAmount()));
                    cardNo.setText(allModels.get(x).getCard());
                    status.setText(allModels.get(x).getStatus());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.updatePayment(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){


                    if(db.refundPayment(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                        spinnerClass();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Please Select !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void spinnerClass(){
        ArrayList<Payments> allModels1 = db.readAllPayments();

        final List<String> list1 = new ArrayList<String>();

        for(int i=0;allModels1.size()>i;i++){

            list1.add(Integer.toString(allModels1.get(i).getID()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
    }

}
