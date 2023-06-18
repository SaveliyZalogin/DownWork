package com.swsoftware.downwork.presentation.messaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.MessageDto;
import com.swsoftware.downwork.presentation.projects.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText messageEditText;
    ImageButton addButton;
    ImageButton attachButton;
    ImageButton voiceButton;
    RecyclerView recyclerView;
    int chatId;
    String chatName;
    ChatViewModel chatViewModel = new ChatViewModel();
    ArrayList<MessageDto> messages = new ArrayList<>();
    SharedPreferences preferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        int userId = preferences.getInt("userId", -1);

        Intent intent = getIntent();
        chatId = intent.getIntExtra("chatId", -1);
        chatName = intent.getStringExtra("chatName");
        ((TextView) findViewById(R.id.chatName)).setText(chatName);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        messageEditText = findViewById(R.id.messageEdit);
        addButton = findViewById(R.id.add);
        attachButton = findViewById(R.id.attach);
        voiceButton = findViewById(R.id.voice);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayout bottomBar = findViewById(R.id.bottomEditor);
        bottomBar.getForeground().setAlpha(25);
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(
                com.google.android.material.R.attr.colorSurface, typedValue, true);
        getWindow().setNavigationBarColor(typedValue.data);

        chatViewModel.getMessages(chatId).observe(this, new Observer<List<MessageDto>>() {
            @Override
            public void onChanged(List<MessageDto> messageDtoList) {
                if (messageDtoList.size() > 0) {
                    messages.addAll(messageDtoList);
                    MessagesAdapter messagesAdapter = new MessagesAdapter(messages, userId);
                    recyclerView.setAdapter(messagesAdapter);
                }
            }
        });
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (messageEditText.getText().length() > 0) {
                    voiceButton.setImageDrawable(
                            AppCompatResources.getDrawable(getApplicationContext(), R.drawable.send));
                } else {
                    voiceButton.setImageDrawable(
                            AppCompatResources.getDrawable(getApplicationContext(), R.drawable.voice));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageEditText.getText().length() > 0) {
                    MessageDto messageDto = new MessageDto(
                            userId, messageEditText.getText().toString());
                    messages.add(messageDto);
                    recyclerView.getAdapter().notifyItemInserted(messages.size() - 1);
                    recyclerView.smoothScrollToPosition(messages.size() - 1);
                    messageEditText.setText("");
                    Log.d("messages", messages.toString());
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}