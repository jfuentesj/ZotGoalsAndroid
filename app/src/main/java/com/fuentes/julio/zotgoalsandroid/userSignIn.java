package com.fuentes.julio.zotgoalsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class userSignIn extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        button = (Button) findViewById(R.id.notRegisteredYet);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openUserRegister();
            }
        });

        button2 = (Button) findViewById(R.id.signInButton);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openUserRegister2();
            }
        });
    }
    public void openUserRegister(){
        Intent intent = new Intent(this, UserLogin.class);
        startActivity(intent);
    }
    public void openUserRegister2(){
        Intent intent = new Intent(this, myMainPage.class);
        startActivity(intent);
    }
}
