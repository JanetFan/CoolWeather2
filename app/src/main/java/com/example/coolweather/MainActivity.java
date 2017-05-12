package com.example.coolweather;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> sources=new ArrayList<>();
    private List<String> province= Arrays.asList(new String[]{"北京市","浙江省","江苏省"});
    private List<String> city_01=Arrays.asList(new String[]{"北京"});
    private List<String> city_02=Arrays.asList(new String[]{"杭州","金华","宁波","温州","台州","萧山","绍兴","嘉兴"});
    private String[] country = new String[]{"瓯海区","鹿城区","龙湾","永嘉"};
    private int level=1;
    private Button backButton=null;
    private ListView listView=null;

    void showCities(String provinceName) {
        level = 2;
        this.backButton.setVisibility(View.VISIBLE);
        if ("北京市".equals(provinceName)) {
            ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, city_01);
            listView.setAdapter(adapter);
        } else if ("浙江省".equals(provinceName)) {
            ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, city_02);
            listView.setAdapter(adapter);
        }
    }
        void showCounties(String cityName){
        level=3;
        backButton.setVisibility(View.VISIBLE);
        if("温州市".equals(cityName)){
            ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,city_02);
            listView.setAdapter(adapter);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.backButton= (Button) findViewById(R.id.button);
        backButton.setVisibility(View.GONE);//返回按钮在省级页面隐藏
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,province);
        this.listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
/**
 * 省级页面到市级页面调用同一个页面
 */

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //Toast.makeText(MainActivity.this,province.get(position),Toast.LENGTH_SHORT).show();
                   if (level==1){
                       showCities(MainActivity.this.province.get(position));
                   }else if(level==2){
                       showCounties(MainActivity.this.city_01.get(position));

                   }else{
                       //显示天气
                   }
               }
               });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton.setVisibility(View.GONE);
                if (level==3){
                    level=2;
                    ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,province);
                    listView.setAdapter(adapter);
                }else {
                    level=2;
                    backButton.setVisibility(View.VISIBLE);
                }

            }

       });

    }
}
