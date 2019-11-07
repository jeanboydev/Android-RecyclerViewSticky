package com.jeanboy.app.sticky;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Synopsis
 * @Author caojianbo
 * @Date 2019/11/7 10:41
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {

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
        processSticky(c, parent);
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
    }

    private void processSticky(Canvas canvas, RecyclerView parent) {
//        int childCount = parent.getChildCount(); // 获取屏幕可见 item 数量
//        if (childCount == 0) return;
//        Log.e(StickyItemDecoration.class.getSimpleName(), "======childCount======" + childCount);

        int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        View firstChild = parent.getLayoutManager().findViewByPosition(position);
        View secondChild = parent.getLayoutManager().findViewByPosition(position + 1);
        if (secondChild.getTop() - firstChild.getTop() > firstChild.getHeight() * 2) {
            canvas.translate(0, firstChild.getTop());
        }
    }
}
