
package me.solidev.library.rx.retrofit;

import me.solidev.library.rx.retrofit.factory.ServiceFactory;
import me.solidev.library.rx.retrofit.func.ResultFunc;
import me.solidev.library.rx.retrofit.func.RetryWhenNetworkException;
import me.solidev.library.rx.retrofit.func.StringFunc;
import me.solidev.library.rx.retrofit.service.CommonService;
import me.solidev.library.rx.retrofit.subscriber.DownLoadSubscribe;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by _SOLID
 * Date:2016/7/28
 * Time:9:22
 */
public class HttpUtil {

    private CommonService mCommonService;

    private static class DefaultHolder {
        private static HttpUtil INSTANCE = new HttpUtil();
    }

    private HttpUtil() {
        mCommonService = ServiceFactory.getInstance().createService(CommonService.class);
    }

    public static HttpUtil getInstance() {
        return DefaultHolder.INSTANCE;
    }

    public Observable<String> loadString(String url) {
        return mCommonService
                .loadString(url)
                .compose(TransformUtils.<ResponseBody>defaultSchedulers())
                .retryWhen(new RetryWhenNetworkException())
                .map(new StringFunc());
    }

    public void download(String url, final DownLoadSubscribe subscribe) {
        mCommonService
                .download(url)
                .compose(TransformUtils.<ResponseBody>all_io())
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        subscribe.writeResponseBodyToDisk(responseBody);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        subscribe.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscribe.onError(e);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //do nothing
                    }
                });
    }


}
