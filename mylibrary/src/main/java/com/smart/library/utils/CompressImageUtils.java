package com.smart.library.utils;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.google.android.exoplayer.C;

import java.io.File;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author guobihai
 * 创建日期：2020/11/30
 * desc：图片压缩工具
 */
public class CompressImageUtils {
    private static final int defaultSize = 4000;//默认大小

    /**
     * 开始压缩
     *
     * @param context  上下文
     * @param filePath 文件
     * @param callBack 回调
     */
    public static void compressImage(Context context, File filePath, final onStringValueInterface callBack) {
        if (null == filePath) {
            throw new IllegalArgumentException("file is allow not null");
        }
        int[] size = getImageWidthHeight(filePath.getPath());
        if (size[0] < defaultSize && size[1] < defaultSize) {
            if (null != callBack) {
                callBack.onFileCallBack(filePath);
            }
            return;
        }
        Luban.with(context)
                .load(filePath)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        if (null != callBack) {
                            callBack.onFileCallBack(file);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (null != callBack) {
                            callBack.onFaile("压缩图片失败");
                        }
                    }
                }).launch();
    }

    /**
     * 获取图片宽高
     */
    private static int[] getImageWidthHeight(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //此时返回的bitmap为null
        BitmapFactory.decodeFile(filePath, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    interface onStringValueInterface {
        void onFileCallBack(File file);

        void onFaile(String errMsg);
    }
}
