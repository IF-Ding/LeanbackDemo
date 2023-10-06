package com.example.leanbackdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitleModel {
    static Map<String, String> lngMap = new HashMap<>();

    static Map<String, String> latMap = new HashMap<>();

    static {
        lngMap.put("深圳", "114.085947");
        lngMap.put("广州", "113.280637");
        lngMap.put("北京", "116.405285");
        lngMap.put("上海", "121.472644");
        lngMap.put("重庆", "106.504962");

        latMap.put("深圳", "22.547");
        latMap.put("广州", "23.125178");
        latMap.put("北京", "39.904989");
        latMap.put("上海", "31.231706");
        latMap.put("重庆", "29.533155");
    }
    public static List<Title> getTitleList() {
        List<Title> titleList = new ArrayList<>();
        titleList.add(new Title("深圳"));
        titleList.add(new Title("广州"));
        titleList.add(new Title("北京"));
        titleList.add(new Title("上海"));
        titleList.add(new Title("重庆"));
        return titleList;
    }
}
