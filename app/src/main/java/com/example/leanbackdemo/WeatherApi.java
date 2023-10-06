package com.example.leanbackdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
    @GET("v2.6/PNLboSRd9Xncvg6z/{lng},{lat}/hourly?hourlysteps=360")
    Call<HourlyResponse> getHourlyWeather(@Path("lng")String lng, @Path("lat")String lat);
}
