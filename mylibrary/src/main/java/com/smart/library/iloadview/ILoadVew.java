package com.smart.library.iloadview;

import android.view.View;

/**
 * @author guobihai
 * 创建日期：2020/10/31
 * desc：UI loading View
 */
public interface ILoadVew {
    /**
     * 没有数据
     */
    void showEmpty(View.OnClickListener listener);

    /**
     * 没有数据
     */
    void showEmpty(String message, View.OnClickListener listener);

    /**
     * 加载失败
     *
     * @param message
     * @param buttonText
     * @param listener
     */
    void showError(String message, String buttonText, View.OnClickListener listener);

    void showError(String message, View.OnClickListener listener);

    void showError(String message);

    /**
     * 网络异常
     *
     * @param listener
     */
    void showNetworkError(View.OnClickListener listener);

    /**
     * 连接超时
     */
    void showTimeOutError(View.OnClickListener listener);

    /**
     * 隐藏loadView
     */
    void hide();

    /**
     * 显示loadView
     *
     * @param message 加载中提示语
     */
    void showLoading(String message);

    void showLoading();

    boolean isShowLoadding();

    boolean isShowError();

    boolean isShow();
}
