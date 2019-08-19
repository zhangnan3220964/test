package com.zn.basemodule.util.http.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * RetrofitUtils工具类
 */
public class RetrofitUtils {

    public static String BASE_URL = "http://biuzone.com:10002/api/";//測試

    /**
     * 接口地址
     */
    public static final String BASE_API = BASE_URL;
    public static final int CONNECT_TIME_OUT = 30;//连接超时时长x秒
    public static final int READ_TIME_OUT = 30;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 30;//写数据接超时时长x秒
    private static RetrofitUtils mInstance = null;

    private RetrofitUtils() {
    }

    public static RetrofitUtils get() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置okHttp
     *
     * @author
     */
    private static OkHttpClient okHttpClient() {
        //开启Log

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("HttpLoggingInterceptor:",message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
//                                .addHeader("X-AUTH-TOKEN",)
//                                .addHeader("CLIENT-OS-NAME", "ANDROID")
//                                .addHeader("CLIENT-OS-VERSION", Build.VERSION.RELEASE)
//                                .addHeader("CLIENT-APP-VERSION", ))
                                .build();
                        return chain.proceed(request);

                    }
                });

        // TODO: 2018/8/1添加https请求证书
//        setCertificates(builder, new okio.Buffer().writeUtf8(Configurations.CER).inputStream());
//        App.getInstance().getAssets().open("api.bweibo.net.cer")
//        new okio.Buffer().writeUtf8(Configurations.CER).inputStream()
        OkHttpClient client = builder.build();
        return client;
    }

    /**
     * 获取Retrofit
     *
     * @author
     */
    public Retrofit retrofit() {


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(BASE_API)
                .addConverterFactory(new ToStringConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

//    private static void setCertificates(OkHttpClient.Builder builder, InputStream... certificates) {
//        try {
//
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(null);
//            int index = 0;
//            for (InputStream certificate : certificates) {
//                String certificateAlias = Integer.toString(index++);
//                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
//
//                if (certificate != null)
//                    certificate.close();
//            }
//
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//
//            trustManagerFactory.init(keyStore);
//            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
//
//            builder.sslSocketFactory(sslContext.getSocketFactory(), Platform.get().trustManager(sslContext.getSocketFactory()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


}
