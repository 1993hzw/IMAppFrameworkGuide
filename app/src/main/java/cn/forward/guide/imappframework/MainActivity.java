package cn.forward.guide.imappframework;

import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

public class MainActivity extends QMUIFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startFragment(new HomeFragment());
    }
}
