package com.example.nurmemet.library;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/5/9.
 */
public class TagGroupLayout extends ViewGroup {

    public static interface OnItemClick {
        public void onItemClick(int position, Object data);
    }

    public static interface BindProperty {
        public void OnBindProperty(TextView view);
    }

    private OnItemClick onItemClick;
    private BindProperty onBindProperty;
    private int verticalSpace = 20;
    private int horizontalSpace = 20;
    private float defPaddingLeftRight = 10;
    private float defPaddingTopBottom = 10;
    private List<Object> list = new ArrayList<>();
    private boolean checkable = true;
    private View checkedView;
    private DrawableUtil drawableUtil;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public TagGroupLayout(Context context) {
        super(context);
    }

    public TagGroupLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.TagGroupLayoutStyle);
    }

    public TagGroupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagGroupLayout);
        horizontalSpace = a.getDimensionPixelSize(R.styleable.TagGroupLayout_horizontal_space, 20);
        verticalSpace = a.getDimensionPixelSize(R.styleable.TagGroupLayout_vertical_space, 20);
        //textColorStateList = ContextCompat.getColorStateList(context, R.color.main_text_color_to_white);

        drawableUtil = new DrawableUtil();
        a.recycle();


    }

    public void setTags(List<? extends Object> list, BindProperty onBindProperty) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                this.list.add(list.get(i));
                TextView tv = getTag(list.get(i).toString(), i);
                if (onBindProperty != null) {
                    onBindProperty.OnBindProperty(tv);
                }
                this.addView(tv);
            }

        }
    }

    /**
     * dp为单位
     *
     * @param lr
     * @param tp
     */
    public void setPadding(int lr, int tp) {
        defPaddingLeftRight = lr;
        defPaddingTopBottom = tp;
    }

    public void setCheckable(boolean checkable) {
        this.checkable = checkable;
    }


    public TextView getTag(Object obj, final int position) {

        TextView tag = new TextView(getContext());
        int lr = (int) dp2px(getContext(), defPaddingLeftRight);
        int tb = (int) dp2px(getContext(), defPaddingTopBottom);
        tag.setPadding(lr, tb, lr, tb);


        tag.setClickable(true);
        tag.setText(obj.toString());

        tag.setGravity(Gravity.CENTER);

        tag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkable) {
                    if (checkedView != null) {
                        checkedView.setSelected(false);
                    }
                    checkedView = v;
                    checkedView.setSelected(true);
                }

                if (onItemClick != null) {
                    onItemClick.onItemClick(position, list.get(position));
                }
            }
        });
        return tag;


    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = verticalSpace;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (left + child.getMeasuredWidth() > getMeasuredWidth()) {
                left = 0;
                top += verticalSpace + child.getMeasuredHeight();
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                left += child.getMeasuredWidth() + horizontalSpace;
            } else {
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                left += child.getMeasuredWidth() + horizontalSpace;

            }


        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int speckWidth = MeasureSpec.getSize(widthMeasureSpec);
        int speckWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int speckHeight = MeasureSpec.getSize(heightMeasureSpec);
        int speckHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int childCount = getChildCount();
        int w = 0;
        int h = verticalSpace;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (w + child.getMeasuredWidth() > speckWidth) {
                h += child.getMeasuredHeight() + verticalSpace;
                w = child.getMeasuredWidth();
            } else {
                w += horizontalSpace + child.getMeasuredWidth();
            }

            if (i == childCount - 1) {
                h += child.getMeasuredHeight() + verticalSpace;
            }
        }
        setMeasuredDimension(speckWidth, speckHeightMode == MeasureSpec.EXACTLY ? speckHeight : h);


    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }


}
