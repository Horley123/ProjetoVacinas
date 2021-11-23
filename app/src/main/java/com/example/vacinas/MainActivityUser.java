package com.example.vacinas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.*;


public class MainActivityUser extends AppCompatActivity {

    Button btnTelaCadastro;
    Button btnTelaLogin;
    Intent iTelaCadastro;
    Intent iTelaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        btnTelaCadastro = findViewById(R.id.btnIr);
        btnTelaLogin =  findViewById(R.id.btnIrLogin);

        btnTelaLogin.setOnClickListener(v -> {
            iTelaLogin = new Intent(MainActivityUser.this, LoginActivity.class);
            startActivity(iTelaLogin);
        });
        btnTelaCadastro.setOnClickListener(v -> {
            iTelaCadastro = new Intent(MainActivityUser.this, CadastroActivity.class);
            startActivity(iTelaCadastro);
        });
    }

}