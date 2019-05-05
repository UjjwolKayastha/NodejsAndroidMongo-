package com.example.assignment3onlineclothshop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.assignment3onlineclothshop.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    EditText inEmail, inPassword;
    Button signin;


    SharedPreferences preference;

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static LoginFragment newInstance(int index) {
        LoginFragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        inEmail = root.findViewById(R.id.emailLogin)   ;
        inPassword = root.findViewById(R.id.passwordLogin);
        signin = root.findViewById(R.id.btnSignin);

        preference = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);






        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = preference.getString("regEmail", "");
                String pass = preference.getString("regPassword", "");

                Log.d("val", email+pass);

                if(inEmail.getText().toString().equals(email) && inPassword.getText().toString().equals(pass) ){
                    Toast.makeText(getActivity(), "LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), Dashboard.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getActivity(), "INVALID USERNAME OR PASSWORD", Toast.LENGTH_SHORT).show();
                }


            }
        });

        return root;
    }
}