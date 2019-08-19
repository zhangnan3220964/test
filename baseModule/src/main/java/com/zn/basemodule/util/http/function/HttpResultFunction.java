package com.zn.basemodule.util.http.function;


import com.blankj.utilcode.util.LogUtils;
import com.zn.basemodule.util.http.exception.ExceptionEngine;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * http结果处理函数
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        //打印具体错误
        LogUtils.e("HttpResultFunction:" + throwable.getMessage());
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
