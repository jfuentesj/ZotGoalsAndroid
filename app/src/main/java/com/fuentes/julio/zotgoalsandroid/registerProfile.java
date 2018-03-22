package com.fuentes.julio.zotgoalsandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class registerProfile extends AppCompatActivity {
    private Button button;
    private EditText nameOfUser;
    private EditText dateOfBirth;
    private EditText height;
    private EditText weight;
    private EditText desiredWeight;
    private IUserAPI myuserAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        button = (Button) findViewById(R.id.plsRegister);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == button) {
                    //registerUser();
                    user myUser = new user();
                    myUser.name = nameOfUser.getText().toString().trim();
                    myUser.age = Integer.parseInt(dateOfBirth.getText().toString().trim());
                    myUser.heightInInches = Float.parseFloat(height.getText().toString().trim());
                    myUser.weightInPounds = Float.parseFloat(weight.getText().toString().trim());
                    myUser.endGoalWeightInPounds = Float.parseFloat(desiredWeight.getText().toString().trim());
                    SharedPreferences sharedPref = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
                    String retrievedString = sharedPref.getString("myAccessToken", "Cannot Get Access String");
                    try {
                        myuserAPI.update(myUser,"Bearer " + retrievedString).enqueue(updateProfile);

                    } catch (Exception ex)
                    {
                        String a = "";
                    }
                }
                openUserRegister();
            }
        });
        createmyuserAPI();
        nameOfUser = (EditText) findViewById(R.id.getName);
        dateOfBirth = (EditText) findViewById(R.id.getAge);
        height = (EditText) findViewById(R.id.getHeight);
        weight = (EditText) findViewById(R.id.getWeight);
        desiredWeight = (EditText) findViewById(R.id.getDesiredWeight);
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
        Intent intent = new Intent(this, myNewMainPageUseThis.class);
        startActivity(intent);
    }

    Callback<myBooleanResponse> updateProfile = new Callback<myBooleanResponse>() {
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
