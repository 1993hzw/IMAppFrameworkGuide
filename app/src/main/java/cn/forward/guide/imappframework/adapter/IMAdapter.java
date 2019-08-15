package cn.forward.guide.imappframework.adapter;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.util.DiffUtil;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager;
import com.hannesdorfmann.adapterdelegates3.AsyncListDifferDelegationAdapter;

import java.util.List;

import cn.forward.guide.imappframework.adapter.wrapper.IMAdapterDelegateWrapperWithAvatar;
import cn.forward.guide.imappframework.adapter.wrapper.IMAdapterDelegateWrapperWithBubble;
import cn.forward.guide.imappframework.model.IMessage;

public class IMAdapter extends AsyncListDifferDelegationAdapter<IMessage> {

    public IMAdapter(@NonNull DiffUtil.ItemCallback<IMessage> diffCallback) {
        super(diffCallback);
        init();
    }

    public IMAdapter(@NonNull DiffUtil.ItemCallback<IMessage> diffCallback, @NonNull AdapterDelegatesManager<List<IMessage>> delegatesManager) {
        super(diffCallback, delegatesManager);
        init();
    }

    public IMAdapter(@NonNull AsyncDifferConfig differConfig, @NonNull AdapterDelegatesManager<List<IMessage>> delegatesManager) {
        super(differConfig, delegatesManager);
        init();
    }

    private void init() {
        addAdapterDelegate(new IMAdapterDelegateWrapperWithAvatar<>(true,
                new IMPhotoAdapterDelegate()));
        addAdapterDelegate(new IMAdapterDelegateWrapperWithAvatar<>(false,
                new IMAdapterDelegateWrapperWithBubble<>(false, new IMPhotoAdapterDelegate())));

        addAdapterDelegate(new IMAdapterDelegateWrapperWithAvatar<>(true,
                new IMAdapterDelegateWrapperWithBubble<>(true, new IMTextAdapterDelegate())));
        addAdapterDelegate(new IMAdapterDelegateWrapperWithAvatar<>(false,
                new IMAdapterDelegateWrapperWithBubble<>(false, new IMTextAdapterDelegate())));
    }

    private void addAdapterDelegate(AdapterDelegate<List<IMessage>> adapterDelegate) {
        delegatesManager.addDelegate(adapterDelegate);
    }
}
