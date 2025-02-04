package com.example.login;

import static com.example.login.R.layout.activity_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout TextInputLayout4;
    private EditText txtMail;
    private TextInputEditText txtPassword;
    private Button btnLogin;
    private TextView lblRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        TextInputLayout4 = (TextInputLayout)findViewById(R.id.TextInputLayout4);

        txtMail = (EditText) findViewById(R.id.txtMail);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        lblRegister = (TextView) findViewById(R.id.lblRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            userLogin();
        });

        lblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });


    }/* End onCreate */

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }// End openRegisterActivity

    public void userLogin() {
        String mail = txtMail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            txtMail.setError("Ingrese un correo");
            txtMail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            txtPassword.requestFocus();
        } else {

            mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, InicioActivity.class));
                    } else {
                        Log.w("TAG", "Error:", task.getException());
                    }
                }
            });

        }

    }


}