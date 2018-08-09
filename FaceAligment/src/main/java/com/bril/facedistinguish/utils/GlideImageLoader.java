package com.bril.facedistinguish.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 项目名：FaceAlignment
 * 包名：com.bril.facedistinguish.utils
 * 时间：2018/7/3 14:42
 * 描述：
 * 姓名： sunyang
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).asBitmap().into(imageView);
    }

}
