package com.example.nearbydiscosnevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtRegistrar = findViewById(R.id.tvCreateAccountLogin);
        btnIngresar = findViewById(R.id.btnLogin);

        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirRegistro();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void abrirRegistro() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }
}
