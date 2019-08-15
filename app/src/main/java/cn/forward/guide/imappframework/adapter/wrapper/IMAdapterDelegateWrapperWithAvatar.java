package cn.forward.guide.imappframework.adapter.wrapper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import cn.forward.guide.imappframework.R;
import cn.forward.guide.imappframework.model.IMessage;

public class IMAdapterDelegateWrapperWithAvatar<ContentHolder extends RecyclerView.ViewHolder>
        extends BaseIMAdapterDelegateWrapper<IMAdapterDelegateWrapperWithAvatar.ViewHolderWithAvatar<ContentHolder>, ContentHolder> {

    public IMAdapterDelegateWrapperWithAvatar(boolean isRightLayout, AdapterDelegate<List<IMessage>> contentAdapterDelegate) {
        super(isRightLayout, contentAdapterDelegate);
    }

    @Override
    protected ViewGroup getRootView(ViewGroup parent, boolean isRightLayout) {
        return (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(mIsRightLayout ? R.layout.item_base_im_with_avtar_right
                        : R.layout.item_base_im_with_avtar_left, parent, false);
    }

    @NonNull
    @Override
    protected ViewHolderWithAvatar<ContentHolder> onCreateRootViewHolder(@NonNull View itemView, ContentHolder contentViewHolder) {
        ViewHolderWithAvatar<ContentHolder> viewHolderWithAvatar = new ViewHolderWithAvatar<>(itemView, contentViewHolder);
        viewHolderWithAvatar.mName.setVisibility(mIsRightLayout ? View.GONE : View.VISIBLE);
        return viewHolderWithAvatar;
    }

    @Override
    protected void onBindRootViewHolder(@NonNull List<IMessage> items, int position, @NonNull ViewHolderWithAvatar<ContentHolder> holder, @NonNull List<Object> payloads) {
        IMessage message = items.get(position);
        holder.mAvatar.setImageResource(mIsRightLayout ? R.mipmap.ic_avatar_female : R.mipmap.ic_avatar_male);
        holder.mName.setText(message.mAuthor);
    }

    public static class ViewHolderWithAvatar<H extends RecyclerView.ViewHolder> extends BaseIMAdapterDelegateWrapper.BaseViewHolder<H> {
        ImageView mAvatar;

        TextView mName;

        public ViewHolderWithAvatar(@NonNull View itemView, H contentViewHolder) {
            super(itemView, contentViewHolder);
            mAvatar = itemView.findViewById(R.id.avatar);
            mName = itemView.findViewById(R.id.name);
        }
    }
}
