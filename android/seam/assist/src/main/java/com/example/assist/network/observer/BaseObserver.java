package com.example.assist.network.observer;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Description: 基础Observer
 * @e-mail:2036573698@qq.com
 */
public abstract class BaseObserver<T> implements Observer<T> {
    // 开始
    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    // 继续
    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    // 异常
    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e);
    }

    // 完成
    @Override
    public void onComplete() {
    }

    // 成功
    public abstract void onSuccess(T t);

    // 失败
    public abstract void onFailure(Throwable e);
}
