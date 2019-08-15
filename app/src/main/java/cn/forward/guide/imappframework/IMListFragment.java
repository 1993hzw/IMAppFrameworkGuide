package cn.forward.guide.imappframework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.forward.guide.imappframework.adapter.IMAdapter;
import cn.forward.guide.imappframework.model.IMDataPhoto;
import cn.forward.guide.imappframework.model.IMessage;

public class IMListFragment extends QMUIFragment {

    @BindView(R.id.top_bar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private IMAdapter mAdapter;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_im_list, null);
        ButterKnife.bind(this, view);

        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mAdapter = new IMAdapter(new DiffUtil.ItemCallback<IMessage>() {
            @Override
            public boolean areItemsTheSame(@NonNull IMessage iMessage, @NonNull IMessage t1) {
                return iMessage == t1;
            }

            @Override
            public boolean areContentsTheSame(@NonNull IMessage iMessage, @NonNull IMessage t1) {
                return false;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        List<IMessage> messages = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            IMessage msg = new IMessage();
            msg.mAuthor = i % 2 == 0 ? "male" : "female";
            msg.mIsSender = i % 2 == 0;
            msg.mMsg = "message " + i;
            msg.mTimeStamp = System.currentTimeMillis();
            if (i % 3 == 0) {
                IMDataPhoto photo = new IMDataPhoto();
                msg.mIMData = photo;
            }
            messages.add(msg);
        }
        mAdapter.setItems(messages);
    }
}
