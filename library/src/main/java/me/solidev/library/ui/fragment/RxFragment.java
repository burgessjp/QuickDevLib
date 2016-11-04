package me.solidev.library.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/**
 * Created by _SOLID
 * Date:2016/11/4
 * Time:16:36
 * Desc:Copy from http://blog.csdn.net/dd864140130/article/details/53029617
 * to deal RxLife
 */

public abstract class RxFragment extends BaseFragment {

    protected final BehaviorSubject<FragmentEvent> lifeSubject = BehaviorSubject.create();

    protected <T> Observable.Transformer<T, T> bindLifeEvent(final FragmentEvent bindEvent) {
        //被监视的Observable
        final Observable<FragmentEvent> observable = lifeSubject.takeFirst(new Func1<FragmentEvent, Boolean>() {
            @Override
            public Boolean call(FragmentEvent event) {
                return event.equals(bindEvent);
            }
        });

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceOb) {
                return sourceOb.takeUntil(observable);
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifeSubject.onNext(FragmentEvent.CREATE);
    }


    @Override
    public void onStart() {
        super.onStart();
        lifeSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifeSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifeSubject.onNext(FragmentEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lifeSubject.onNext(FragmentEvent.STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifeSubject.onNext(FragmentEvent.DESTROY);
    }
}
