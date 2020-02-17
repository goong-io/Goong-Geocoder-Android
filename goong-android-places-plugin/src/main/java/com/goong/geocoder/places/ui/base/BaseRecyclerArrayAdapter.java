package com.goong.geocoder.places.ui.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerArrayAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    protected List<T> items;
    protected int layoutId;

    public BaseRecyclerArrayAdapter(@NonNull List<T> items, int layoutId) {
        this.items = items;
        this.layoutId = layoutId;
    }

    public BaseRecyclerArrayAdapter(@NonNull List<T> items) {
        this.items = items;
    }

    public abstract BaseViewHolder<T> getViewHolder(View view, int viewType);

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.setData(getItem(position));
    }

    public @Nullable
    T getItem(int post) {
        if (post < 0 || post >= this.items.size()) {
            return null;
        }
        return items.get(post);
    }

    /**
     * Get items of items which are displayed in recyclerview
     */
    public List<T> getItems() {
        return this.items;
    }

    /**
     * Change data of adapter view.
     */
    public void setItems(Collection<T> data) {
        // clear previous data
        items.clear();
        // add all data
        items.addAll(data);

        notifyDataSetChanged();
    }

    public void addItem(T data) {
        items.add(0, data);
        notifyItemInserted(0);
    }

    public void addItem(Collection<T> data) {
        int presize = getItemCount();
        items.addAll(data);
        notifyItemRangeInserted(presize, data.size());
    }

    public void clear() {
        int numberOfItems = items.size();
        items.clear();
        notifyItemRangeRemoved(0, numberOfItems);
    }

    public void removeItem(T data) {
        int index = this.items.indexOf(data);
        if (index == -1) {
            return;
        }
        removeItem(index);
    }

    public void removeItem(Collection<T> datas) {
        for (T data : datas) {
            removeItem(data);
        }
    }

    /**
     * remove a items of data from the adapter and notify that data is changed or not.
     */
    public void removeItems(Collection<T> datas, boolean notifyDataChanged) {
        for (T data : datas) {
            items.remove(data);
        }
        if (notifyDataChanged) {
            notifyDataSetChanged();
        }
    }

    /**
     * Remove an item at specific position
     */
    public void removeItem(int position) {
        if (position >= items.size() || position < 0) return;
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * get layout based on ViewType. Default is the layout of each item.
     */
    protected int getLayoutId(int viewType) {
        return layoutId;
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent,
                false);
        return getViewHolder(view, viewType);
    }

}
