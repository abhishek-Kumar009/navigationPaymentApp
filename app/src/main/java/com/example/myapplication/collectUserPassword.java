package com.example.myapplication;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class collectUserPassword extends Fragment {

    RegistrationViewModel registrationViewModel;
    LoginViewModel loginViewModel;
    EditText username, password;


    public collectUserPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect_user_password, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.usernameReg);
        password = view.findViewById(R.id.passwordReg);

        ViewModelProvider provider = new ViewModelProvider(requireActivity());
        registrationViewModel = provider.get(RegistrationViewModel.class);
        loginViewModel = provider.get(LoginViewModel.class);

        view.findViewById(R.id.registerLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationViewModel.createAccountAndLogin(username.getText().toString(), password.getText().toString());
            }
        });

        registrationViewModel.registrationState.observe(getViewLifecycleOwner(), new Observer<RegistrationViewModel.RegistrationState>() {
            @Override
            public void onChanged(RegistrationViewModel.RegistrationState registrationState) {
                switch (registrationState){
                    case REGISTRATION_COMPLETED:
                        loginViewModel.authenticationState.setValue(LoginViewModel.AuthenticationState.AUTHENTICATED);
                        Navigation.findNavController(getView()).popBackStack(R.id.mainFragment, false);
                }
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                registrationViewModel.userCancelledRegistration();
                Navigation.findNavController(getView()).popBackStack(R.id.login, false);
            }
        });

    }

}
