package com.example.nearbydiscosnevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearbydiscosnevents.Models.Request.LoginUser.LoginUserRequest;
import com.example.nearbydiscosnevents.Models.Request.LoginUser.Payload;
import com.example.nearbydiscosnevents.Models.Request.LoginUser.Request;
import com.example.nearbydiscosnevents.Models.Request.LoginUser.Trace;
import com.example.nearbydiscosnevents.Models.Response.ResponseLoginUser;
import com.example.nearbydiscosnevents.Models.User;
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;
import com.example.nearbydiscosnevents.Utils.CustomDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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
    private static EditText txtUser,txtPassUser;

    private static CustomDialog loadingDialog;

    private static RelativeLayout RelativeLogin;

    private IAPI mService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/arkhip_font.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_login);



        mService = Common.getAPI();

        RelativeLogin = findViewById(R.id.RLLogin);

        txtRegistrar = findViewById(R.id.tvCreateAccountLogin);
        txtUser = findViewById(R.id.edtUserName);
        txtPassUser = findViewById(R.id.edtPassword);

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
                final String source = "LOGIN";
                loadingDialog.startLoadingDialog(source);

                if(TextUtils.isEmpty(txtUser.getText().toString())){
                    loadingDialog.dismissLoadingDialog();
                    Toast.makeText(LoginActivity.this, "Favor de ingresar usuario", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(txtPassUser.getText().toString())){
                    loadingDialog.dismissLoadingDialog();
                    Toast.makeText(LoginActivity.this, "Favor de ingresar clave", Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("key",Common.API_KEY);
                    jsonObject.put("nomUsuario",txtUser.getText().toString());
                    jsonObject.put("passUsuario",txtPassUser.getText().toString());
                    System.out.println("jsonObject --------->>>>> "+jsonObject);

                    RequestBody myreqbody = null;
                    try {
                        myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                                (new JSONObject(String.valueOf(jsonObject))).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mService.LoginUser(myreqbody).enqueue(new Callback<ResponseLoginUser>() {
                        @Override
                        public void onResponse(Call<ResponseLoginUser> call, Response<ResponseLoginUser> response) {
                            loadingDialog.dismissLoadingDialog();
                            if(response.body().getMessage().get(0).getPersonId() != null) {
                                Common.usuarioActual = response.body().getMessage().get(0);
                                abrirHome();
                            } else {
                                Snackbar.make(RelativeLogin,"Usuario o contrasena incorrectos.",Snackbar.LENGTH_LONG).show();
                            }



                        }

                        @Override
                        public void onFailure(Call<ResponseLoginUser> call, Throwable t) {
                            Log.e("Failed Login",t.getMessage().toString());
                            loadingDialog.dismissLoadingDialog();
                            if(t.getMessage().contains("failed to connect")) {
                                Snackbar.make(RelativeLogin,"Estamos presentando inconvenientes. Por favor, reintenta en unos minutos.",Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });

                    /*compositeDisposable.add(mService.LoginUser(jsonObject)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(ResponseLoginUser -> {
                                if(ResponseLoginUser.isSuccess()){
                                    Log.d("response",ResponseLoginUser.getMessage().get(0).toString());
                                } else {

                                }
                            }));*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }




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
