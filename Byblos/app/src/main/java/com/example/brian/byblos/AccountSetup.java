package com.example.brian.byblos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by Brian on 2/6/2016.
 */
public class AccountSetup extends Activity {

    private Firebase ref;

    private EditText emailField;
    private EditText fNameField;
    private EditText lNameField;
    private EditText passwordField;
    private EditText password2Field;
    private EditText pNumberField;
    private EditText ageField;

    private Button next1;
    private Button back1;

    private String phoneNum;
    private String age;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String password2;


    private final String VERIFY = "cpp.edu";
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.account_setup);
        initUI();
    }

    private void initUI() {
        emailField = (EditText) findViewById( R.id.emailField );
        lNameField = (EditText) findViewById( R.id.lNameField );
        fNameField = (EditText) findViewById( R.id.fNameField );
        passwordField = (EditText) findViewById( R.id.passwordField );
        password2Field = (EditText) findViewById( R.id.password2Field );
        ageField = (EditText) findViewById( R.id.ageField);
        pNumberField = (EditText) findViewById( R.id.pNumberField);


        next1 = (Button) findViewById( R.id.next1 );
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClicked();

            }


        });

        back1 = (Button) findViewById( R.id.back1 );
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void onRegisterButtonClicked()
    {
        phoneNum  = pNumberField.getText().toString().trim();
        age = ageField.getText().toString().trim();
        fName = fNameField.getText().toString().trim();
        lName = lNameField.getText().toString().trim();
        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
        password2 = password2Field.getText().toString().trim();

        if (email.isEmpty() )
        {
            showToast( "Field 'email' cannot be empty." );
            return;
        }

        if (fName.isEmpty() )
        {
            showToast( "Field 'name' cannot be empty." );
            return;
        }

        if (password.isEmpty() )
        {
            showToast( "Field 'password' cannot be empty." );
            return;
        }

        if (password2.isEmpty() )
        {
            showToast( "Field 'password' cannot be empty." );
            return;
        }
        if(age.isEmpty()){
            showToast( "Field 'age' cannot be empty." );
            return;
        }
        if(phoneNum.isEmpty()){
            showToast( "Field 'phone number' cannot be empty." );
            return;
        }

        if (!password.equals(password2))
        {
            showToast( "Passwords entered dont match!" );
            return;
        }

        if( !age.isEmpty() )
        {
            this.age = age;
        }
        if( !phoneNum.isEmpty() )
        {
            this.phoneNum = phoneNum;
        }

        if( !email.isEmpty() )
        {
            this.email = email;
        }

        if( !fName.isEmpty() )
        {
            this.fName = fName;
        }
        if( !lName.isEmpty() )
        {
            this.lName = lName;
        }

        if( !password.isEmpty() )
        {
            this.password = password;
        }
        if( !password2.isEmpty() )
        {
            this.password2 = password2;
        }

        user = new User();

        if( email != null && email.length() > 8)
        {
            String checkEmail = email.substring(email.lastIndexOf("@") + 1);
            if(checkEmail.equals(VERIFY))
            {
                user.setEmail(email);
            }
        }

        if(fName != null )
        {
            user.setFirstName(fName);
        }

        if(lName != null )
        {
            user.setLastName(lName);
        }

        if ( password != null && password.equals(password2))
        {
            user.setPassword(password);
        }

        if(age != null)
        {
            user.setAge(age);
        }

        if(phoneNum != null)
        {
            user.setPhoneNumber(phoneNum);
        }
        String startString = email.substring(0,email.indexOf('@'));
        ref = new Firebase("https://byblos.firebaseio.com/");
        Firebase newUserRef = ref.child("users").child(startString);
        newUserRef.child("lastName").setValue(lName);
        newUserRef.child("firstName").setValue(fName);
        newUserRef.child("age").setValue(age);
        newUserRef.child("phonenumber").setValue(phoneNum);

        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                showToast("Created Account");

                ref = new Firebase("https://byblos.firebaseio.com/users");
                ref.authWithPassword(email, password, new Firebase.AuthResultHandler()

                {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        showToast("logged in");
                        Intent intent = new Intent(AccountSetup.this, CreditCard.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        showToast("Something happen");
                    }
                });
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                System.out.println(firebaseError.toString());
            }
        });


    }
    private void showToast( String msg )
    {
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }
}
