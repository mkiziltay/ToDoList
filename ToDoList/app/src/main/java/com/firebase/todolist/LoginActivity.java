package com.firebase.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView register;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        definition();
        actions();
    }

    private void definition() {
        fAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        password = findViewById(R.id.password);
    }

    private void actions() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String username = email.getText().toString();
               String passcode = password.getText().toString();
                fAuth.signInWithEmailAndPassword(username,passcode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Hoş Geldiniz...",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else {Toast.makeText(getApplicationContext(),"Kullanıcı kaydı bulunamadı...",Toast.LENGTH_SHORT).show();}
                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}