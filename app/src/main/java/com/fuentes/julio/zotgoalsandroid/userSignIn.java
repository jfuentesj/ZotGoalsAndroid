package com.fuentes.julio.zotgoalsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userSignIn extends AppCompatActivity {
    private Button buttonRegister;
    private Button buttonSignIn;
    private IUserAPI myuserAPI;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        buttonRegister = (Button) findViewById(R.id.notRegisteredYet);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openUserRegister();
            }
        });

        buttonSignIn = (Button) findViewById(R.id.signInButton);
        buttonSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openUserRegister2();
            }
        });
        createmyuserAPI();
    }
    public void createmyuserAPI(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IUserAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myuserAPI = retrofit.create(IUserAPI.class);
    }
    public void openUserRegister(){
        Intent intent = new Intent(this, UserLogin.class);
        startActivity(intent);
    }
    public void openUserRegister2(){
        Intent intent = new Intent(this, myMainPage.class);
        startActivity(intent);
    }

    //@Override
    public void onClick(View view) {

        if (view == buttonRegister) {
            //registerUser();
            user myUser = new user();
            myUser.Username = editTextEmail.getText().toString().trim();
            myUser.Password = editTextPassword.getText().toString().trim();
            myuserAPI.signUp(myUser).enqueue(signUpCallback);
        }
    }

    Callback<myBooleanResponse> signUpCallback = new Callback<myBooleanResponse>() {
        public void onResponse(Call<myBooleanResponse> call , Response<myBooleanResponse> response){
            if (response.isSuccessful())
            {
                myBooleanResponse questions = response.body();
            }
            else
            {
                Log.d("Callback", "Code: " + response.code() + "Message: " + response.message());
            }
        }
        public void onFailure(Call<myBooleanResponse> call, Throwable t)
        {
            t.printStackTrace();
        }
    };
}
