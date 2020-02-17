package com.goong.geocoder.places.ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView, @Nullable OnViewHolderClickListener onViewHolderClickListener) {
        super(itemView);
        itemView.setOnClickListener(view -> {
            if (onViewHolderClickListener != null) {
                onViewHolderClickListener.onItemClick(getAdapterPosition());
            }
        });
    }

    public abstract void setData(@NonNull final T data);

    public interface OnViewHolderClickListener {
        void onItemClick(int position);
    }
}
