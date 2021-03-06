package com.example.nurmemet.taglayoutexample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nurmemet.library.DrawableUtil;
import com.example.nurmemet.library.TagGroupLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TagGroupLayout mTagGroupDef;
    TagGroupLayout mTagGroupPr;
    TagGroupLayout mTagGroupSelecable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawableUtil drawableUtil = new DrawableUtil();
        mTagGroupDef = (TagGroupLayout) findViewById(R.id.tag_container);
        mTagGroupDef.setPadding(10, 4);

        mTagGroupPr = (TagGroupLayout) findViewById(R.id.tag_container_pr);
        mTagGroupPr.setPadding(20, 4);

        mTagGroupSelecable = (TagGroupLayout) findViewById(R.id.tag_container_selectable);
        mTagGroupSelecable.setPadding(30, 4);


        mTagGroupSelecable.setTags(getList(), new TagGroupLayout.BindProperty() {
            @Override
            public void OnBindProperty(TextView view) {
                view.setBackground(drawableUtil.getStateListDrawable());
                view.setTextColor(drawableUtil.getColorStateList());
            }
        });
        mTagGroupPr.setTags(getList(), new TagGroupLayout.BindProperty() {
            @Override
            public void OnBindProperty(TextView view) {
                view.setBackground(drawableUtil.getBackgroundBorderDrawable(false, Color.parseColor("#38BE07")));
                view.setTextColor(Color.parseColor("#38BE07"));
            }
        });
        mTagGroupDef.setTags(getList(), new TagGroupLayout.BindProperty() {
            @Override
            public void OnBindProperty(TextView view) {
                view.setBackground(drawableUtil.getBackgroundBorderDrawable(false, Color.parseColor("#FF3D7F")));

            }
        });
    }

    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("classmate");
        list.add("friend");
        list.add("family");
        list.add("基友");
        list.add("同事");
        list.add("117");
        list.add("ئۇيغۇر");
        list.add("ئالىم");
        list.add("ئالىم");
        list.add("يامغۇر");
        return list;
    }


    public float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
