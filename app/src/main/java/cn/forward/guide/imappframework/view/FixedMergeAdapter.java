package cn.forward.guide.imappframework.view;

import android.support.v7.widget.RecyclerView;

import me.mvdw.recyclerviewmergeadapter.adapter.RecyclerViewMergeAdapter;

public class FixedMergeAdapter extends RecyclerViewMergeAdapter {

    // fix bugs: auto scroll to bottom, if it returns -1 when adapter.getItemCount() == 0
    @Override
    public int getSubAdapterFirstGlobalPosition(RecyclerView.Adapter adapter) {

        int count = 0;

        for (LocalAdapter localAdapter : mAdapters) {
            RecyclerView.Adapter adapter_ = localAdapter.mAdapter;
            if (adapter_.equals(adapter)) {
                return count;
            }
            count += adapter_.getItemCount();
        }

        return -1;
    }
}
