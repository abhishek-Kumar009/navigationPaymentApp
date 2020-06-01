package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainFragment extends Fragment {

    LoginViewModel loginViewModel;


    public mainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //Check if the user is Authenticated or not

        //Get reference to the shared LoginViewModel
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        //set an observer to the loginViewModel.authenticationState
        //to observe the change in enum values

        loginViewModel.authenticationState.observe(getViewLifecycleOwner(), new Observer<LoginViewModel.AuthenticationState>() {
            @Override
            public void onChanged(LoginViewModel.AuthenticationState authenticationState) {
                switch(authenticationState){
                    case AUTHENTICATED:
                        performActivity();
                        break;
                    case UNAUTHENTICATED:
                        Navigation.findNavController(getView()).navigate(R.id.login);
                        break;
                }
            }
        });


    }

    private void performActivity(){
        getView().findViewById(R.id.view_transactions_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = mainFragmentDirections.actionMainFragmentToViewTransaction();
                Navigation.findNavController(v).navigate(action);
            }
        });

        getView().findViewById(R.id.send_money_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = mainFragmentDirections.actionMainFragmentToSpecifyAmount();
                Navigation.findNavController(v).navigate(action);
            }
        });

        getView().findViewById(R.id.view_balance_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = mainFragmentDirections.actionMainFragmentToViewBalance();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

}
