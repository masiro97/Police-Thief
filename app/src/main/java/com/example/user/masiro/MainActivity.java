package com.example.user.masiro;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by User on 2017-06-10.
 */

public class MainActivity extends AppCompatActivity {


    Button btn;
    EditText et;
    SQLiteDatabase database = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);

        btn = (Button)findViewById(R.id.button);
        et = (EditText)findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean delete = deleteDatabase(et.getText().toString());
                if (delete) {

                    database = null;
                }

            }
        });

    }


}
