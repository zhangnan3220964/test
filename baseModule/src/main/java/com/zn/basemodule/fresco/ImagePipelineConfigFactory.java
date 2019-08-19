package com.zn.basemodule.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

public class ImagePipelineConfigFactory {
    private static final String IMAGE_PIPELINE_CACHE_DIR = "imagepipeline_cache";

    private static ImagePipelineConfig sImagePipelineConfig;
//    private static ImagePipelineConfig sOkHttpImagePipelineConfig;

    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();

    public static final int MAX_DISK_CACHE_SIZE = 500 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 5;

    /**
     * 使用Android HTTP栈作为网络后端创建配置。
     */
    public static ImagePipelineConfig getImagePipelineConfig(Context context) {
        if (sImagePipelineConfig == null) {
            ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(context)
                    .setDownsampleEnabled(true)
                    .setBitmapsConfig(Bitmap.Config.RGB_565);
            configureCaches(configBuilder, context);
            sImagePipelineConfig = configBuilder.build();
        }
        return sImagePipelineConfig;
    }

//    /**
//     * 使用网络备份创建OKHTTP配置。
//     */
// public static ImagePipelineConfig getOkHttpImagePipelineConfig(Context context) {
//    if (sOkHttpImagePipelineConfig == null) {
//      OkHttpClient okHttpClient = new OkHttpClient();
//      ImagePipelineConfig.Builder configBuilder =
//        OkHttpImagePipelineConfigFactory.newBuilder(context, okHttpClient);
//      configureCaches(configBuilder, context);
//      sOkHttpImagePipelineConfig = configBuilder.build();
//    }
//    return sOkHttpImagePipelineConfig;
//  }

    /**
     * 配置磁盘和内存缓存不超过公共限制
     */
    private static void configureCaches(ImagePipelineConfig.Builder configBuilder, Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size
        configBuilder
                .setBitmapMemoryCacheParamsSupplier(
                        new Supplier<MemoryCacheParams>() {
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        })
                .setMainDiskCacheConfig(DiskCacheConfig.newBuilder(context)
                        .setBaseDirectoryPath(getExternalCacheDir(context))
                        .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)
                        .setMaxCacheSize(MAX_DISK_CACHE_SIZE)

                        .build());
    }

    public static File getExternalCacheDir(final Context context) {
        if (hasExternalCacheDir())
            return context.getExternalCacheDir();

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return createFile(Environment.getExternalStorageDirectory().getPath() + cacheDir, "");
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static File createFile(String folderPath, String fileName) {
        File destDir = new File(folderPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return new File(folderPath, fileName);
    }


}
