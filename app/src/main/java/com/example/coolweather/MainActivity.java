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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> dataList=new ArrayList<>();
    private List<String> province= Arrays.asList(new String[]{"浙江省","江苏省"});
    private List<String> city_01=Arrays.asList(new String[]{"南京","无锡","南通"});
    private List<String> city_02=Arrays.asList(new String[]{"杭州","温州","台州","萧山","绍兴","嘉兴"});
    private List<String>county_01= Arrays.asList(new String[]{"六合","玄武","秦淮","白下"});
    private List<String>county_02 = Arrays.asList(new String[]{"瓯海区","鹿城区","龙湾","永嘉"});
    private List<String>county_03= Arrays.asList(new String[]{"西湖区","下沙区"});

    private int level=1;
    private Button backButton=null;
    private ListView listView=null;
    private ArrayAdapter adapter;
    private  int  selectedProvinceIndex=0;
    private int selectedCityIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.backButton= (Button) findViewById(R.id.button);
        this.listView = (ListView) findViewById(R.id.list_view);
        this.dataList.addAll(this.province);
        adapter=new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   if (level==1){
                       selectedProvinceIndex=position;
                       showCities( selectedProvinceIndex);
                   }else if(level==2){
                       selectedCityIndex=position;
                       showCounties(selectedCityIndex);
                   }
               }
               });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level==3){
                  showCities(selectedProvinceIndex);
                }else if(level==2) {
                    level=1;
                    MainActivity.this.dataList.clear();
                    MainActivity.this.dataList.addAll(province);
                    MainActivity.this.adapter.notifyDataSetChanged();
                }
            }
       });
    }
    private void showCities(int position) {
        level = 2;
        this.backButton.setVisibility(View.VISIBLE);
        this.dataList.clear();
        if ("江苏省".equals(this.province.get(position))) {
            this.dataList.addAll(city_01);
        }else{
            this.dataList.addAll(city_02);
        }
        this.adapter.notifyDataSetChanged();
    }

   private void showCounties(int position2){
        level=3;
        this.dataList.clear();
       if ("南京".equals(this.city_01.get(position2))) {
           this.dataList.addAll(county_01);
       }else if("温州".equals(this.city_02.get(position2))){
           this.dataList.addAll(county_02);
       }else if("杭州".equals(this.city_02.get(position2))){
           this.dataList.addAll(county_03);
       }
       this.adapter.notifyDataSetChanged();
    }

}
