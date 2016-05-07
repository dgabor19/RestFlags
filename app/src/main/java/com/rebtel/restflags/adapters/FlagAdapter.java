package com.rebtel.restflags.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebtel.restflags.R;
import com.rebtel.restflags.models.Country;

import java.util.List;

/**
 * Created by gabordudas on 06/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class FlagAdapter extends RecyclerView.Adapter {
    public static final String TAG = FlagAdapter.class.getSimpleName();

    private Context mContext;
    private List<Country> mCountries;

    public FlagAdapter(Context context, List<Country> countries) {
        mContext = context;
        mCountries = countries;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(inflater.inflate(R.layout.item_flag, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int position) {
        ViewHolder holder = (ViewHolder) vHolder;

        Country country = mCountries.get(position);

        holder.mText.setText(country.getCountryName());
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView mCard;
        public ImageView mImage;
        public TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);

            mCard = (CardView) itemView.findViewById(R.id.cardFlag);
            mImage = (ImageView) itemView.findViewById(R.id.imageFlag);
            mText = (TextView) itemView.findViewById(R.id.textFlag);
        }
    }
}
