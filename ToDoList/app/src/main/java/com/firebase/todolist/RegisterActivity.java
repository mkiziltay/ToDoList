package com.firebase.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    EditText mail,pass,repass;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth = FirebaseAuth.getInstance();
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repass);
        register = findViewById(R.id.save);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mail.getText()) && !TextUtils.isEmpty(pass.getText()) && pass.getText().toString() .equals(repass.getText().toString())) {
                    String email = mail.getText().toString();
                    String password = pass.getText().toString();
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Kullanıcı kaydınız oluşturuldu...",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }else{Toast.makeText(getApplicationContext(),"Kullanıcı kaydı mevcut veya hatalı E-Mail...",Toast.LENGTH_SHORT).show();}
                        }
                    }) ;
                } else if (TextUtils.isEmpty(mail.getText()) || TextUtils.isEmpty(pass.getText()) || !mail.getText().toString().contains(".") || !mail.getText().toString().contains("@")){
                    Toast.makeText(getApplicationContext(),"@ simgesi kullanınız. Mail adresinizi tamamlayınız.",Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(getApplicationContext(),"Şifreleriniz uyuşmuyor  ...",Toast.LENGTH_SHORT).show();
                    Log.i("testLog","**"+TextUtils.isEmpty(mail.getText())+"**"+TextUtils.isEmpty(pass.getText())+"**"+pass.getText().toString().equals(repass.getText().toString()));
                }
            }
        });
    }
}