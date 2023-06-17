package com.swsoftware.downwork.presentation.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.UserDto;

public class ProfileFragment extends Fragment {

    ProfileViewModel profileViewModel = new ProfileViewModel();

    TextView userName;
    TextView name;
    TextView profession;
    TextView about;
    TextView registrationDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userName = view.findViewById(R.id.userName);
        name = view.findViewById(R.id.name);
        profession = view.findViewById(R.id.profession);
        about = view.findViewById(R.id.about);
        registrationDate = view.findViewById(R.id.registrationDate);

        SharedPreferences preferences =
                requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        profileViewModel.getUserInfo(preferences.getInt("userId", -1))
                .observe(getViewLifecycleOwner(), new Observer<UserDto>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onChanged(UserDto userDto) {
                        name.setText(userDto.getName());
                        userName.setText("@" + userDto.getUserName());
                        registrationDate.setText("На сайте с " + userDto.getRegistrationDate());
                        profession.setText(userDto.getProfession());
                        about.setText(userDto.getAbout());
                    }
                });
    }
}