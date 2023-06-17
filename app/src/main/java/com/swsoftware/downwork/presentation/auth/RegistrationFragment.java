package com.swsoftware.downwork.presentation.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.LoginDto;
import com.swsoftware.downwork.presentation.MainActivity;

public class RegistrationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText userName = view.findViewById(R.id.username);
        EditText name = view.findViewById(R.id.name);
        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        AuthenticationViewModel authenticationViewModel =
                ((AuthenticationActivity) requireActivity()).authenticationViewModel;

        AppCompatButton submitButton = view.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();
                String nameValue = name.getText().toString();
                String userNameValue = userName.getText().toString();
                if (emailValue.length() > 0 && passwordValue.length() > 0 && nameValue.length() > 0 && userNameValue.length() > 0) {
                    authenticationViewModel.registration(userNameValue, nameValue, emailValue, passwordValue)
                            .observe(getViewLifecycleOwner(), new Observer<LoginDto>() {
                        @Override
                        public void onChanged(LoginDto loginDto) {
                            if (loginDto.getStatus() == 0) {
                                SharedPreferences.Editor preferences =
                                        requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE).edit();
                                preferences.putInt("userId", loginDto.getUserId());
                                preferences.putString("apiToken", loginDto.getMobileToken());
                                preferences.apply();
                                Intent intent = new Intent(requireActivity(), MainActivity.class);
                                requireActivity().startActivity(intent);
                                requireActivity().finish();
                            } else {
                                Snackbar.make(view, loginDto.getMessage(), 2000).show();
                            }
                        }
                    });
                }
            }
        });

        AppCompatButton loginButton = view.findViewById(R.id.registrationButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, new LoginFragment())
                        .addToBackStack("login")
                        .setReorderingAllowed(false)
                        .commit();
            }
        });
    }
}