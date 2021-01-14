package com.smart.library.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 自定义Gson转换器
 *
 * @author guobihai
 * @date 2019/03/14
 */
final class GsonResponseBodyConverter
        <T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            // 错误码
            String code = null;
            String msg = null;
            try {
                code = jsonObject.getString("status");
                msg = jsonObject.getString("msg");
            } catch (JSONException e) {
            }

            // 如果响应码不为200则表示此次请求是失败的，
            // 需要将data统一转为null(因为错误时并不会用到这个字段)，避免外部定义的数据类型与当前返回的数据类型冲突，
            // 比如定义成功时数据返回Object，而错误时后台却返回String（写Api的程序员不太专业）导致解析出错
            if (code == null) {
                jsonObject.put("code", "500");
            } else if (!code.equals("200")) {
                jsonObject.put("data", null);
            }
            // 如果msg为空，为了前段展示不报错需要重新赋值
            if (msg == null) {
                jsonObject.put("msg", "服务器错误");
            }
            JsonReader jsonReader = gson.newJsonReader(new StringReader(jsonObject.toString()));

            return adapter.read(jsonReader);
        } catch (JSONException e) {
            e.printStackTrace();
            JsonReader jsonReader = gson.newJsonReader(new StringReader(jsonString));

            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
