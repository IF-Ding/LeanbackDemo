package com.example.leanbackdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnChildSelectedListener;
import androidx.leanback.widget.VerticalGridView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String TOKEN = "PNLboSRd9Xncvg6z";
    private ArrayObjectAdapter mRowsAdapter;

    private ItemBridgeAdapter mItemBridgeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        HorizontalGridView horizontalGridView = findViewById(R.id.hg_title);
        horizontalGridView.setHorizontalSpacing(20);
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new TitlePresenter());
        ItemBridgeAdapter itemBridgeAdapter = new ItemBridgeAdapter(arrayObjectAdapter);
//        FocusHighlightHelper.setupBrowseItemFocusHighlight(itemBridgeAdapter,
//                FocusHighlight.ZOOM_FACTOR_LARGE, true);
        horizontalGridView.setAdapter(itemBridgeAdapter);
        arrayObjectAdapter.addAll(0, TitleModel.getTitleList());
        horizontalGridView.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                String city = TitleModel.getTitleList().get(position).name;
                if (city != null) {
                    Toast.makeText(getApplicationContext(),city, Toast.LENGTH_LONG).show();
                    getData(city);
                }
            }
        });

        VerticalGridView verticalGridView = findViewById(R.id.vg_temp);
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        mItemBridgeAdapter = new ItemBridgeAdapter(mRowsAdapter);
        verticalGridView.setAdapter(mItemBridgeAdapter);
        getData("深圳");

    }

    private void getData(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.caiyunapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<HourlyResponse> hourlyCall = weatherApi.getHourlyWeather(TitleModel.lngMap.get(city),TitleModel.latMap.get(city));
        hourlyCall.enqueue(new Callback<HourlyResponse>() {
            @Override
            public void onResponse(Call<HourlyResponse> call, Response<HourlyResponse> response) {
                HourlyResponse hourlyResponse = response.body();
                if (hourlyResponse != null) {
                    Log.d("HAHA", "onResponse: "+ hourlyResponse.status);
                    List<HourlyResponse.Temperature> list = hourlyResponse.getResult().getHourly().getTemperature();
                    TemperaturePresenter temperaturePresenter = new TemperaturePresenter();
                    mRowsAdapter.clear();
                    for (int i = 0; i < 15; i++) {
                        ArrayObjectAdapter adapter = new ArrayObjectAdapter(temperaturePresenter);
                        adapter.add(list.get(i * 24).datetime.getMonth()+1+"月"+list.get(i * 24).datetime.getDate()+"日");
                        for (int j = 0; j < 24; j++) {
                            adapter.add(list.get(i * 24 + j));
                        }
                        mRowsAdapter.add(new ListRow(adapter));
                    }
                    mItemBridgeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HourlyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}