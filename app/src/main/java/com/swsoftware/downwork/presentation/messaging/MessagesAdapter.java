package com.swsoftware.downwork.presentation.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.MessageDto;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {
    private final List<MessageDto> messages;
    int userId;

    public MessagesAdapter(List<MessageDto> messages, int userId) {
        this.messages = messages;
        this.userId = userId;
    }
    static class MessagesViewHolder extends RecyclerView.ViewHolder {

        TextView messageTextView;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageText);
        }


    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_layout, parent, false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        MessageDto message = messages.get(position);
        holder.messageTextView.setText(message.getMessage());
        if (message.getFromUser() != userId) {
            holder.messageTextView.setBackground(
                    AppCompatResources.getDrawable(holder.itemView.getContext(), R.drawable.message_back));
            ((LinearLayout) holder.itemView).setGravity(Gravity.START);
            holder.itemView.setPadding(
                    0, 0, holder.itemView.getPaddingStart(), holder.itemView.getPaddingBottom());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
