package com.example.jil.myproject_prototype2sqlite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intermediary_Activty extends AppCompatActivity implements View.OnClickListener {

    Button btnSignIn;
    Button btnSignup, btnMain, toolbarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediary__activty);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignup = (Button) findViewById(R.id.btnSignUp);
        btnMain = (Button) findViewById(R.id.btnMain);
        toolbarBtn = (Button) findViewById(R.id.toToolbar);
        btnSignIn.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnMain.setOnClickListener(this);
        toolbarBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {

            case R.id.btnSignIn:
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSignUp:
                intent = new Intent(this, signup_activity.class);
                startActivity(intent);
                break;

            case R.id.btnMain:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.toToolbar:
                intent = new Intent(this, toolbar10.class);
                startActivity(intent);
                break;

            default:
                finish();
                break;
        }
    }
}
