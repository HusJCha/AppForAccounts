package com.example.husain.accountsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class HomeActivity extends AppCompatActivity
{
    Button aaa;
    ListView lv;
    DBHelper dbh;
    ArrayList<HashMap<String,String>> list;
    FrameLayout footerLayout;
    ArrayList<String> acc_nm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbh=new DBHelper(getApplicationContext(),"mydatabse",null,1);
        lv = (ListView) findViewById(R.id.lv_of_list);
        footerLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.footer_layout,null);
        aaa = (Button) footerLayout.findViewById(R.id.btn_aaa);
        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ShowActivity.class);
                i.putExtra("acc_nm","");
                i.putExtra("status","new");
                startActivity(i);
            }
        });
        list = dbh.getAllRecord();
        lv.setAdapter(new getAllRecordAdapterActivity(this,list));
        lv.addFooterView(footerLayout);
        openList();
    }

    private void openList()
    {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos=position;
                HashMap<String,String> map = list.get(pos);
                String acc_nm = map.get("acc_nm");
                Intent i=new Intent(HomeActivity.this,ShowActivity.class);
                i.putExtra("acc_nm",acc_nm);
                i.putExtra("status","old");
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
