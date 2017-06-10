package com.example.user.masiro;

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
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by User on 2017-06-10.
 */

public class MainActivity extends AppCompatActivity {

    private final String[] navItems = {"Brown","Cadet Blue","Dark Olive Green", "Dark Orange","Golden Rod"};
    private ListView lvNavList;
    private FrameLayout flContainer;
    private DrawerLayout dlDrawer;
    private Button btn;

    @Override
    public void onBackPressed() {
        if(dlDrawer.isDrawerOpen(lvNavList)) dlDrawer.closeDrawer(lvNavList);
        else super.onBackPressed();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding_menu);

        lvNavList = (ListView)findViewById(R.id.lv_activity_main_nav_list);
        flContainer = (FrameLayout)findViewById(R.id.fl_activity_main_container);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlDrawer.openDrawer(lvNavList);
            }
        });

        dlDrawer = (DrawerLayout)findViewById(R.id.dl_activity_main_drawer);
        lvNavList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,navItems));
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override

        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            switch (position) {

                case 0:

                    flContainer.setBackgroundColor(Color.parseColor("#A52A2A"));
                    break;

                case 1:

                    flContainer.setBackgroundColor(Color.parseColor("#5F9EA0"));
                    break;

                case 2:

                    flContainer.setBackgroundColor(Color.parseColor("#556B2F"));
                    break;

                case 3:

                    flContainer.setBackgroundColor(Color.parseColor("#FF8C00"));
                    break;

                case 4:

                    flContainer.setBackgroundColor(Color.parseColor("#DAA520"));
                    break;

            }

            dlDrawer.closeDrawer(lvNavList);
        }

    }

}
