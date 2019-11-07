package com.jeanboy.app.sticky.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.jeanboy.app.sticky.base.BaseViewHolder;

import java.util.List;

/**
 * @Synopsis RecyclerBaseAdapter
 * @Author caojianbo
 * @Date 2019/11/5 18:38
 */
public abstract class RecyclerBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> dataList;
    private int itemLayoutId = 0;

    public RecyclerBaseAdapter(List<T> dataList, int itemLayoutId) {
        this.dataList = dataList;
        this.itemLayoutId = itemLayoutId;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(getLayoutView(parent, itemLayoutId));
        setListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, dataList.get(position), holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    protected View getLayoutView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    private void setListener(final BaseViewHolder viewHolder) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });
    }


    public abstract void convert(BaseViewHolder holder, T t, int position);

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, BaseViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
