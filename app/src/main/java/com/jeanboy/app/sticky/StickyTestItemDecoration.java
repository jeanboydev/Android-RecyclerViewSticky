package com.jeanboy.app.sticky;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Synopsis
 * @Author caojianbo
 * @Date 2019/11/7 10:41
 */
public class StickyTestItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint = new Paint();
    private Paint mPaint2 = new Paint();

    private View stickyView;

    public StickyTestItemDecoration() {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth(10);
        mPaint2.setColor(Color.BLACK);
        mPaint2.setTextSize(50);
    }

    /**
     * 绘制第一层
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        mPaint.setColor(Color.RED);
//        int childCount = parent.getChildCount();
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        for (int i = 0; i < childCount; i++) {
//            View view = parent.getChildAt(i);
//            float top = view.getTop() - 10;
//            float bottom = view.getTop();
//            c.drawRect(left, top, right, bottom, mPaint);
//        }
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            int top = view.getTop() - view.getHeight();
            int bottom = top + view.getHeight();
            Log.e(StickyTestItemDecoration.class.getSimpleName(), "=======itemHeight=====" + view.getHeight());
            c.drawRect(0, top, parent.getWidth(), bottom, mPaint);
            c.drawText("item:" + position, view.getLeft(), view.getTop(), mPaint2);
        }
    }

    /**
     * 绘制第二层
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

//        mPaint.setColor(Color.GREEN);
//        c.drawRect(0, 0, parent.getRight(), 60, mPaint);

//        Log.e(StickyTestItemDecoration.class.getSimpleName(), "===========onDrawOver=====");
//        int childCount = parent.getChildCount();//可见 item 数量
//        for (int i = 0; i < childCount; i++) {
//            View childAt = parent.getChildAt(i);
//            int position = parent.getChildAdapterPosition(childAt);
//            Log.e(StickyTestItemDecoration.class.getSimpleName(), "=position=" + position);
//            if (position == 0) {
//                int offset = 0;
//                if (childAt.getTop() <= 0) {
//                    offset = -childAt.getTop();
//                } else {
//
//                }
//                int saveCount = c.save();
//                c.translate(0, );
//            }
//        }
//        View child0 = parent.getChildAt(0);
//
//        if (child0.getBottom() <= itemHeight) {
//            c.drawRect(0, 0, parent.getWidth(), child0.getBottom(), mPaint);
//        } else {
//            c.drawRect(0, 0, parent.getWidth(), itemHeight, mPaint);
//        }


        if (stickyView == null) {
            int count = parent.getChildCount();
            for (int i = 0; i < count; i++) {
                View view = parent.getChildAt(i);
                int position = parent.getChildAdapterPosition(view);
                if (position == 0) {
                    stickyView = view;
                }
            }
        }

        int offset = 0;
        if (stickyView.getTop() < 0) {
            offset = -stickyView.getTop();
        } else {
            offset = stickyView.getTop();
        }
        int saveCount = c.save();
        c.translate(0, offset);
        stickyView.draw(c);
        c.restoreToCount(saveCount);

    }

    /**
     * 获取 item 偏移量
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = 200;
    }
}
