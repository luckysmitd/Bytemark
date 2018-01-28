package com.bytemark.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytemark.R;
import com.bytemark.models.ForecastList;
import com.bytemark.models.Weather;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyMultipleCitiesWeatherForecastRecyclerViewAdapter extends RecyclerView.Adapter<MyMultipleCitiesWeatherForecastRecyclerViewAdapter.ViewHolder> {

    private final List<ForecastList> mValues;

    public MyMultipleCitiesWeatherForecastRecyclerViewAdapter(List<ForecastList> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvCity.setText(holder.mItem.getCity());
        List<Weather> weatherList = holder.mItem.getWeather();
        if(weatherList.size() > 0) {
            holder.tvForecast.setText(weatherList.get(0).getMain());
            holder.tvDetails.setText(weatherList.get(0).getDescription());
        }
        holder.tvDateTime.setText(holder.mItem.getDtTxt());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        @BindView(R.id.tvCity) TextView tvCity;
        @BindView(R.id.tvForecast) TextView tvForecast;
        @BindView(R.id.tvDetails) TextView tvDetails;
        @BindView(R.id.tvDateTime) TextView tvDateTime;
        ForecastList mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
