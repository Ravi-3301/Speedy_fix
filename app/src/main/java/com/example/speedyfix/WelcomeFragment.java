package com.example.speedyfix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WelcomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        Button btnContinue = view.findViewById(R.id.phoneNumber);
        btnContinue.setOnClickListener(v -> {
            // Navigate to phone number fragment
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PhoneInputFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
