package com.hrfsoftlab.jakir.encryptiondecryption_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    private TextView tvUserName;
    private TextView tvPassword;
    private Button btnEncryption;
    private Button btnDecryption;
    private EditText etUserName;
    private EditText etPassword;

    private String userName;
    private String password;
    String encryptedPassword;
    String encryptedUserName;
    AESCrypt aesCrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aesCrypt =new AESCrypt();
        initialization();
        eventClickListener();
    }

    private void eventClickListener() {
        btnEncryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=etUserName.getText().toString().trim();
                password=etPassword.getText().toString().trim();
                try {
                    encryptedUserName=AESCrypt.encrypt(userName);
                    encryptedPassword=AESCrypt.encrypt(password);

                    tvUserName.setText(encryptedUserName);
                    tvPassword.setText(encryptedPassword);
                    Log.e("Encrypted Name",encryptedUserName);
                    Log.e("Encrypted Password",encryptedPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnDecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name=AESCrypt.decrypt(encryptedUserName);
                    String password=AESCrypt.decrypt(encryptedPassword);
                    tvUserName.setText(name);
                    tvPassword.setText(password);
                    Log.e("User Name",name);
                    Log.e("Password",password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialization() {
        etUserName= (EditText) findViewById(R.id.etUserName);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnDecryption= (Button) findViewById(R.id.btnDecryption);
        btnEncryption= (Button) findViewById(R.id.btnEncryption);
        tvUserName= (TextView) findViewById(R.id.tvUserName);
        tvPassword= (TextView) findViewById(R.id.tvPassword);

    }
}
