package com.example.leanbackdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

public class TemperaturePresenter extends Presenter {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_temp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof HourlyResponse.Temperature) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.tvTime.setText((((HourlyResponse.Temperature) item).datetime.getHours()+8)%24 +":00");
            holder.tvTemp.setText(((HourlyResponse.Temperature) item).value+"℃");
        } else if (item instanceof String) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.tvTime.setVisibility(View.GONE);
            holder.tvTemp.setText((String) item);
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    static class ViewHolder extends Presenter.ViewHolder {
        TextView tvTime;
        TextView tvTemp;

        public ViewHolder(View view) {
            super(view);
            tvTime = view.findViewById(R.id.tv_time);
            tvTemp = view.findViewById(R.id.tv_temp);
        }
    }
}
