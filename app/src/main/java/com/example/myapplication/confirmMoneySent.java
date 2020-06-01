package com.example.myapplication;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class confirmMoneySent extends Fragment {


    public confirmMoneySent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_money_sent, container, false);
    }
    @Override
    public void  onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        try{
            float amount = confirmMoneySentArgs.fromBundle(getArguments()).getMoneySent();
            String recipientName = confirmMoneySentArgs.fromBundle(getArguments()).getRecipientName();

            TextView confirmationMessage = (TextView)getView().findViewById(R.id.confirmation_message);
            confirmationMessage.setText("$"+amount + " was sent to "+recipientName);

            //Back button takes to the main fragment
            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    Navigation.findNavController(getView()).popBackStack(R.id.mainFragment, false);
                }
            });


        }catch (Exception e){
            Log.d("Exception", e.toString());

        }


    }

}
