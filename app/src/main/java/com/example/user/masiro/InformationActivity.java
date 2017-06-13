package com.example.user.masiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class InformationActivity extends AppCompatActivity {

    TextView id,enc,age,email,name,birth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setTitle("Rodedown");

        Intent intent = getIntent();
        String info = intent.getStringExtra("information");

        String[] userinfo = info.split(",");

        id = (TextView)findViewById(R.id.textViewid);
        enc = (TextView)findViewById(R.id.textViewnick);
        name = (TextView)findViewById(R.id.textViewname);
        age = (TextView)findViewById(R.id.textViewage);
        email = (TextView)findViewById(R.id.textViewemail);
        birth = (TextView)findViewById(R.id.textViewbirth);

        id.setText("USER ID : "+ userinfo[0]);
        enc.setText("USER ENCODE_ID : " + userinfo[1]);
        name.setText("USER NAME : " + userinfo[2]);
        age.setText("USER AGE : " + userinfo[3]);
        email.setText("USER EMAIL : " + userinfo[4]);
        birth.setText("USER BIRTH : " + userinfo[5]);

    }
}
