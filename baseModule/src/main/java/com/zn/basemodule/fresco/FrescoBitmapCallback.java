package com.zn.basemodule.fresco;

import android.net.Uri;

public interface FrescoBitmapCallback<T> {
    void onSuccess(String uri, T result);

    void onFailure(String uri, Throwable throwable);

    void onCancel(Uri uri);
}
