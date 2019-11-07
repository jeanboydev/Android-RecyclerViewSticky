package com.jeanboy.app.sticky;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Synopsis
 * @Author caojianbo
 * @Date 2019/11/7 15:36
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {

    private View stickyView;

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
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
        int y = 0;

        if (stickyView.getTop() > 0) {
            y = stickyView.getTop();
        }

        int saveCount = c.save();
        c.translate(0, y);
        stickyView.draw(c);
        c.restoreToCount(saveCount);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = 200;
    }
}
