package cn.forward.guide.imappframework;

import android.app.Application;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        QMUISwipeBackActivityManager.init(this);
    }
}
