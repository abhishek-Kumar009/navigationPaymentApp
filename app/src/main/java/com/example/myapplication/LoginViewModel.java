package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class LoginViewModel extends ViewModel {

    public enum AuthenticationState{
        AUTHENTICATED,
        UNAUTHENTICATED,
        INVALID_AUTHENTICATION
    }
    String username;

    //LiveData to maintain the state of enum
    final MutableLiveData<AuthenticationState> authenticationState = new MutableLiveData<>();

    //Constructor that always initializes the model
    //as Unauthenticated
    public LoginViewModel(){
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
        this.username = "";
    }

    //Method to handle authentication
    public void authenticate(String username, String password){
        if(isValidUsernamePassword(username, password)){
            authenticationState.setValue(AuthenticationState.AUTHENTICATED);
            this.username = username;
        }else{
            authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION);
        }
    }

    private boolean isValidUsernamePassword(String username, String password){
        //Code to check authentication ---> Integrate with Firebase
        if(username.equals("lastairbender") && password.equals("1234")){
            return true;
        }
        return false;

    }
    public void refuseAuthentication(){
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }


}
