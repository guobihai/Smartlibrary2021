package com.smart.textrunapp.flutter;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class FlutterRoutes {

    public static String buildRoutes(String routes, String param) {
        Gson gson = new Gson();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", routes);
        objectMap.put("param", param);
        return gson.toJson(objectMap);

    }
}
