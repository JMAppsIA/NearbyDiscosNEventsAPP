package com.example.nearbydiscosnevents;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nearbydiscosnevents.Utils.LoadingDialog;

/**
 *
 * @author Jorge M. Herrera Tume
 */

public class LoginActivity extends AppCompatActivity {

    /**
     *
     * @param txtRegistrar: Variable asignada par abrir activity de registro.
     */
    private static Button btnIngresar;
    private static TextView txtRegistrar;
    private static LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtRegistrar = findViewById(R.id.tvCreateAccountLogin);
        btnIngresar = findViewById(R.id.btnLogin);
        loadingDialog = new LoadingDialog(LoginActivity.this);
        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirRegistro();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadingDialog.startLoadingDialog();

            }
        });

    }


    private void abrirRegistro() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }
}
