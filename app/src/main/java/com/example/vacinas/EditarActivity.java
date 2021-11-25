package com.example.vacinas;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vacinas.databinding.ActivityEditarBinding;

public class EditarActivity extends AppCompatActivity {

    int idUser;
    Bundle bundle;
    Button btnEditar;
    SQLiteDatabase db;
    EditText email, senha, nome, ubs, marca, dose, data ,idade;
    int indice;
    DialogInterface.OnClickListener diAlteraInformacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        if(getIntent().hasExtra("idUser")){
            bundle = getIntent().getExtras();
            idUser = bundle.getInt("idUser");
        }

        nome = findViewById(R.id.input_nomee) ;
        email =  findViewById(R.id.input_emaile) ;
        idade =  findViewById(R.id.input_idadee) ;
        senha =  findViewById(R.id.input_senhae) ;


        ubs =  findViewById(R.id.input_UBSe) ;
        marca = findViewById(R.id.input_marcae) ;
        dose =  findViewById(R.id.input_dosee) ;
        data = findViewById(R.id.input_datae) ;


        btnEditar = findViewById(R.id.btn_editar);
        diAlteraInformacoes = new DialogInterface.OnClickListener(){
        @Override
            public void onClick(DialogInterface dialog, int wich){
           String nomeE = nome.getText().toString();
           String emailE = email.getText().toString();
           String senhaE = senha.getText().toString();
           String idadeE = idade.getText().toString();

            String Cubs = ubs.getText().toString();
            String Cmarca = marca.getText().toString();
            String Cdose = dose.getText().toString();
            String Cdata = data.getText().toString();


            try{
                ContentValues valor = new ContentValues();
                ContentValues valor2 = new ContentValues();

                valor.put("nome", nomeE);
                valor.put("email", emailE);
                valor.put("senha", senhaE);
                valor.put("idade", idadeE);

                valor2.put("nomeUbs", Cubs);
                valor2.put("marca", Cmarca);
                valor2.put("dose", Cdose);
                valor2.put("data", Cdata);

                db = openOrCreateDatabase("dbVacinas", Context.MODE_PRIVATE, null);
                if(valor != null) {
                    db.update("usuarios", valor, "idUser =?", new String[]{String.valueOf(idUser)});
                }
                if(valor2 != null) {
                    db.update("Vacina", valor2, "idR=?", new String[]{String.valueOf(idUser)});
                }
                Intent iTelaPrincipal = new Intent(EditarActivity.this, MainActivityUser.class);
                iTelaPrincipal.putExtra("idUser",idUser);
                startActivity(iTelaPrincipal);

            }catch(Exception e){
            MostrarMensagem("Algo deu errado: "+e.toString());
            }

        }
    };
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(EditarActivity.this);
                dialogo.setTitle("Confirma");
                dialogo.setMessage("Deseja alterar as informações?");
                dialogo.setNegativeButton("Não", null);
                dialogo.setPositiveButton("Sim", diAlteraInformacoes);
                dialogo.show();
            }
        });

    }
    public  void MostrarMensagem( String  str){

        AlertDialog.Builder dialogo = new AlertDialog.Builder(EditarActivity.this);
        dialogo.setTitle("Aviso");
        dialogo.setNeutralButton("OK", null);
        dialogo.setMessage(str);
        dialogo.show();
    }
}