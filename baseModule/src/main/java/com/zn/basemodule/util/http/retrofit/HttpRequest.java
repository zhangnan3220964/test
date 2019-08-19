package com.zn.basemodule.util.http.retrofit;

import com.blankj.utilcode.util.LogUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zn.basemodule.util.http.Api.Api;
import com.zn.basemodule.util.http.observer.HttpRxCallback;
import com.zn.basemodule.util.http.observer.HttpRxObservable;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Http请求类
 */
public class HttpRequest {

    /**
     * ApiUrl key 开发者可自定义
     */
    public static final String API_URL = "API_URL";

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }


    /**
     * 发送请求
     * 备注:不管理生命周期
     *
     * @param method   请求方式
     * @param prams    参数集合
     * @param callback 回调
     */
    public void request(Method method, TreeMap<String, Object> prams, HttpRxCallback callback) {

        Observable<String> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, callback).subscribe(callback);

    }


    /**
     * 发送请求
     * 备注:自动管理生命周期
     *
     * @param method    请求方式
     * @param lifecycle 实现RxActivity/RxFragment 参数为空不管理生命周期
     * @param prams     参数集合
     * @param callback  回调
     */
    public void request(Method method, TreeMap<String, Object> prams, LifecycleProvider lifecycle, HttpRxCallback callback) {
        Observable<String> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, lifecycle, callback).subscribe(callback);
    }


    /**
     * 发送请求
     * 备注:手动指定生命周期-Activity
     *
     * @param method    请求方式
     * @param lifecycle 实现RxActivity
     * @param event     指定生命周期
     * @param prams     参数集合
     * @param callback  回调
     */
    public void request(Method method, TreeMap<String, Object> prams, LifecycleProvider<ActivityEvent> lifecycle, ActivityEvent event, HttpRxCallback callback) {
        Observable<String> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, lifecycle, event, callback).subscribe(callback);
    }


    /**
     * 发送请求
     * 备注:手动指定生命周期-Fragment
     *
     * @param method    请求方式
     * @param lifecycle 实现RxFragment
     * @param event     指定生命周期
     * @param prams     参数集合
     * @param callback  回调
     */
    public void request(Method method, TreeMap<String, Object> prams, LifecycleProvider<FragmentEvent> lifecycle, FragmentEvent event, HttpRxCallback callback) {
        Observable<String> apiObservable = handleRequest(method, prams);

        HttpRxObservable.getObservable(apiObservable, lifecycle, event, callback).subscribe(callback);
    }


    /**
     * 预处理请求
     *
     * @param method 请求方法
     * @param prams  参数集合
     * @return
     */
    private Observable<String> handleRequest(Method method, TreeMap<String, Object> prams) {

        //获取基础参数
        TreeMap<String, Object> request = getBaseRequest();
        //添加业务参数
        request.putAll(prams);
        //获取apiUrl
        String apiUrl = "";
        if (request.containsKey(API_URL)) {
            apiUrl = String.valueOf(request.get(API_URL));
            //移除apiUrl参数（此参数不纳入业务参数）
            request.remove(API_URL);
        }
        Observable<String> apiObservable;


        LogUtils.e(apiUrl);
        switch (method) {
            case GET:
                apiObservable = RetrofitUtils.get().retrofit().create(Api.class).get(apiUrl, request);
                break;
            case POST:
                apiObservable = RetrofitUtils.get().retrofit().create(Api.class).post(apiUrl, request);
                break;
            case PUT:
                apiObservable = RetrofitUtils.get().retrofit().create(Api.class).put(apiUrl, request);
                break;
            case DELETE:
                apiObservable = RetrofitUtils.get().retrofit().create(Api.class).delete(apiUrl);
                break;
            default:
                apiObservable = RetrofitUtils.get().retrofit().create(Api.class).post(apiUrl, request);
                break;
        }
        return apiObservable;
    }

    /**
     * 获取基础request参数
     */
    private TreeMap<String, Object> getBaseRequest() {
        TreeMap<String, Object> map = new TreeMap<>();
//        map.put(k_key, appKey);
        return map;
    }


}
