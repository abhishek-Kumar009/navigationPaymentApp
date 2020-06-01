package com.example.myapplication;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class collectProfileData extends Fragment {

    RegistrationViewModel registrationViewModel;
    EditText fullName, bio;


    public collectProfileData() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect_profile_data, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        registrationViewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
        fullName = view.findViewById(R.id.fullName);
        bio = view.findViewById(R.id.bio);

        view.findViewById(R.id.nextRegPart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationViewModel.collectProfileData(fullName.getText().toString(), bio.getText().toString());
            }
        });

        registrationViewModel.registrationState.observe(getViewLifecycleOwner(), new Observer<RegistrationViewModel.RegistrationState>() {
            @Override
            public void onChanged(RegistrationViewModel.RegistrationState registrationState) {
                switch(registrationState){
                    case COLLECT_USER_PASSWORD:
                        //Pass the name and bio as arguments
                        NavDirections action = collectProfileDataDirections.actionCollectProfileDataToCollectUserPassword();
                        Navigation.findNavController(getView()).navigate(action);
                }
            }
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                registrationViewModel.userCancelledRegistration();
                Navigation.findNavController(getView()).navigate(R.id.login);
            }
        });





    }

}
