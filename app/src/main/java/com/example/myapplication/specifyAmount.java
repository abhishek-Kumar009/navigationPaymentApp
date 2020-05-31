package com.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class specifyAmount extends Fragment {


    public specifyAmount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText amtSent = (EditText) getView().findViewById(R.id.input_amount);
                try{
                    float amount = Float.parseFloat(amtSent.getText().toString());
                    NavDirections action = specifyAmountDirections.actionSpecifyAmountToChooseRecipient(amount);
                    Navigation.findNavController(v).navigate(action);
                }catch(NumberFormatException exception){
                    Log.d("NumberFormatException: ", exception.toString());
                    Snackbar snackbar = Snackbar.make(v, "Invalid amount!", Snackbar.LENGTH_LONG);
                    snackbar.setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.show();
                }




            }
        });

        view.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavDirections action = specifyAmountDirections.actionSpecifyAmountToMainFragment();
                Navigation.findNavController(v).navigate(action);

            }
        });
    }

}
