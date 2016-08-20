package com.lj.horizontalrankboard;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Description
 * Created by langjian on 2016/8/20.
 * Version
 */
public class MyImageLoader {
    public static void loadFrescoImg(final SimpleDraweeView qiyiDraweeView, final String url, final ControllerListener imageListener){

        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            public Handler mHandler;

            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageListener != null){
                    imageListener.onFinalImageSet(id, imageInfo, anim);
                }
            }
            @Override
            public void onFailure(String id, Throwable throwable) {
                if (url.equals(qiyiDraweeView.getTag())){{
                    Fresco.getImagePipeline().evictFromCache(Uri.parse(url));
                    if (throwable != null)
                        throwable.printStackTrace();

                    if (mHandler == null){
                        mHandler = new Handler();
                    }

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!url.equals(qiyiDraweeView.getTag())){{
                                return ;
                            }}
                            ControllerListener controllerListener1 = new BaseControllerListener<ImageInfo>() {

                                @Override
                                public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                                    if (imageListener != null){
                                        imageListener.onFinalImageSet(id, imageInfo, animatable);
                                    }
                                }

                                public void onFailure(String id, Throwable throwable) {
                                    Fresco.getImagePipeline().evictFromCache(Uri.parse(url));
                                    if (imageListener != null){
                                        imageListener.onFailure(id,throwable);
                                    }
                                    if (url.equals(qiyiDraweeView.getTag())) {
                                        {
                                            qiyiDraweeView.setTag(null);
                                        }
                                    }
                                }
                            };

                            Uri uri = url.startsWith("http")? Uri.parse(url) : Uri.parse("file://"+url);
                            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setResizeOptions(new ResizeOptions(4096, 4096))
                                    .build();
                            DraweeController controller = Fresco.newDraweeControllerBuilder()
                                    .setImageRequest(request)
                                    .setAutoPlayAnimations(false)
                                    .setControllerListener(controllerListener1)
                                    .build();
                            qiyiDraweeView.setController(controller);
                        }
                    }, 300);

                }}
            }
        };

        Uri uri = url.startsWith("http")? Uri.parse(url) : Uri.parse("file://"+url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setResizeOptions(new ResizeOptions(4096,4096))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(false)
                .setControllerListener(controllerListener)
                .build();

        qiyiDraweeView.setController(controller);
    }
}
