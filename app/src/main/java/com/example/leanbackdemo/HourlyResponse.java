package com.example.leanbackdemo;

import java.util.Date;
import java.util.List;

public class HourlyResponse {
    String status;

    Result result;

    public HourlyResponse(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    class Result {
        Hourly hourly;

        public Result(Hourly hourly) {
            this.hourly = hourly;
        }

        public Hourly getHourly() {
            return hourly;
        }

        public void setHourly(Hourly hourly) {
            this.hourly = hourly;
        }
    }

    class Hourly {
        String status;
        List<Temperature> temperature;

        public Hourly(String status, List<Temperature> temperature) {
            this.status = status;
            this.temperature = temperature;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Temperature> getTemperature() {
            return temperature;
        }

        public void setTemperature(List<Temperature> temperature) {
            this.temperature = temperature;
        }
    }

    class Temperature {
        Date datetime;
        Float value;

        public Temperature(Date datetime, Float value) {
            this.datetime = datetime;
            this.value = value;
        }

        public Date getDatetime() {
            return datetime;
        }

        public void setDatetime(Date datetime) {
            this.datetime = datetime;
        }

        public Float getValue() {
            return value;
        }

        public void setValue(Float value) {
            this.value = value;
        }
    }
}