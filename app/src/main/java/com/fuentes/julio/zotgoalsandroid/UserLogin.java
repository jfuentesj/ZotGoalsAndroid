package com.fuentes.julio.zotgoalsandroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLogin extends AppCompatActivity implements View.OnClickListener {
    private IUserAPI myuserAPI;
    private Button buttonRegister;
    private Button buttonSign;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        buttonSign = (Button) findViewById(R.id.goToSignIn);
        buttonSign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == buttonRegister) {
                    //registerUser();
                    user myUser = new user();
                    myUser.Username = editTextUsername.getText().toString().trim();
                    myUser.Password = editTextPassword.getText().toString().trim();
                    myuserAPI.signUp(myUser).enqueue(signUpCallback);
                }
                openUserRegister();
            }
        });

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == buttonRegister) {
                    //registerUser();
                    user myUser = new user();
                    myUser.Username = editTextUsername.getText().toString().trim();
                    myUser.Password = editTextPassword.getText().toString().trim();
                    myuserAPI.signUp(myUser).enqueue(signUpCallback);
                }
                openUserRegister2();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        editTextUsername = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        textViewSignin.setOnClickListener(this);
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

    public void openUserRegister() {
        Intent intent = new Intent(this, userSignIn.class);
        startActivity(intent);
    }

    public void openUserRegister2() {
        Intent intent = new Intent(this, registerProfile.class);
        startActivity(intent);
    }

    private void registerUser() {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UserLogin.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UserLogin.this, "Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view == buttonRegister) {
            //registerUser();
            user myUser = new user();
            myUser.Username = editTextUsername.getText().toString().trim();
            myUser.Password = editTextPassword.getText().toString().trim();
            myuserAPI.signUp(myUser).enqueue(signUpCallback);
        }

    }

    Callback<myBooleanResponse> signUpCallback = new Callback<myBooleanResponse>() {
        public void onResponse(Call<myBooleanResponse> call ,Response<myBooleanResponse> response){
            if (response.isSuccessful())
            {
                String accessToken = response.raw().header("ACCESS-TOKEN");
                SharedPreferences sharedPref = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("myAccessToken", accessToken);
                editor.commit();
                /*
                SharedPreferences sharedPref = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
                String retrievedString = sharedPref.getString("myAccessToken", defaultValue);
                */
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
