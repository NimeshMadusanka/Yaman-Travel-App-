package com.androidtutorialshub.loginregister.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.model.User;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageUsers extends AppCompatActivity {

    Spinner spinner;

    Button edit,delete;

    DatabaseHelper db;

    TextInputEditText name,email,password,cpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        edit = (Button) findViewById(R.id.userEdit);

        delete = (Button) findViewById(R.id.userDelete);

        name = (TextInputEditText) findViewById(R.id.editUserName);

        email = (TextInputEditText) findViewById(R.id.editUserEmail);

        password = (TextInputEditText) findViewById(R.id.editUserPassword);

        cpassword = (TextInputEditText) findViewById(R.id.editUser_C_Password);

        spinner = (Spinner) findViewById(R.id.spinnerBooking);

        db = new DatabaseHelper(this);

        users();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<User> allModels = db.selectedUser(spinner.getSelectedItem().toString());

                for(int x=0;allModels.size()>x;x++){

                    name.setText(allModels.get(x).getName());
                    email.setText(allModels.get(x).getEmail());
                    password.setText(allModels.get(x).getPassword());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(name.getText().toString().equals("")&&email.getText().toString().equals("")&&password.getText().toString().equals("")&&cpassword.getText().toString().equals(""))){

                    if (password.getText().toString().equals(cpassword.getText().toString())){

                        User user = new User();

                        user.setId(Integer.parseInt(spinner.getSelectedItem().toString()));
                        user.setName(name.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setPassword(password.getText().toString());

                        if(db.updateUsers(user)){
                            Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"password & confirm password not equal !",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Required !",Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spinner.getSelectedItem().toString().equals("")){
                    if(db.deleteUsers(spinner.getSelectedItem().toString())){
                        Toast.makeText(getApplicationContext(),"Success !",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Error !",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Select one !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void users(){
        ArrayList<User> allModels1 = db.readAllUsers();

        final List<String> list1 = new ArrayList<String>();

        for(int i=0;allModels1.size()>i;i++){

            list1.add(Integer.toString(allModels1.get(i).getId()));

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list1);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);
    }
}
