package com.zn.basemodule.util.http.Api;



import java.util.TreeMap;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Api接口
 */
public interface Api {


    /**
     * GET请求
     *
     * @param url     api接口url
     * @param request 请求参数map
     * @return
     */
    @GET
    Observable<String> get(@Url String url, @QueryMap TreeMap<String, Object> request);

    /**
     * POST请求
     *
     * @param url     api接口url
     * @param request 请求参数map
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap TreeMap<String, Object> request);

    /**
     * PUT请求
     *
     * @param url     api接口url
     * @param request 请求参数map
     * @return
     */
    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap TreeMap<String, Object> request);

    /**
     * DELETE请求
     *
     * @param url     api接口url
     * @return
     */

    @DELETE
    Observable<String> delete(@Url String url);
}
