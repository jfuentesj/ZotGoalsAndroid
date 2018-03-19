package com.fuentes.julio.zotgoalsandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class titlepage extends AppCompatActivity {
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlepage);

        button = (Button) findViewById(R.id.myRegisterButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openUserRegister();
            }
        });

        button2 = (Button) findViewById(R.id.mySignInButton);
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
        Intent intent = new Intent(this, userSignIn.class);
        startActivity(intent);
    }
}
