package com.example.foodinformation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDataFragment extends Fragment {

    private TextView nameTextView;
    private TextView emailTextView;
    private TextView mobileTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data, container, false);

        nameTextView = view.findViewById(R.id.nameTextView1);
        emailTextView = view.findViewById(R.id.emailTextView2);
        mobileTextView = view.findViewById(R.id.mobileTextView3);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String name = arguments.getString("Name");
            String email = arguments.getString("Email");
            String mobile = arguments.getString("Mobile");

            nameTextView.setText(name);
            emailTextView.setText(email);
            mobileTextView.setText(mobile);
        }

        return view;
    }
}
