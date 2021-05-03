package com.example.qr_code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class LoginActivity extends AppCompatActivity {
	// class for Logging into the App.
	///<summary>
	///This class consists of three attributes.
	///</summary>
	
    EditText email, password;
    Button login;
	
	// onCreate() is a builtin method defined in 'appcompt' class.
	// This mthod is run for the first time when this activity is created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
			
			//onClick() is an instantiation of the object login. Its a built in method, defined in 'view' library.
            @Override
            public void onClick(View v) {
                String emailLogin = email.getText().toString();
                String passwordLogin = password.getText().toString();

                if (emailLogin.equals("admin")) {
                    if (passwordLogin.equals("admin")){
                        Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(myIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong password !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong email !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

	//onBackPressed() is a builtin method, defined in 'appcompat' library.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
