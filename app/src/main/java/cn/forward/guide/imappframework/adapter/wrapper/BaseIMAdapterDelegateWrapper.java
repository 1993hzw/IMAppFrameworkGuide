package cn.forward.guide.imappframework.adapter.wrapper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import cn.forward.guide.imappframework.R;
import cn.forward.guide.imappframework.model.IMessage;

public abstract class BaseIMAdapterDelegateWrapper<RootHolder extends BaseIMAdapterDelegateWrapper.BaseViewHolder<ContentHolder>, ContentHolder extends RecyclerView.ViewHolder>
        extends AdapterDelegate<List<IMessage>> {

    protected final boolean mIsRightLayout;
    protected final AdapterDelegate<List<IMessage>> mContentAdapterDelegate;

    public BaseIMAdapterDelegateWrapper(boolean isRightLayout, AdapterDelegate<List<IMessage>> contentAdapterDelegate) {
        mIsRightLayout = isRightLayout;
        mContentAdapterDelegate = contentAdapterDelegate;
        if (mContentAdapterDelegate instanceof BaseIMAdapterDelegateWrapper) {
            if (((BaseIMAdapterDelegateWrapper) mContentAdapterDelegate).mIsRightLayout != mIsRightLayout) {
                throw new RuntimeException("the wrapper's layout direction is different from the content's");
            }
        }
    }

    @NonNull
    @Override
    public RootHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View rootView = getRootView(parent, mIsRightLayout);
        ViewGroup contentContainer = rootView.findViewById(R.id.im_content);
        if (contentContainer == null) {
            throw new RuntimeException("the root view must contains the child view with a id 'im_content'");
        }
        ContentHolder contentHolder = onCreateContentViewHolder(contentContainer);
        RootHolder baseViewHolder = onCreateRootViewHolder(rootView, contentHolder);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull List<IMessage> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        RootHolder baseViewHolder = (RootHolder) holder;
        onBindRootViewHolder(items, position, baseViewHolder, payloads);
        onBindContentViewHolder(items, position, baseViewHolder.mContentViewHolder, payloads);
    }

    @Override
    public boolean isForViewType(@NonNull List<IMessage> items, int position) {
        boolean isValidLayout = (mIsRightLayout && items.get(position).mIsSender)
                || (!mIsRightLayout && !items.get(position).mIsSender);
        return isValidLayout && mContentAdapterDelegate.isForViewType(items, position);
    }

    protected abstract ViewGroup getRootView(ViewGroup parent, boolean isRightLayout);

    @NonNull
    protected abstract RootHolder onCreateRootViewHolder(@NonNull View itemView, ContentHolder contentViewHolder);

    protected abstract void onBindRootViewHolder(@NonNull List<IMessage> items, int position, @NonNull RootHolder holder, @NonNull List<Object> payloads);

    @NonNull
    protected ContentHolder onCreateContentViewHolder(@NonNull ViewGroup contentContainer) {
        return (ContentHolder) mContentAdapterDelegate.onCreateViewHolder(contentContainer);
    }

    protected void onBindContentViewHolder(@NonNull List<IMessage> items, int position, @NonNull ContentHolder holder, @NonNull List<Object> payloads) {
        mContentAdapterDelegate.onBindViewHolder(items, position, holder, payloads);
    }

    public static class BaseViewHolder<ContentHolder extends RecyclerView.ViewHolder> extends RecyclerView.ViewHolder {

        public final ContentHolder mContentViewHolder;
        public final ViewGroup mContentContainer;

        public BaseViewHolder(@NonNull View itemView, ContentHolder contentViewHolder) {
            super(itemView);
            mContentViewHolder = contentViewHolder;
            mContentContainer = itemView.findViewById(R.id.im_content);
            mContentContainer.addView(mContentViewHolder.itemView);
        }
    }
}
