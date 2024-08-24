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
import androidx.fragment.app.Fragment;

import com.example.speedyfix.classes.OTPTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Otp_Verification extends Fragment {
    private String verificationId;
    private String mobile;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_verification, container, false);
        EditText otpBox1 = view.findViewById(R.id.otpBox1);
        EditText otpBox2 = view.findViewById(R.id.otpBox2);
        EditText otpBox3 = view.findViewById(R.id.otpBox3);
        EditText otpBox4 = view.findViewById(R.id.otpBox4);
        EditText otpBox5 = view.findViewById(R.id.otpBox5);
        EditText otpBox6 = view.findViewById(R.id.otpBox6);
        Button btnNext = view.findViewById(R.id.btnNext);
        if (getArguments() != null) {
            verificationId = getArguments().getString("verificationId");
            mobile = getArguments().getString("mobile");
        }
        otpBox1.addTextChangedListener(new OTPTextWatcher(otpBox1,otpBox2));
        otpBox2.addTextChangedListener(new OTPTextWatcher(otpBox2, otpBox3));
        otpBox3.addTextChangedListener(new OTPTextWatcher(otpBox3, otpBox4));
        otpBox4.addTextChangedListener(new OTPTextWatcher(otpBox4, otpBox5));
        otpBox5.addTextChangedListener(new OTPTextWatcher(otpBox5, otpBox6));
        otpBox6.addTextChangedListener(new OTPTextWatcher(otpBox6, null));
        btnNext.setOnClickListener(v -> {
            String code = otpBox1.getText().toString() + otpBox2.getText().toString() + otpBox3.getText().toString() + otpBox4.getText().toString() + otpBox5.getText().toString() + otpBox6.getText().toString();
            // Navigate to OTP fragment
//            getActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new OtpVerificationFragment())
//                    .addToBackStack(null)
//                    .commit();
            if(verificationId != null){
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId,code);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(requireContext(),"Successfully verified",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(requireContext(),"Failed verification",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        return view;
    }
}
