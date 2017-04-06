package me.solidev.library.module.banner;

import android.os.Handler;
import android.provider.Settings;

import java.lang.ref.WeakReference;

import me.solidev.library.utils.Logger;

/**
 * Created by Vincent Woo
 * Date: 2015/12/31
 * Time: 13:38
 */
class EDog {

    private class WaitingThread extends Thread {
        public void run() {
            while (!cancelled) {
                boolean shouldBark = System.currentTimeMillis() - startTick > duration;
                if (shouldBark) {
                    bark();
                    startTick = System.currentTimeMillis();
                }
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private Handler handler = new Handler();
    private int duration;
    private long startTick;
    private boolean cancelled;
    private WeakReference<Runnable> r;
    private WaitingThread waitingThread;

    void feed(Runnable r, int duration) {
        this.r = new WeakReference<>(r);
        this.duration = duration;
        this.startTick = System.currentTimeMillis();
        this.cancelled = false;

        if (waitingThread == null || !waitingThread.isAlive()) {
            waitingThread = new WaitingThread();
            waitingThread.start();
        }
    }

    private void bark() {
        handler.post(r.get());
    }

    void cancel() {
        cancelled = true;
    }
}
