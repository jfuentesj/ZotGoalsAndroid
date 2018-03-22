package com.fuentes.julio.zotgoalsandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class myNewMainPageUseThis extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_new_main_page_use_this);

        button = (Button) findViewById(R.id.planbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openUserRegister();
            }
        });
    }

    public void openUserRegister(){
        Intent intent = new Intent(this, myPlan.class);
        startActivity(intent);
    }
}
