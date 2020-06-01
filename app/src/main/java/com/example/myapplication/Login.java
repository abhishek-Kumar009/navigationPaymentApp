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
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


public class Login extends Fragment {
    LoginViewModel loginViewModel;
    EditText username,  password;
    Button loginButton, registerButton;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.txtusername);
        password = view.findViewById(R.id.txtpassword);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.registerButton);

        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.authenticate(username.getText().toString(), password.getText().toString());

            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                loginViewModel.refuseAuthentication();
                //Navigate somewhere on pressing back
            }
        });

        loginViewModel.authenticationState.observe(getViewLifecycleOwner(), new Observer<LoginViewModel.AuthenticationState>() {
            @Override
            public void onChanged(LoginViewModel.AuthenticationState authenticationState) {
                switch (authenticationState){
                    case AUTHENTICATED:
                        Navigation.findNavController(getView()).popBackStack();
                        break;
                    case INVALID_AUTHENTICATION:
                        Snackbar snackbar = Snackbar.make(getView(), "Invalid credentials!", Snackbar.LENGTH_LONG);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        snackbar.show();
                        break;
                }
            }
        });

    }

}
