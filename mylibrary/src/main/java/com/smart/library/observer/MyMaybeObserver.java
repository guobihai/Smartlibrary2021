package com.smart.library.observer;


import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

/**
 * @author guobihai
 * 创建日期：2020/12/14
 * desc：MayBe
 */
public abstract class MyMaybeObserver<T> implements MaybeObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(T t) {
        onResultSuccess(t);
        onResultFinish();
    }

    @Override
    public void onError(Throwable e) {
        onResultException(e);
        onResultFinish();
    }

    @Override
    public void onComplete() {
        onResultFinish();
    }

    /**
     * 请求成功
     *
     * @param t
     */
    public abstract void onResultSuccess(T t);

    /**
     * 请求失败
     *
     * @param result
     */
    public void onResultFailed(T result) {
    }

    /**
     * 完成（请求结束均会执行）
     */
    public void onResultFinish() {

    }

    /**
     * 异常（请求发生异常）
     *
     * @param e
     */
    public void onResultException(Throwable e) {
    }

}
