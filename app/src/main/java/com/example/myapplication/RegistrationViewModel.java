package com.example.myapplication;



import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class RegistrationViewModel extends ViewModel {

    public enum RegistrationState{
        COLLECT_PROFILE_DATA,
        COLLECT_USER_PASSWORD,
        REGISTRATION_COMPLETED
    }
    String authToken;

    final MutableLiveData<RegistrationState> registrationState = new MutableLiveData<>(RegistrationState.COLLECT_PROFILE_DATA);

    public String getAuthToken(){
        return this.authToken;
    }

    public void collectProfileData(String fullname, String bio){
        //Validate and store the data

        //Change the state of registration state
        //to collect username & password
        registrationState.setValue(RegistrationState.COLLECT_USER_PASSWORD);

    }

    public void createAccountAndLogin(String username, String password){
        //create account in Firebase
        //Get the token from Firebase
        this.authToken = "";
        registrationState.setValue(RegistrationState.REGISTRATION_COMPLETED);
    }

    public boolean userCancelledRegistration(){
        registrationState.setValue(RegistrationState.COLLECT_PROFILE_DATA);
        this.authToken = "";
        return true;
    }


}
