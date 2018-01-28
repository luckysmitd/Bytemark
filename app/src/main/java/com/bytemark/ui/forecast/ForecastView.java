package com.bytemark.ui.forecast;

import com.bytemark.models.ForecastList;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * Created by smit on 26/01/18.
 */

public interface ForecastView extends MvpView {

    void showError(String message);

    void showLoading();

    void showWeatherForecast(List<ForecastList> response);

}
