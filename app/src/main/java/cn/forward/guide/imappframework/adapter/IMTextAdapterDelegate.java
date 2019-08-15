package cn.forward.guide.imappframework.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;

import java.util.List;

import cn.forward.guide.imappframework.R;
import cn.forward.guide.imappframework.model.IMessage;

public class IMTextAdapterDelegate extends AdapterDelegate<List<IMessage>> {


    @Override
    public boolean isForViewType(@NonNull List<IMessage> items, int position) {
        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull List<IMessage> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        TextViewHolder textViewHolder = (TextViewHolder) holder;
        IMessage msg = items.get(position);
        textViewHolder.mTextView.setText(msg.mMsg);
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
        }
    }
}
