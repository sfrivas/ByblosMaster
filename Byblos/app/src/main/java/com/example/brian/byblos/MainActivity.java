package com.example.brian.byblos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    private Button joinNow;
    private Button loginButton;

    private EditText emailField;
    private EditText passwordField;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        joinNow = (Button) findViewById(R.id.joinnow_btn);
        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountSetup.class);
                startActivity(intent);
            }
        });

        initUI();
    }
    private void initUI()
    {
        emailField = (EditText) findViewById( R.id.email );
        passwordField = (EditText) findViewById( R.id.password);
        loginButton = (Button) findViewById( R.id.signin_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClicked();
            }
        });
    }

    public void onLoginButtonClicked() {

        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();

        if (email.isEmpty() )
        {
            showToast( "Field 'email' cannot be empty." );
            return;
        }
        if (password.isEmpty() )
        {
            showToast( "Field 'password' cannot be empty." );
            return;
        }

        if( !email.isEmpty() )
        {
            this.email = email;
        }

        if( !password.isEmpty() )
        {
            this.password = password;
        }

        Firebase ref = new Firebase("https://byblos.firebaseio.com/users");
        ref.authWithPassword(email, password , new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                showToast("logged in");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                showToast("Something happen");
            }
        });
    }
    private void showToast( String msg )
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
