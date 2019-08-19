package com.zn.basemodule.fresco;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.zn.basemodule.R;

import java.io.File;

/**
 * Created by mayn on 2018/6/26.
 */
public class FrescoUtils {

    private static boolean isInit = false;

    /**
     * 显示缩略图
     *
     * @param draweeView     draweeView
     * @param url            url
     * @param resizeWidthDp  resizeWidth
     * @param resizeHeightDp resizeHeight
     */
    public static void showThumb(SimpleDraweeView draweeView, String url, int resizeWidthDp, int resizeHeightDp) {
        if (url == null || "".equals(url))
            return;
        if (draweeView == null)
            return;
        initialize(draweeView.getContext());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(new ResizeOptions(resizeWidthDp, resizeHeightDp))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<>())
                .build();

        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(draweeView.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(100)
                .setPlaceholderImage(R.drawable.zwt)
                .setFailureImage(R.drawable.zwt)
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        draweeView.setHierarchy(hierarchy);
        draweeView.setController(controller);
    }

    /**
     * 显示缩略图
     *
     * @param draweeView     draweeView
     * @param url            url
     * @param resizeWidthDp  resizeWidth
     * @param resizeHeightDp resizeHeight
     */
    public static void showThumbs(final SimpleDraweeView draweeView, final String url, final int resizeWidthDp, int resizeHeightDp) {
        if (url == null || "".equals(url))
            return;
        if (draweeView == null)
            return;
        initialize(draweeView.getContext());
        draweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageDecodeOptions decodeBuilder = ImageDecodeOptions.newBuilder()
                .setDecodeAllFrames(false)
                .setDecodePreviewFrame(true)
                .build();
        final ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(new ResizeOptions(resizeWidthDp, resizeHeightDp))
                .setImageDecodeOptions(decodeBuilder)
                .build();


        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(draweeView.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(100)
                .setPlaceholderImage(R.drawable.zwt)
                .setFailureImage(R.drawable.zwt)
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        draweeView.setHierarchy(hierarchy);


        final DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline()
                .fetchDecodedImage(request, null);
        BaseDataSubscriber<CloseableReference<CloseableImage>> source =
                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> source) {
                        draweeView.setBackgroundResource(R.drawable.zwt);
                    }

                    @Override
                    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> source) {
                        Bitmap bitmap = getBitmap(source);
                        if (bitmap.getHeight() >= 2000) {
                            bitmap =  Bitmap.createScaledBitmap(bitmap, resizeWidthDp, 150, true);
                            draweeView.setImageBitmap(bitmap);
//                            FrescoDealPicUtil.downloadFile(url, new OnDownloadListener() {
//                                @Override
//                                public void onDownloadSuccess(String fileName) {
//                                    BitmapFactory.Options options = new BitmapFactory.Options();
//                                    options.inSampleSize = 2;
//                                    BitmapFactory.decodeResource(draweeView.getResources(),)
//                                    FrescoDealPicUtil.getCutedPic(draweeView, "file://"+fileName,
//                                            com.blankj.utilcode.util.ScreenUtils.getScreenWidth(),  com.blankj.utilcode.util.ScreenUtils.getScreenHeight()/3, 0, 0);
//                                    LogUtils.e("下载图路径"+fileName);
//                                    draweeView.setImageURI(Uri.parse(fileName));
//                                }
//
//                                @Override
//                                public void onDownloading(int progress) {
////                                    draweeView.setBackgroundResource(R.mipmap.blog_zwt);
//                                }
//
//                                @Override
//                                public void onDownloadFailed() {
////                                    draweeView.setBackgroundResource(R.mipmap.blog_zwt);
//                                }
//                            }, Configurations.BigPicPath );

                        } else {
                            draweeView.setImageBitmap(bitmap);

                        }

                        dataSource.close();
                    }
                };
        dataSource.subscribe(source, UiThreadImmediateExecutorService.getInstance());
    }


    /**
     * initialize
     *
     * @param context context
     */
    public static void initialize(Context context) {
        if (isInit)
            return;
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(context, config);
        isInit = true;
    }


    public static void displayRoundImageSupportGif(final ImageView imageView, String url, int reqWidth, int reqHeight) {
        if (imageView == null || TextUtils.isEmpty(url)) {
            return;
        }

        ImageDecodeOptions decodeBuilder = ImageDecodeOptions.newBuilder()
                .setDecodeAllFrames(false)
                .setDecodePreviewFrame(true)
                .build();
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setImageDecodeOptions(decodeBuilder)
                .setResizeOptions(new ResizeOptions(reqWidth, reqHeight))
                .build();

        final DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline()
                .fetchDecodedImage(imageRequest, null);
        BaseDataSubscriber<CloseableReference<CloseableImage>> source =
                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> source) {
                        imageView.setBackgroundResource(R.drawable.zwt);
                    }

                    @Override
                    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> source) {
                        Bitmap bitmap = getBitmap(source);
                        bitmap = makeRoundCorner(bitmap);
                        if (bitmap == null || bitmap.isRecycled()) {
                            return;
                        }
                        try {
                            imageView.setImageBitmap(bitmap);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }

                        dataSource.close();
                    }
                };
        dataSource.subscribe(source, UiThreadImmediateExecutorService.getInstance());
    }

    public static void displayImageSupport(final String url, final FrescoBitmapCallback<Bitmap> frescoBitmapCallback) {


        ImageDecodeOptions decodeBuilder = ImageDecodeOptions.newBuilder()
                .setDecodeAllFrames(false)
                .setDecodePreviewFrame(true)
                .build();
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setImageDecodeOptions(decodeBuilder)
//                .setResizeOptions(new ResizeOptions(reqWidth, reqHeight))
                .build();

        final DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline()
                .fetchDecodedImage(imageRequest, null);
        BaseDataSubscriber<CloseableReference<CloseableImage>> source =
                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> source) {
                        frescoBitmapCallback.onFailure(url, new Throwable());
                    }

                    @Override
                    protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> source) {
                        Bitmap bitmap = getBitmap(source);
                        frescoBitmapCallback.onSuccess(url, bitmap);
                        dataSource.close();
                    }
                };
        dataSource.subscribe(source, UiThreadImmediateExecutorService.getInstance());
    }


    /**
     * 获取bitmap，如果是gif则提取第一帧
     *
     * @param source
     * @return
     */
    private static Bitmap getBitmap(DataSource<CloseableReference<CloseableImage>> source) {
        if (source == null) {
            return null;
        }
        CloseableReference<CloseableImage> reference = source.getResult();
        if (reference == null) {
            return null;
        }

        CloseableImage image = reference.get();
        if (image == null) {
            return null;
        }
        Bitmap bitmap = null;
        if (image instanceof CloseableBitmap) {
            // 普通bitmap
            CloseableBitmap cb = (CloseableBitmap) image;
            bitmap = cb.getUnderlyingBitmap();
        } else if (image instanceof CloseableAnimatedImage) {
            // 如果是有动画的image，就是gif
            CloseableAnimatedImage bitmapImage = (CloseableAnimatedImage) image;
            AnimatedImageResult animatedImageResult = bitmapImage.getImageResult();
            if (animatedImageResult == null) {
                return null;
            }
            //图片转为bitmap
            CloseableReference<Bitmap> picBitmap = animatedImageResult.getPreviewBitmap();
            if (picBitmap == null) {
                return null;
            }
            //获取到bitmap，可是这个bitmap返回的是null
            bitmap = picBitmap.get();
        }
        return bitmap;
    }

    /**
     * 将bitmap变成圆
     *
     * @param bitmap
     * @return
     */
    public static Bitmap makeRoundCorner(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        int width = bitmap.getWidth();

        int height = bitmap.getHeight();

        int left = 0, top = 0, right = width, bottom = height;

        float roundPx = height / 2;

        if (width > height) {

            left = (width - height) / 2;

            top = 0;

            right = left + height;

            bottom = height;

        } else if (height > width) {

            left = 0;

            top = (height - width) / 2;

            right = width;

            bottom = top + width;

            roundPx = width / 2;

        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        int color = 0xff424242;

        Paint paint = new Paint();

        Rect rect = new Rect(left, top, right, bottom);

        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static boolean isLongImg(File file, Bitmap bitmap) {
        //TODO file.length()的判断，需要根据OS的版本号做动态调整大小
        if (file == null || file.length() == 0) {
            return false;
        }
        if (bitmap.getHeight() > bitmap.getWidth() * 3) {
            return true;
        }
        return false;
    }

    public static void SaveGifFromMainFileCache(Context mContext, String url, String localSavePath) {
        final String fileName;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + localSavePath;
        } else {  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + localSavePath;
        }
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true).build();

        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(imageRequest,
                mContext);

        if (ImagePipelineFactory.getInstance().getMainFileCache().hasKey(cacheKey)) {
            BinaryResource resource = ImagePipelineFactory.getInstance().getMainFileCache().getResource(cacheKey);
            File cacheFile = ((FileBinaryResource) resource).getFile();
            Boolean saveSuccess = FileUtils.copyFile(cacheFile.getPath(), fileName);//把缓存文件复制到要保存的位置上
            if (saveSuccess) {
                //保存成功处理
                ToastUtils.showLong("保存成功");
                // 发送广播，通知刷新图库的显示
                mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
            } else {
                //保存失败处理
                ToastUtils.showLong("保存失败");
            }

        } else {
            //保存失败处理
        }
    }

}
