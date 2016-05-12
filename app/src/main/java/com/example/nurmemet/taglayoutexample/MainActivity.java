package com.example.nurmemet.taglayoutexample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nurmemet.library.DrawableUtil;
import com.example.nurmemet.library.TagGroupLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TagGroupLayout.OnItemClick {

    TagGroupLayout mTagGroupDef;
    TagGroupLayout mTagGroupPr;
    TagGroupLayout mTagGroupSelecable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawableUtil drawableUtil = new DrawableUtil();
        mTagGroupDef = (TagGroupLayout) findViewById(R.id.tag_container);
        mTagGroupDef.setOnItemClick(this);
        mTagGroupDef.setPadding(10, 4);

        mTagGroupPr = (TagGroupLayout) findViewById(R.id.tag_container_pr);
        mTagGroupPr.setOnItemClick(this);
        mTagGroupPr.setPadding(20, 4);

        mTagGroupSelecable = (TagGroupLayout) findViewById(R.id.tag_container_selectable);
        mTagGroupSelecable.setOnItemClick(this);
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

    private Drawable getBackgroundBorderDrawable() {
        float w = dp2px(this, 50);
        // 外部矩形弧度
        float[] outerR = new float[]{w - 50, w, w, w, w, w, w, w};
        // 内部矩形与外部矩形的距离
        RectF inset = new RectF(70, 70, 70, 70);
        // 内部矩形弧度
        float[] innerRadii = new float[]{20, 20, 20, 20, 20, 20, 20, 20};

        RoundRectShape rr = new RoundRectShape(outerR, inset, null);
        ShapeDrawable drawable = new ShapeDrawable(rr);
        //指定填充颜色
        drawable.getPaint().setColor(Color.RED);
        // 指定填充模式
        drawable.getPaint().setStyle(Paint.Style.FILL);
        return drawable;

    }

    @Override
    public void onItemClick(int position, Object data) {

    }


    public float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
