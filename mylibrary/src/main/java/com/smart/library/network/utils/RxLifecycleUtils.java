package com.smart.library.network.utils;

import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.ViewScopeProvider;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public class RxLifecycleUtils {
    private RxLifecycleUtils() {
        throw new IllegalStateException("Can't instance the RxLifecycleUtils");
    }

    /**
     * 绑定LifecycleOwner
     */
    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner) {
        return bindLifecycle(lifecycleOwner, Lifecycle.Event.ON_DESTROY);
    }

    /**
     * 绑定LifecycleOwner
     */
    public static <T> AutoDisposeConverter<T> bindLifecycle(LifecycleOwner lifecycleOwner, Lifecycle.Event untilEvent) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, untilEvent));
    }

    /**
     * 绑定View
     */
    public static <T> AutoDisposeConverter<T> bindView(View view) {
        return AutoDispose.autoDisposable(ViewScopeProvider.from(view));
    }

    /**
     * 绑定View
     */
    public static <T> AutoDisposeConverter<T> bindLifecycle(View view) {
        return AutoDispose.autoDisposable(ViewScopeProvider.from(view));
    }

    public static <T> AutoDisposeConverter<T> autoDispose(LifecycleOwner bindTarget) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(bindTarget));
    }

    public static <T> AutoDisposeConverter<T> autoDispose(View bindTarget) {
        return AutoDispose.autoDisposable(ViewScopeProvider.from(bindTarget));
    }
}
