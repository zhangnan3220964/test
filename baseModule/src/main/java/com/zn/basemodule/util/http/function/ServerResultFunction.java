package com.zn.basemodule.util.http.function;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.zn.basemodule.util.http.exception.ServerException;
import com.zn.basemodule.util.http.retrofit.HttpResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * 服务器结果处理函数
 */
public class ServerResultFunction implements Function<String, Object> {
    @Override
    public Object apply(@NonNull String response) throws Exception {
//        String result = AESCipher.decrypt(AES_KEY, response);
//
//
//        String[] strings = result.split("\0");
//
//        result = strings[0];
//        //打印服务器回传结果
        LogUtils.e("HttpResponse:", response);

        HttpResponse httpResponse = new Gson().fromJson(response, HttpResponse.class);

        if (httpResponse.getCode() == 0) {
            return new Gson().toJson(httpResponse.getData());
        } else {
            String data;
            if (StringUtils.isEmpty(httpResponse.getMsg())) {
                data = new Gson().toJson(httpResponse.getData());
            } else {
                data = new Gson().toJson(httpResponse.getMsg());
            }
            throw new ServerException(httpResponse.getCode(), data);//抛出服务器错误
        }
    }
}