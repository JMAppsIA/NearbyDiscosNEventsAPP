package com.example.nearbydiscosnevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;
import com.example.nearbydiscosnevents.Utils.CustomDialog;

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
    private static CustomDialog loadingDialog;
    private IAPI mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mService = Common.getAPI();

        txtRegistrar = findViewById(R.id.tvCreateAccountLogin);
        btnIngresar = findViewById(R.id.btnLogin);
        loadingDialog = new CustomDialog(LoginActivity.this);
        txtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirRegistro();
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source = "LOGIN";
                loadingDialog.startLoadingDialog(source);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissLoadingDialog();
                        abrirHome();
                    }
                },3000);
            }
        });

    }

    private void abrirHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }


    private void abrirRegistro() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(intent);
        finish();
    }
}
