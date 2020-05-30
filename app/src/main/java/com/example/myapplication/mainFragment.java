package com.example.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class mainFragment extends Fragment {


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

        view.findViewById(R.id.view_transactions_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = mainFragmentDirections.actionMainFragmentToViewTransaction();
                Navigation.findNavController(v).navigate(action);
            }
        });

        view.findViewById(R.id.send_money_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = mainFragmentDirections.actionMainFragmentToSpecifyAmount();
                Navigation.findNavController(v).navigate(action);
            }
        });

        view.findViewById(R.id.view_balance_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = mainFragmentDirections.actionMainFragmentToViewBalance();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

}
