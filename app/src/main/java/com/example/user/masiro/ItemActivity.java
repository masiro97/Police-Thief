package com.example.user.masiro;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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

        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Rodedown");
        dlg.setMessage("전체 삭제를 하면 level과 point가 초기화 됩니다\n" +
                "계속 진행하시겠습니까?");
        dlg.setNegativeButton("Close",null);
        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ListItem listitem = new ListItem(getApplicationContext(),"ItemLog.db",null,1);
                listitem.deleteAll();
                info.clear();
                adapter.notifyDataSetChanged();

            }
        });
        dlg.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setTitle("Rodedown");
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
