package com.example.nurmemet.library;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * Created by nurmemet on 2016/5/12.
 */
public class DrawableUtil {

    public Drawable getBackgroundBorderDrawable(boolean isFill, int color) {
        // 外部矩形四圆角椭圆的x,y半径
        float[] outerR = new float[]{100, 100, 100, 100, 100, 100, 100, 100};
        // 内部矩形与外部矩形的距离
        RectF inset = new RectF(3, 3, 3, 3);
        // 内部矩形弧度
        float[] innerRadii = new float[]{100, 100, 100, 100, 100, 100, 100, 100};

        RoundRectShape rr = new RoundRectShape(outerR, isFill ? null : inset, isFill ? null : outerR);
        ShapeDrawable drawable = new ShapeDrawable(rr);
        //指定填充颜色
        drawable.getPaint().setColor(color);
        // 指定填充模式
        drawable.getPaint().setStyle(Paint.Style.FILL);
        drawable.getPaint().setAntiAlias(true);
        return drawable;

    }


    public StateListDrawable getStateListDrawable() {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed}, getBackgroundBorderDrawable(true, Color.parseColor("#FF3D7F")));
        drawable.addState(new int[]{android.R.attr.state_selected}, getBackgroundBorderDrawable(true, Color.parseColor("#FF3D7F")));
        drawable.addState(new int[]{android.R.attr.state_enabled}, getBackgroundBorderDrawable(false, Color.parseColor("#FF3D7F")));
        return drawable;
    }

    public ColorStateList getColorStateList() {
        int[][] stateList = new int[3][];

        stateList[0] = new int[]{android.R.attr.state_pressed};
        stateList[1] = new int[]{android.R.attr.state_selected};
        stateList[2] = new int[]{android.R.attr.state_enabled};
        int[] colorList = new int[]{Color.WHITE, Color.WHITE, Color.parseColor("#FF3D7F")};

        ColorStateList colorStateList = new ColorStateList(stateList, colorList);
        return colorStateList;
    }
}
