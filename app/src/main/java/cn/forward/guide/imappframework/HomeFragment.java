package cn.forward.guide.imappframework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends QMUIFragment {
    @BindView(R.id.top_bar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.group_list_view)
    QMUIGroupListView mGroupListView;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        mTopBar.setTitle(R.string.app_name);

        QMUICommonListItemView itemWithChevron = mGroupListView.createItemView("IM");
        itemWithChevron.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        int size = QMUIDisplayHelper.dp2px(getContext(), 20);
        QMUIGroupListView.newSection(getContext())
                .setLeftIconSize(size, ViewGroup.LayoutParams.WRAP_CONTENT)
                .addItemView(itemWithChevron, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startFragment(new IMListFragment());
                    }
                })
                .addTo(mGroupListView);

        return view;
    }
}
