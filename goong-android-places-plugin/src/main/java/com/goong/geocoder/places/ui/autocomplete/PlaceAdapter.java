package com.goong.geocoder.places.ui.autocomplete;

import android.view.View;
import android.widget.TextView;

import com.goong.geocoder.places.R;
import com.goong.geocoder.places.data.remote.entity.ChildPlace;
import com.goong.geocoder.places.data.remote.entity.Place;
import com.goong.geocoder.places.ui.base.BaseRecyclerArrayAdapter;
import com.goong.geocoder.places.ui.base.BaseViewHolder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlaceAdapter extends BaseRecyclerArrayAdapter<Place> {
    private final int TYPE_PREDICTION_PLACE = 1;
    private final int TYPE_CHILD_PLACE = 2;
    private BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener;

    public PlaceAdapter(BaseViewHolder.OnViewHolderClickListener onViewHolderClickListener) {
        super(new ArrayList<>());
        this.onViewHolderClickListener = onViewHolderClickListener;
    }

    @Override
    protected int getLayoutId(final int viewType) {
        return viewType == TYPE_CHILD_PLACE ? R.layout.item_child : R.layout.item_place;
    }

    @Override
    public int getItemViewType(final int position) {
        Place place = getItem(position);
        if (place instanceof ChildPlace) {
            return TYPE_CHILD_PLACE;
        } else {
            return TYPE_PREDICTION_PLACE;
        }
    }

    @Override
    public BaseViewHolder<Place> getViewHolder(final View view, final int viewType) {
        if (viewType == TYPE_CHILD_PLACE) {
            return new ChildViewHolder(view, onViewHolderClickListener);
        } else {
            return new PredictionViewHolder(view, onViewHolderClickListener);
        }
    }

    class PredictionViewHolder extends BaseViewHolder<Place> {

        TextView tvTitle;
        TextView tvSubTitle;

        public PredictionViewHolder(@NonNull final View itemView,
                                    @Nullable final OnViewHolderClickListener onViewHolderClickListener) {
            super(itemView, onViewHolderClickListener);
            tvSubTitle = itemView.findViewById(R.id.tv_sub_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void setData(@NonNull final Place data) {
            tvTitle.setText(data.getTitle());
            tvSubTitle.setText(data.getSubTitle() != null ? data.getSubTitle() : "");
        }
    }

    class ChildViewHolder extends BaseViewHolder<Place> {
        TextView tvTitle;
        TextView tvSubTitle;

        public ChildViewHolder(@NonNull final View itemView,
                               @Nullable final OnViewHolderClickListener onViewHolderClickListener) {
            super(itemView, onViewHolderClickListener);
            tvSubTitle = itemView.findViewById(R.id.tv_sub_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void setData(@NonNull final Place data) {
            tvTitle.setText(data.getTitle());
            tvSubTitle.setText(data.getSubTitle() != null ? data.getSubTitle() : "");
        }
    }
}
