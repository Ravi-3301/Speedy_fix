package com.example.speedyfix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneInputFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_input, container, false);
        
        Button btnNext = view.findViewById(R.id.btnNext);
        EditText phoneNumber = view.findViewById(R.id.etPhoneNumber);
        btnNext.setOnClickListener(v -> {
            // Navigate to OTP fragment
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91"+ phoneNumber.getText().toString(),60, TimeUnit.SECONDS,requireActivity(),
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(requireContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            Otp_Verification otpVerification = new Otp_Verification();
                            Bundle bundle = new Bundle();
                            bundle.putString("mobile",phoneNumber.getText().toString());
                            bundle.putString("verificationId",verificationId);
                            otpVerification.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, otpVerification)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
            );

        });

        return view;
    }
}

