package com.example.assignment3onlineclothshop.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.assignment3onlineclothshop.interfaces.UserInterface;
import com.example.assignment3onlineclothshop.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {

    EditText inEmail, inPassword;
    Button signin;

    public  String BASE_URL = "http://10.0.2.2:4000/";


//    SharedPreferences preference;

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






        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nullValidation()) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    UserInterface userInterface = retrofit.create(UserInterface.class);
                    final User user = new User(inEmail.getText().toString().trim(), inPassword.getText().toString().trim());
                    Call<ResponseBody> call = userInterface.userLogin(user);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            Log.d("VAL", "success ");

                            if(response.isSuccessful()) {
                                Toast.makeText(getActivity(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                                Log.d("VAL", "success response ");

                                inEmail.setText("");
                                inPassword.setText("");
                                startActivity(new Intent(getActivity(), Dashboard.class));

                            }else {
                                try{
                                    Log.d("VAL", response.toString());

                                    Toast.makeText(getActivity(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Log.d("VAL", t.getLocalizedMessage());

                        }
                    });


                }


            }
        });

        return root;
    }

    public boolean nullValidation(){
        if (TextUtils.isEmpty(inEmail.getText().toString())){
            inEmail.setError("Required Field");
            return false;
        }
        else if (TextUtils.isEmpty(inPassword.getText().toString())){
            inPassword.setError("Required Field");
            return false;
        }

        return true;
    }
}