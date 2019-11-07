package com.jeanboy.app.sticky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Synopsis
 * @Author caojianbo
 * @Date 2019/11/7 14:32
 */
public class TestItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private Paint mPaint2;

    private View lastStickyView;
    private View currentStickyView;

    public TestItemDecoration() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeWidth(10);
        mPaint2.setColor(Color.BLACK);
        mPaint2.setTextSize(50);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);


        mPaint.setColor(Color.YELLOW);

        // 获取正在显示的 item 的数量
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View item = parent.getChildAt(i); // 获取到 item
            int position = parent.getChildAdapterPosition(item); // 获取到 item 在 RecyclerView 的 position
            int top = item.getTop() - item.getHeight();
            int bottom = top + item.getHeight();
            c.drawRect(0, top, parent.getWidth(), bottom, mPaint);
            c.drawText("onDraw:" + position, item.getLeft(), item.getTop() - item.getHeight() * 0.5f, mPaint2);
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        mPaint.setColor(Color.BLUE);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View item = parent.getChildAt(i); // 获取到 item
            int position = parent.getChildAdapterPosition(item); // 获取到 item 在 RecyclerView 的 position
            int top = item.getTop() - item.getHeight();
            int bottom = top + item.getHeight();
            c.drawRect(300, top - 50, 800, bottom - 50, mPaint);
            c.drawText("onDrawOver:" + position, 300, top, mPaint2);

            if (isGroupHead(position)) {
                lastStickyView = currentStickyView;
                currentStickyView = item;
            }
        }

    }

    /**
     * 判断是否是分组头
     *
     * @param position
     * @return
     */
    private boolean isGroupHead(int position) {
        return position % 5 == 0;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 设置 item decor 的大小
        outRect.bottom = 200;
    }
}
