package cn.forward.guide.imappframework.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import cn.forward.guide.imappframework.R;
import cn.forward.guide.imappframework.model.IMDataPhoto;
import cn.forward.guide.imappframework.model.IMessage;

public class IMPhotoAdapterDelegate extends AdapterDelegate<List<IMessage>> {


    @Override
    public boolean isForViewType(@NonNull List<IMessage> items, int position) {
        return items.get(position).mIMData instanceof IMDataPhoto;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List<IMessage> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        TextViewHolder textViewHolder = (TextViewHolder) holder;
        IMessage msg = items.get(position);
        textViewHolder.mImageView.setImageResource(R.drawable.ic_launcher_background);
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}
