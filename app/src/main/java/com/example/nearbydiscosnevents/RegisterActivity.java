package com.example.nearbydiscosnevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 *
 * @author Jorge M. Herrera Tume
 */

public class RegisterActivity extends AppCompatActivity {

    /**
     *
     * @param txtIngresar: Variable asignada par abrir activity de login.
     */

    private static TextView txtIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtIngresar = findViewById(R.id.tvGoToLogin);

        txtIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLogin();
            }
        });
    }

    private void abrirLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
        finish();
    }
}
