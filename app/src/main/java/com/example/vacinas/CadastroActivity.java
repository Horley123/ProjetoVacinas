package com.example.vacinas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CadastroActivity extends AppCompatActivity {

    ImageButton btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btVoltar = findViewById(R.id.btn_voltar);
        btVoltar.setOnClickListener(v -> CadastroActivity.this.finish());
    }
}