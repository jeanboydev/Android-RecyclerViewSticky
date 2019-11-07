package com.jeanboy.app.sticky;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jeanboy.app.sticky.base.BaseViewHolder;
import com.jeanboy.app.sticky.base.RecyclerBaseAdapter;

import java.util.List;

/**
 * @Synopsis
 * @Author caojianbo
 * @Date 2019/11/7 10:24
 */
public class ListAdapter extends RecyclerBaseAdapter<String> {


    private static final int TYPE_STICKY = 100;
    private static final int TYPE_NORMAL = 101;

    public ListAdapter(List<String> dataList) {
        super(dataList, R.layout.item_normal);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {//第一个 item 为悬停类型
            return TYPE_STICKY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_STICKY == viewType) {
            return new BaseViewHolder(getLayoutView(parent, R.layout.item_sticky));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        super.onBindViewHolder(holder, position);
        if (position == 0) {
            convert(holder);
        } else {
            convert(holder, dataList.get(position - 1), holder.getAdapterPosition());
        }
    }

    @Override
    public void convert(BaseViewHolder holder, String s, int position) {
        TextView tv_data = holder.getView(R.id.tv_data);
        tv_data.setText(s);
    }

    public void convert(BaseViewHolder holder) {
        //悬停类型

    }

}
