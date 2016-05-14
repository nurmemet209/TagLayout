package com.example.nurmemet.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/5/9.
 */
public class TagGroupLayout extends ViewGroup {

    public interface OnItemClick {
        void onItemClick(int position, Object data);
    }

    public interface BindProperty {
        void OnBindProperty(TextView view);
    }

    private OnItemClick mOnItemClick;
    private int mVerticalSpace = 20;
    private int mHorizontalSpace = 20;
    private float mDefPaddingLeftRight = 10;
    private float mDefPaddingTopBottom = 10;
    private List<Object> mList = new ArrayList<>();
    private boolean mCheckable = true;
    private View mCheckedView;

    public void setOnItemClick(OnItemClick mOnItemClick) {
        this.mOnItemClick = mOnItemClick;
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
        mHorizontalSpace = a.getDimensionPixelSize(R.styleable.TagGroupLayout_horizontal_space, 20);
        mVerticalSpace = a.getDimensionPixelSize(R.styleable.TagGroupLayout_vertical_space, 20);
        a.recycle();


    }

    public void setTags(List<? extends Object> list, BindProperty onBindProperty) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                this.mList.add(list.get(i));
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
        mDefPaddingLeftRight = lr;
        mDefPaddingTopBottom = tp;
    }

    public void setCheckable(boolean mCheckable) {
        this.mCheckable = mCheckable;
    }


    private TextView getTag(Object obj, final int position) {

        TextView tag = new TextView(getContext());
        int lr = (int) dp2px(getContext(), mDefPaddingLeftRight);
        int tb = (int) dp2px(getContext(), mDefPaddingTopBottom);
        tag.setPadding(lr, tb, lr, tb);


        tag.setClickable(true);
        tag.setText(obj.toString());

        tag.setGravity(Gravity.CENTER);

        tag.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckable) {
                    if (mCheckedView != null) {
                        mCheckedView.setSelected(false);
                    }
                    mCheckedView = v;
                    mCheckedView.setSelected(true);
                }

                if (mOnItemClick != null) {
                    mOnItemClick.onItemClick(position, mList.get(position));
                }
            }
        });
        return tag;


    }




    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = mVerticalSpace;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (left + child.getMeasuredWidth() > getMeasuredWidth()) {
                left = 0;
                top += mVerticalSpace + child.getMeasuredHeight();
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                left += child.getMeasuredWidth() + mHorizontalSpace;
            } else {
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                left += child.getMeasuredWidth() + mHorizontalSpace;

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
        int h = mVerticalSpace;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (w + child.getMeasuredWidth() > speckWidth) {
                h += child.getMeasuredHeight() + mVerticalSpace;
                w = child.getMeasuredWidth();
            } else {
                w += mHorizontalSpace + child.getMeasuredWidth();
            }

            if (i == childCount - 1) {
                h += child.getMeasuredHeight() + mVerticalSpace;
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
