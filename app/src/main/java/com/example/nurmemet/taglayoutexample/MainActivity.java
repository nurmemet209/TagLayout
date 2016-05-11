package com.example.nurmemet.taglayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nurmemet.library.TagGroupLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TagGroupLayout.OnItemClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TagGroupLayout mTagGroup = (TagGroupLayout) findViewById(R.id.tag_container);
        mTagGroup.setOnItemClick(this);
        mTagGroup.setPadding(10, 4);
        ArrayList<String> list = new ArrayList<>();
        list.add("套餐1");
        list.add("套餐2");
        list.add("套餐3er");
        list.add("套餐2erer");
        list.add("套餐3erer");
        list.add("套餐2er");
        list.add("套餐ererer3");
        list.add("套餐");
        list.add("套餐er3");
        list.add("套餐1");
        list.add("套餐2");
        list.add("套餐3er");
        list.add("套餐");

        mTagGroup.setTags(list);
    }

    @Override
    public void onItemClick(int position, Object data) {

    }
}
