package com.example.nearbydiscosnevents;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nearbydiscosnevents.Models.Response.ResponseDocumentType;
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;
import com.example.nearbydiscosnevents.Utils.CustomDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author Jorge M. Herrera Tume
 */

public class RegisterActivity extends AppCompatActivity {

    /**
     *
     * @param txtIngresar: Variable asignada par abrir activity de login.
     */
    private IAPI mService;
    private static TextView txtIngresar;
    ImageView ivPersonalD,ivUserD;
    RelativeLayout rlPersonal, rlUser;
    Button btnPersonal,btnUser;
    AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
    AnimatedVectorDrawable animatedVectorDrawable;
    private static CustomDialog loadingDialog;

    private ArrayList<String> documentTypes = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mService = Common.getAPI();

        ivPersonalD = findViewById(R.id.ivPersonalData);
        ivUserD = findViewById(R.id.ivUserData);
        rlPersonal = findViewById(R.id.RLPersonalData);
        rlUser = findViewById(R.id.RLUserData);
        btnPersonal = findViewById(R.id.btnFirstStep);
        btnUser = findViewById(R.id.btnSecondStep);
        btnUser.setEnabled(false);
        loadingDialog = new CustomDialog(RegisterActivity.this);



        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                showPersonalDataDialog();
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                Drawable drawable = ivUserD.getDrawable();

                if(drawable instanceof AnimatedVectorDrawableCompat) {
                    ivUserD.setBackground(null);
                    rlUser.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_step_success));
                    btnUser.setVisibility(View.GONE);
                    animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) drawable;
                    animatedVectorDrawableCompat.start();
                } else if(drawable instanceof  AnimatedVectorDrawable){
                    ivUserD.setBackground(null);
                    rlUser.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_step_success));
                    btnUser.setVisibility(View.INVISIBLE);
                    animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                    animatedVectorDrawable.start();
                }

            }
        });
    }

    private void showPersonalDataDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog;
        builder.setTitle("REGISTRO - DATOS PERSONALES");
        builder.setMessage("Favor de completar los campos");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.registration_form_step_one_loader,null);

        MaterialEditText edtNames = register_layout.findViewById(R.id.metNamesPersonalData);
        MaterialEditText edtAddress = register_layout.findViewById(R.id.metAddressPersonalData);
        MaterialEditText edtDocument = register_layout.findViewById(R.id.metDocumentNumberPersonalData);
        MaterialSpinner spinnerDocumentT = register_layout.findViewById(R.id.spinnerTypeDocument);
        MaterialSpinner spinnerDayBirth = register_layout.findViewById(R.id.spinnerDayBirthDate);
        MaterialSpinner spinnerMonthBirth = register_layout.findViewById(R.id.spinnerMonthBirthDate);
        MaterialSpinner spinnerYearBirth = register_layout.findViewById(R.id.spinnerYearBirthDate);
        MaterialSpinner spinnerGenreT = register_layout.findViewById(R.id.spinnerGenre);

        loadDocumentTypes(spinnerDocumentT);

        builder.setView(register_layout);

        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Drawable drawable = ivPersonalD.getDrawable();

                if(drawable instanceof AnimatedVectorDrawableCompat) {
                    ivPersonalD.setBackground(null);
                    rlPersonal.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_step_success));
                    btnPersonal.setVisibility(View.INVISIBLE);
                    btnUser.setEnabled(true);
                    btnUser.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_button));
                    animatedVectorDrawableCompat = (AnimatedVectorDrawableCompat) drawable;
                    animatedVectorDrawableCompat.start();
                } else if(drawable instanceof  AnimatedVectorDrawable){
                    ivPersonalD.setBackground(null);
                    rlPersonal.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_step_success));
                    btnPersonal.setVisibility(View.INVISIBLE);
                    btnUser.setEnabled(true);
                    btnUser.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_button));
                    animatedVectorDrawable = (AnimatedVectorDrawable) drawable;
                    animatedVectorDrawable.start();
                }
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();

    }

    private void loadDocumentTypes(MaterialSpinner spinnerDocumentT) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", Common.API_KEY);
            System.out.println("jsonObject --------->>>>> "+jsonObject);

            RequestBody myreqbody = null;
            try {
                myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(String.valueOf(jsonObject))).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("myreqbody",myreqbody.toString());
            mService.obtainDocumentTypes(myreqbody).enqueue(new Callback<ResponseDocumentType>() {
                @Override
                public void onResponse(Call<ResponseDocumentType> call, Response<ResponseDocumentType> response) {
                    Log.i("response.body()",response.toString());
                    if (response.isSuccessful()){
                        if (response.body() != null){

                            PopulateSpinnerDocumentType(response.body(),spinnerDocumentT);
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseDocumentType> call, Throwable t) {
                    Log.e("response.body()",t.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void PopulateSpinnerDocumentType(ResponseDocumentType body, MaterialSpinner spinnerDocumentT) {
        for (int i = 0; i < body.getMessage().size(); i++){
            documentTypes.add(body.getMessage().get(i).getDescripcion().toString());
        }
        adapter = new ArrayAdapter<>(this,R.layout.spinner_document_type_items,documentTypes);
        adapter.setDropDownViewResource(R.layout.spinner_document_type_items);
        spinnerDocumentT.setAdapter(adapter);
        spinnerDocumentT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    String selected = spinnerDocumentT.getItemAtPosition(position).toString();
                    Log.i("selected",selected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // spinner.setItems("DNI", "C.E.");
        //txtIngresar = findViewById(R.id.tvGoToLogin);

       /* txtIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLogin();
            }
        });*/

    }


    private void abrirLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
        finish();
    }
}
