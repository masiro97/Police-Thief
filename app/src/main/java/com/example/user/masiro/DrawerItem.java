package com.example.user.masiro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DrawerItem extends AppCompatActivity {

    ListView list;
    ArrayList<Item> info = new ArrayList<Item>();
    NAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_item);

        ListItem listitem = new ListItem(getApplicationContext(),"ItemLog.db",null,1);
        String result = listitem.getResult();
        String[] itemstring = result.split("\n");

        for(int i = 0 ; i < itemstring.length; i++){
            String[] index = itemstring[i].split(":");
            String[] sub = index[1].split(",");
            Item item = new Item(sub[0],Integer.parseInt(sub[1]));
//            info.add(item);
//            Toast.makeText(getApplicationContext(),Integer.toString(item.getPoint()),Toast.LENGTH_SHORT).show();
        }

        list = (ListView)findViewById(R.id.listview);
        adapter = new NAdapter(info,this);
        list.setAdapter(adapter);
    }
}
