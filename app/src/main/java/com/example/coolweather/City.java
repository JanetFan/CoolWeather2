package com.example.coolweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class City extends AppCompatActivity {
    private String[] city_01={
            "北京"
    };
    private String[] city_02={
            "金华","台州","温州","宁波"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        String p= getIntent().getStringExtra("province");
        String[] datas=null;
        if ("北京".equals(p)){
            datas=city_01;
        }else if ("浙江省".equals(p)){
            datas=city_02;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                City.this,android.R.layout.simple_list_item_1,datas
        );
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
