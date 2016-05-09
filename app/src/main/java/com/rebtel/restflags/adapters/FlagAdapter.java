package com.rebtel.restflags.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rebtel.restflags.MainApplication;
import com.rebtel.restflags.R;
import com.rebtel.restflags.interfaces.OnItemClickListener;
import com.rebtel.restflags.models.Country;
import com.rebtel.restflags.utils.Constants;

import java.util.List;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class FlagAdapter extends RecyclerView.Adapter {
    public static final String TAG = FlagAdapter.class.getSimpleName();

    private Context mContext;
    private List<Country> mCountries;
    private OnItemClickListener mItemClickListener;

    public FlagAdapter(Context context, List<Country> countries, OnItemClickListener itemClickListener) {
        mContext = context;
        mCountries = countries;
        mItemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(inflater.inflate(R.layout.item_flag, parent, false), mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int position) {
        ViewHolder holder = (ViewHolder) vHolder;

        Country country = mCountries.get(position);

        holder.mText.setText(country.getCountryName());
        holder.mImage.setImageDrawable(null);


        ImageLoader.getInstance().displayImage(
                Constants.BASE_COUNTRIES_URL + String.format(Constants.IMAGE_COUNTRY_ENDPOINT, country.getCountryCode().toLowerCase()),
                holder.mImage,
                MainApplication.getDisplayImageLoaderOptions(mContext));
    }

    /**
     * Setiing country list
     * @param countries
     */
    public void setCountries(List<Country> countries) {
        mCountries = countries;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CardView mCard;
        public ImageView mImage;
        public TextView mText;
        public OnItemClickListener mItemClickListener;

        public ViewHolder(View itemView, OnItemClickListener itemClickListener) {
            super(itemView);

            mCard = (CardView) itemView.findViewById(R.id.cardFlag);
            mImage = (ImageView) itemView.findViewById(R.id.imageFlag);
            mText = (TextView) itemView.findViewById(R.id.textFlag);
            mItemClickListener = itemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, this, getAdapterPosition());
            }
        }
    }
}
