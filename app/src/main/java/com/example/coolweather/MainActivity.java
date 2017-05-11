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
//    private String[] data={
//            "北京市","江苏省","浙江省","天津市","上海市","重庆市","河北省","山西省","辽宁省",
//            "吉林省","黑龙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省",
//            "四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古自治区","广西壮族自治区","西藏自治区","宁夏回族自治区",
//            "新疆维吾尔自治区","香港特别行政区","澳门特别行政区"
//    };
    private List<String> sources=new ArrayList<>();
    private List<String> province= Arrays.asList(new String[]{"北京市","浙江省","江苏省"});
    private List<String> city_01=Arrays.asList(new String[]{"北京"});
    private List<String> city_02=Arrays.asList(new String[]{"杭州","金华","宁波","温州","台州","萧山","绍兴","嘉兴"});


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button= (Button) findViewById(R.id.button);
        button.setVisibility(View.GONE);//返回按钮在省级页面隐藏
        //button.setVisibility(View.VISIBLE);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,province);
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
/**
 * 省级页面到市级页面调用同一个页面
 */

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               button.setVisibility(View.VISIBLE);
               button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       button.setVisibility(View.GONE);
                       ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,province);
                       listView.setAdapter(adapter);
                   }
               });
               Toast.makeText(MainActivity.this,province.get(position),Toast.LENGTH_SHORT).show();
               if ("北京市".equals(province.get(position))){
                   ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,city_01);
                   listView.setAdapter(adapter);
               }else if("浙江省".equals(province.get(position) )){
                   ArrayAdapter adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,city_02);
                   listView.setAdapter(adapter);
               }

                    /*
                    * 两个页面用intent p50页
                    *  Intent intent=new Intent(MainActivity.this,City.class);
                                   intent.putExtra("province",data[position]);
                                   startActivity(intent);*/

           }
       });

    }
}
