package com.swsoftware.downwork.presentation.chats;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.ChatDto;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatsViewHolder> {

    private final List<ChatDto> chats;
    private final OnItemClickListener onItemClickListener;

    public ChatListAdapter(List<ChatDto> chats, OnItemClickListener onItemClick) {
        this.chats = chats;
        onItemClickListener = onItemClick;
    }

    public interface OnItemClickListener {
        void onClick(int itemId, String itemName);
    }
    static public class ChatsViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView title;
        TextView lastMessage;

        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            title = itemView.findViewById(R.id.title);
            lastMessage = itemView.findViewById(R.id.last_message);
        }
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_preview_layout, parent, false);
        return new ChatsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChatsViewHolder holder, int position) {
        ChatDto chat = chats.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(chat.getId(), chat.getUserInfo().getUserName());
            }
        });
        holder.title.setText(chat.getUserInfo().getUserName());
        holder.lastMessage.setText(String.valueOf(chat.getLastMessageId()));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
