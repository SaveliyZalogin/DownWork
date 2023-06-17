package com.swsoftware.downwork.presentation.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.presentation.MainActivity;

public class AuthenticationActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    AuthenticationViewModel authenticationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        SharedPreferences preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        if (preferences.getInt("userId", -1) != -1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        authenticationViewModel = new AuthenticationViewModel();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, new LoginFragment())
                .addToBackStack("login")
                .setReorderingAllowed(false)
                .commit();
    }
}