package com.example.user.masiro;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    ListView list;
    ArrayList<Item> info = new ArrayList<Item>();
    NAdapter adapter;

    public void OnButton(View v){

        ListItem listitem = new ListItem(getApplicationContext(),"ItemLog.db",null,1);
        listitem.deleteAll();
        info.clear();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        list = (ListView)findViewById(R.id.listview);

        ListItem listitem = new ListItem(getApplicationContext(),"ItemLog.db",null,1);

        String result = listitem.getResult();
        String[] itemstring = result.split("\n");

        for(int i = 0 ; i < itemstring.length; i++){
            String[] index = itemstring[i].split(":");
            String[] sub = index[1].split(",");
            Item item = new Item(sub[0],Integer.parseInt(sub[1]));
            info.add(item);
        }

        adapter = new NAdapter(info,this);
        list.setAdapter(adapter);

    }
}
