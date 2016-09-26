package me.solidev.library.rx.retrofit.subscriber;


import me.solidev.library.rx.retrofit.HttpResult;
import me.solidev.library.utils.Logger;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by _SOLID
 * Date:2016/7/27
 * Time:21:27
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Logger.e(this, e.getMessage());
        e.printStackTrace();
        //在这里做全局的错误处理
        if (e instanceof HttpException) {
            // ToastUtils.getInstance().showToast(e.getMessage());
        }
        onFailure(e);
    }

    @Override
    public void onNext(HttpResult<T> t) {
        if (!t.error)
            onSuccess(t.results);
        else
            onFailure(new Throwable("error=" + t.error));
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable e);
}
