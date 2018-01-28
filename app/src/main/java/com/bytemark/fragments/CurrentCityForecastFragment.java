package com.bytemark.fragments;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bytemark.ByteMarkApplication;
import com.bytemark.R;
import com.bytemark.adapters.MyMultipleCitiesWeatherForecastRecyclerViewAdapter;
import com.bytemark.di.components.ForecastComponent;
import com.bytemark.di.modules.ForecastModule;
import com.bytemark.ui.forecast.ForecastPresenter;
import com.bytemark.ui.forecast.ForecastView;
import com.bytemark.models.ForecastList;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CurrentCityForecastFragment extends MvpFragment<ForecastView,ForecastPresenter> implements ForecastView {


    @BindView(R.id.recyclerView)
    RecyclerView rvForecast;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    @Inject
    ForecastPresenter forecastPresenter;

    public CurrentCityForecastFragment() {
    }

    @Override
    public ForecastPresenter createPresenter() {
        return forecastPresenter;
    }


    public static CurrentCityForecastFragment newInstance() {
        return new CurrentCityForecastFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_city_weather_forecast, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    private ForecastComponent getForecastComponent()
    {
        return ByteMarkApplication.get(getActivity()).getAppComponent()
                .getForecastComponent(new ForecastModule(this));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getForecastComponent().inject(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void updateLocation(Location location) {

        forecastPresenter.getForecastByLatLong(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void showError(String message) {
        loadingView.setVisibility(View.GONE);
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWeatherForecast(List<ForecastList> response) {

        loadingView.setVisibility(View.GONE);
        MyMultipleCitiesWeatherForecastRecyclerViewAdapter adapter = new MyMultipleCitiesWeatherForecastRecyclerViewAdapter(response);
        rvForecast.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvForecast.setAdapter(adapter);

    }

}
