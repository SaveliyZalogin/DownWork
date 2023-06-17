package com.swsoftware.downwork.presentation.chats;

import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.ChatDto;
import com.swsoftware.downwork.presentation.messaging.ChatActivity;

import java.util.List;

public class ChatsFragment extends Fragment {

    private final ChatsViewModel chatsViewModel = new ChatsViewModel();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ProgressBar loading = view.findViewById(R.id.progress);

        SharedPreferences preferences =
                requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

        chatsViewModel.getChats(preferences.getInt("userId", -1)).observe(getViewLifecycleOwner(), new Observer<List<ChatDto>>() {
            @Override
            public void onChanged(List<ChatDto> chatList) {
                if (chatList.size() > 0) {
                    ChatListAdapter adapter = new ChatListAdapter(chatList, new ChatListAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int itemId, String itemName) {
                            Intent chatIntent = new Intent(requireContext(), ChatActivity.class);
                            chatIntent.putExtra("chatId", itemId);
                            chatIntent.putExtra("chatName", itemName);
                            requireContext().startActivity(chatIntent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    loading.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}