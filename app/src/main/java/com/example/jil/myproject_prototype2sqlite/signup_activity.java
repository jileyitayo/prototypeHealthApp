package com.example.jil.myproject_prototype2sqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signup_activity extends AppCompatActivity {

    private EditText mFirstnameView;
    private EditText mLastnameView;
    private EditText mEmailView;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;

    SQLiteDbAdapter sqLiteDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        sqLiteDbAdapter = new SQLiteDbAdapter(this);
        mFirstnameView = (EditText) findViewById(R.id.first_name);
        mLastnameView = (EditText) findViewById(R.id.last_name);
        mEmailView = (EditText) findViewById(R.id.email);
        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mConfirmPasswordView = (EditText) findViewById(R.id.confirm_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mUserSignUpButton = (Button) findViewById(R.id.username_sign_up_button);
        TextView mUserSignInMessage = (TextView) findViewById(R.id.tvSignUpMessage);

        mUserSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mUserSignInMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Redirect(signup_activity.this, LoginActivity.class);
            }
        });


    }

    public void attemptLogin()
    {
        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mFirstnameView.setError(null);
        mLastnameView.setError(null);
        mEmailView.setError(null);

        long rowNo = 0;

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String fName = mFirstnameView.getText().toString();
        String LName = mLastnameView.getText().toString();
        String email = mEmailView.getText().toString();
        String confirmedPassword = mConfirmPasswordView.getText().toString();

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
        }


        if (!TextUtils.isEmpty(username) && !isnameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_Name));
        }
        if (!TextUtils.isEmpty(fName) && !isnameValid(fName)) {
            mFirstnameView.setError(getString(R.string.error_invalid_Name));
        }

        if (!TextUtils.isEmpty(LName) && !isnameValid(LName)) {
            mLastnameView.setError(getString(R.string.error_invalid_Name));
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
        }

        if(!isPasswordConfirmed(password, confirmedPassword))
        {
            mConfirmPasswordView.setError(getString(R.string.error_field_confirmed_required));
        }

        if(TextUtils.isEmpty(confirmedPassword))
        {
            mConfirmPasswordView.setError(getString(R.string.error_field_required));
        }

        rowNo =  sqLiteDbAdapter.Insert(fName, LName, email, username, password);
        if(rowNo <= 0)
        {
            Message.messageToast(this, "Already a user!");
        }

        /*
        else
        {
            Message.messageToast(this, "User Successfully Added");
        }
        */

    }

    private boolean isnameValid(String name) {
        //TODO: Replace this with your own logic
        return name.length() >= 3;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public void Redirect(Context context, Class returnsThis)
    {
        Intent intent = new Intent(context, returnsThis);
        startActivity(intent);
    }

    private boolean isPasswordConfirmed(String password, String confirmPassword) {
        //TODO: Replace this with your own logic
        return password.equals(confirmPassword);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
