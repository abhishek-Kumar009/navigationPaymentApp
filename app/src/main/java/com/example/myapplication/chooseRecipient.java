package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class chooseRecipient extends Fragment {


    public chooseRecipient() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final TextView viewAmount = view.findViewById(R.id.viewMoney);
        final float amount = chooseRecipientArgs.fromBundle(getArguments()).getAmount();
        viewAmount.setText("Sending amount: "+amount);


        view.findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    EditText recipient = (EditText)getView().findViewById(R.id.input_recipient);
                    String recipientName = recipient.getText().toString();
                    if(!recipientName.isEmpty()){
                        NavDirections action = chooseRecipientDirections.actionChooseRecipientToConfirmMoneySent(recipientName, amount);
                        Navigation.findNavController(v).navigate(action);
                    }else{
                        Snackbar snackbar = Snackbar.make(v, "Invalid name!", Snackbar.LENGTH_LONG);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        snackbar.show();
                    }


            }
        });
        view.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = chooseRecipientDirections.actionChooseRecipientToMainFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

}
