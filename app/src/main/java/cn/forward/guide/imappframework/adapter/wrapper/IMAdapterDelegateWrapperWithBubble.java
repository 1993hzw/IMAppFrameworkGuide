package cn.forward.guide.imappframework.adapter.wrapper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import cn.forward.guide.imappframework.R;
import cn.forward.guide.imappframework.model.IMessage;

public class IMAdapterDelegateWrapperWithBubble<ContentHolder extends RecyclerView.ViewHolder>
        extends BaseIMAdapterDelegateWrapper<IMAdapterDelegateWrapperWithBubble.ViewHolderWithBubble<ContentHolder>, ContentHolder> {

    public IMAdapterDelegateWrapperWithBubble(boolean isRightLayout, AdapterDelegate<List<IMessage>> contentAdapterDelegate) {
        super(isRightLayout, contentAdapterDelegate);
    }

    @Override
    protected ViewGroup getRootView(ViewGroup parent, boolean isRightLayout) {
        return (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(mIsRightLayout ? R.layout.item_base_im_with_bubble_right
                        : R.layout.item_base_im_with_bubble_left, parent, false);
    }

    @NonNull
    @Override
    protected ViewHolderWithBubble<ContentHolder> onCreateRootViewHolder(@NonNull View itemView, ContentHolder contentViewHolder) {
        return new ViewHolderWithBubble<>(itemView, contentViewHolder);
    }

    @Override
    protected void onBindRootViewHolder(@NonNull List<IMessage> items, int position, @NonNull ViewHolderWithBubble<ContentHolder> holder, @NonNull List<Object> payloads) {
    }

    public static class ViewHolderWithBubble<H extends RecyclerView.ViewHolder> extends BaseIMAdapterDelegateWrapper.BaseViewHolder<H> {

        public ViewHolderWithBubble(@NonNull View itemView, H contentViewHolder) {
            super(itemView, contentViewHolder);
        }
    }
}
