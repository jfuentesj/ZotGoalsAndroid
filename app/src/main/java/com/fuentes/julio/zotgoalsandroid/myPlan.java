package com.fuentes.julio.zotgoalsandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myPlan extends AppCompatActivity {
    private Button button;
    private EditText foodEaten;
    private IFoodAPI myFoodAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);

        button = (Button) findViewById(R.id.plsRegister);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == button) {
                    //registerUser();
                    String name = foodEaten.getText().toString().trim();
                    try {
                        myFoodAPI.search(name).enqueue(updateProfile);

                    } catch (Exception ex)
                    {
                        String a = "";
                    }
                }
                openUserRegister();
            }
        });
        createmyuserAPI();
        foodEaten = (EditText) findViewById(R.id.typefood);
    }

    public void createmyuserAPI(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFoodAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myFoodAPI = retrofit.create(IFoodAPI.class);
    }

    public void openUserRegister(){
        Intent intent = new Intent(this, myNewMainPageUseThis.class);
        startActivity(intent);
    }

    Callback<List<myFoodNDBResponse>> updateProfile = new Callback<List<myFoodNDBResponse>>() {
        public void onResponse(Call<List<myFoodNDBResponse>> call , Response<List<myFoodNDBResponse>> response){
            if (response.isSuccessful())
            {
                String a = "";
            }
            else
            {
                Log.d("Callback", "Code: " + response.code() + "Message: " + response.message());
            }
        }
        public void onFailure(Call<List<myFoodNDBResponse>> call, Throwable t)
        {
            t.printStackTrace();
        }
    };
}
