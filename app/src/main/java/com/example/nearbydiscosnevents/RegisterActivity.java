package com.example.nearbydiscosnevents;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearbydiscosnevents.Models.Response.ResponseCreateUser;
import com.example.nearbydiscosnevents.Models.Response.ResponseDocumentType;
import com.example.nearbydiscosnevents.Models.Response.ResponseGenreType;
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;
import com.example.nearbydiscosnevents.Utils.CustomDialog;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    private static IAPI mService;
    /**
     *
     * @param txtIngresar: Variable asignada par abrir activity de login.
     */
    RelativeLayout rootLayout;
    private TextView txtIngresar;
    ImageView ivPersonalD,ivUserD;
    RelativeLayout rlPersonal, rlUser;
    Button btnPersonal,btnUser;
    AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
    AnimatedVectorDrawable animatedVectorDrawable;

    private CustomDialog loadingDialog;

    private ArrayList<String> documentTypes = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private ArrayList<String> genreTypes = new ArrayList<String>();
    private ArrayAdapter<String> adapterGenre;

    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private int isFirstStepSuccess = 0;
    JSONObject jsonObject;
    String ages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mService = Common.getAPI();
        rootLayout = findViewById(R.id.RootLayout);
        ivPersonalD = findViewById(R.id.ivPersonalData);
        ivUserD = findViewById(R.id.ivUserData);
        rlPersonal = findViewById(R.id.RLPersonalData);
        rlUser = findViewById(R.id.RLUserData);
        btnPersonal = findViewById(R.id.btnFirstStep);
        btnUser = findViewById(R.id.btnSecondStep);
        btnUser.setEnabled(false);
        loadingDialog = new CustomDialog(RegisterActivity.this);

        jsonObject = new JSONObject();

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

               showUserDataDialog();



            }
        });
    }

    private void showUserDataDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog;
        builder.setTitle("REGISTRO - DATOS DE USUARIO");
        builder.setMessage("Favor de completar los campos");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_layout = inflater.inflate(R.layout.registration_form_step_two_loader,null);
        CardView cardViewRegisterSecond = register_layout.findViewById(R.id.cardViewRegisterSecondStep);
        MaterialEditText edtCellPhone = register_layout.findViewById(R.id.metCellPhoneUser);
        MaterialEditText edtUser = register_layout.findViewById(R.id.metUserName);
        MaterialEditText edtPassword = register_layout.findViewById(R.id.metPasswordUser);
        MaterialEditText edtEmail = register_layout.findViewById(R.id.metEmail);

        builder.setView(register_layout);


        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                try {

                    jsonObject.put("numeroCelular", edtCellPhone.getText().toString());
                    jsonObject.put("nomUsuario", edtUser.getText().toString());
                    jsonObject.put("passUsuario", edtPassword.getText().toString());
                    jsonObject.put("email", edtEmail.getText().toString());
                    jsonObject.put("origen", 1);
                    Log.i("jsonObject->",jsonObject.toString());

                    RequestBody myreqbody = null;
                    try {

                        if(isFirstStepSuccess == 10) {

                            dialog.dismiss();

                            loadingDialog.startLoadingDialog("REGISTER");

                            myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                                    (new JSONObject(String.valueOf(jsonObject))).toString());

                            mService.createUser(myreqbody).enqueue(new Callback<ResponseCreateUser>() {
                                @Override
                                public void onResponse(Call<ResponseCreateUser> call, Response<ResponseCreateUser> response) {
                                    loadingDialog.dismissLoadingDialog();
                                    Log.i("response createUser ->>>", response.toString());

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

                                    abrirLogin();

                                }

                                @Override
                                public void onFailure(Call<ResponseCreateUser> call, Throwable t) {
                                    loadingDialog.dismissLoadingDialog();
                                    Log.i("error createUser ->>>", t.toString());

                                }
                            });
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (JSONException e){

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

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        TextWatcher firstStepTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mobileInput = edtCellPhone.getText().toString().trim();
                String userInput = edtUser.getText().toString().trim();
                String passwordInput = edtPassword.getText().toString().trim();
                String emailInput = edtEmail.getText().toString().trim();

                if(!mobileInput.isEmpty() &&
                        !userInput.isEmpty() &&
                        !passwordInput.isEmpty() &&
                        !emailInput.isEmpty()){
                    isFirstStepSuccess = 10;
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtCellPhone.addTextChangedListener(firstStepTextWatcher);
        edtUser.addTextChangedListener(firstStepTextWatcher);
        edtPassword.addTextChangedListener(firstStepTextWatcher);
        edtEmail.addTextChangedListener(firstStepTextWatcher);


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
        MaterialEditText edtBirthDate = register_layout.findViewById(R.id.metBirthDatePersonalData);
        MaterialSpinner spinnerGenreT = register_layout.findViewById(R.id.spinnerGenre);

        builder.setView(register_layout);

        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    try {

                        if(isFirstStepSuccess == 6) {
                            String[] arrSplit = edtNames.getText().toString().split(" ");

                            if(!arrSplit[0].equals("")){
                                jsonObject.put("primerNombre",arrSplit[0]);
                            } else {
                                jsonObject.put("primerNombre",null);
                            }
                            if(!arrSplit[1].equals("")){
                                jsonObject.put("segudoNombre",arrSplit[1]);
                            } else {
                                jsonObject.put("segudoNombre",null);
                            }
                            if(!arrSplit[2].equals("")){
                                jsonObject.put("primerApellido",arrSplit[2]);
                            } else {
                                jsonObject.put("primerApellido",null);
                            }
                            if(!arrSplit[3].equals("")){
                                jsonObject.put("segundoApellido",arrSplit[3]);
                            } else {
                                jsonObject.put("segundoApellido",null);
                            }

                            String[] birthDateSplit = edtBirthDate.getText().toString().split("-");
                            String BirthdateFormatBD = birthDateSplit[2]+"-"+birthDateSplit[1]+"-"+birthDateSplit[0];
                            int day = Integer.parseInt(birthDateSplit[0]);
                            int month = Integer.parseInt(birthDateSplit[1]);
                            int year = Integer.parseInt(birthDateSplit[2]);
                            int age = getAge(year,month);

                            jsonObject.put("key",Common.API_KEY);
                            jsonObject.put("direccion", edtAddress.getText().toString());
                            jsonObject.put("tipoDocumento", spinnerDocumentT.getSelectedItemId());
                            jsonObject.put("numeroDocumento", edtDocument.getText().toString());
                            jsonObject.put("fechaNacimiento",BirthdateFormatBD);
                            jsonObject.put("edad",age) ;
                            jsonObject.put("genero", spinnerGenreT.getSelectedItemId()); // FALTA CAMBIARLO
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



                    } catch (JSONException e) {
                        e.printStackTrace();
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
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        loadDocumentTypes(spinnerDocumentT,dialog,edtNames,edtAddress,edtBirthDate,edtDocument);
        loadGenreTypes(spinnerGenreT,dialog,edtNames,edtAddress,edtBirthDate,edtDocument);

        TextWatcher firstStepTextWatcher = new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String namesInput = edtNames.getText().toString().trim();
                String documentInput = edtDocument.getText().toString().trim();
                String addressInput = edtAddress.getText().toString().trim();
                String birthDateInput = edtBirthDate.getText().toString().trim();

                if (!edtBirthDate.getText().toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s-%s-%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edtBirthDate.setText(current);
                    edtBirthDate.setSelection(sel < current.length() ? sel : current.length());
                }


                if(!namesInput.isEmpty() &&
                        !documentInput.isEmpty() &&
                        !addressInput.isEmpty() &&
                        !birthDateInput.isEmpty()){
                    isFirstStepSuccess = 4;
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtNames.addTextChangedListener(firstStepTextWatcher);
        edtDocument.addTextChangedListener(firstStepTextWatcher);
        edtAddress.addTextChangedListener(firstStepTextWatcher);
        edtBirthDate.addTextChangedListener(firstStepTextWatcher);

    }

    private void loadGenreTypes(MaterialSpinner spinnerGenreT, AlertDialog dialog,
                                MaterialEditText edtNames, MaterialEditText edtAddress,
                                MaterialEditText edtBirthDate, MaterialEditText edtDocument) {

        JSONObject jsonObjectDocument = new JSONObject();
        try {
            jsonObjectDocument.put("key", Common.API_KEY);
            RequestBody myreqbody = null;
            try {
                myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(String.valueOf(jsonObjectDocument))).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("myreqbody",myreqbody.toString());
            mService.obtainGenreTypes(myreqbody).enqueue(new Callback<ResponseGenreType>() {
                @Override
                public void onResponse(Call<ResponseGenreType> call, Response<ResponseGenreType> response) {
                    Log.i("response.body()",response.toString());
                    if (response.isSuccessful()){
                        if (response.body() != null){

                            PopulateSpinnerGenreType(response.body(),spinnerGenreT,dialog,edtNames,edtDocument,edtBirthDate,edtAddress);
                        }

                    }

                }

                @Override
                public void onFailure(Call<ResponseGenreType> call, Throwable t) {
                    Log.e("response.body()",t.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void PopulateSpinnerGenreType(ResponseGenreType body, MaterialSpinner spinnerGenreT, AlertDialog dialog,
                                          MaterialEditText edtNames, MaterialEditText edtDocument,
                                          MaterialEditText edtBirthDate, MaterialEditText edtAddress) {
        if (genreTypes.size() == 0){
            for (int i = 0; i < body.getMessage().size(); i++){
                genreTypes.add(body.getMessage().get(i).getDescripcion().toString());
            }
            adapterGenre = new ArrayAdapter<>(this,R.layout.spinner_genre_items,genreTypes);
            adapterGenre.setDropDownViewResource(R.layout.spinner_genre_items);

        } else {
            System.out.println("documentTypes -> "+genreTypes);
        }

        spinnerGenreT.setAdapter(adapterGenre);
        spinnerGenreT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    String selected = spinnerGenreT.getItemAtPosition(position).toString();
                    String namesInput = edtNames.getText().toString().trim();
                    String documentInput = edtDocument.getText().toString().trim();
                    String addressInput = edtAddress.getText().toString().trim();
                    String birthDateInput = edtBirthDate.getText().toString().trim();
                    Log.i("selected",selected);
                    if(!namesInput.isEmpty() &&
                            !documentInput.isEmpty() &&
                            !addressInput.isEmpty() &&
                            !birthDateInput.isEmpty()){
                        isFirstStepSuccess = 6;
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    } else {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                } else {
                    spinnerGenreT.setError("Debes seleccionar un genero");
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void loadDocumentTypes(MaterialSpinner spinnerDocumentT, AlertDialog dialog,
                                  MaterialEditText edtNames,
                                  MaterialEditText edtAddress,
                                  MaterialEditText edtBirthDate,
                                  MaterialEditText edtDocument) {
        JSONObject jsonObjectDocument = new JSONObject();
        try {
            jsonObjectDocument.put("key", Common.API_KEY);
            RequestBody myreqbody = null;
            try {
                myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        (new JSONObject(String.valueOf(jsonObjectDocument))).toString());
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

                            PopulateSpinnerDocumentType(response.body(),spinnerDocumentT,dialog,edtNames,edtAddress,edtBirthDate,edtDocument);
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
    private void PopulateSpinnerDocumentType(ResponseDocumentType body, MaterialSpinner spinnerDocumentT, AlertDialog dialog,
                                             MaterialEditText edtNames, MaterialEditText edtAddress,
                                             MaterialEditText edtBirthDate, MaterialEditText edtDocument) {
        if (documentTypes.size() == 0){
            for (int i = 0; i < body.getMessage().size(); i++){
                documentTypes.add(body.getMessage().get(i).getDescripcion().toString());
            }
            adapter = new ArrayAdapter<>(this,R.layout.spinner_document_type_items,documentTypes);
            adapter.setDropDownViewResource(R.layout.spinner_document_type_items);

        } else {
            System.out.println("documentTypes -> "+documentTypes);
        }

        spinnerDocumentT.setAdapter(adapter);
        spinnerDocumentT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    String selected = spinnerDocumentT.getItemAtPosition(position).toString();
                    String namesInput = edtNames.getText().toString().trim();
                    String documentInput = edtDocument.getText().toString().trim();
                    String addressInput = edtAddress.getText().toString().trim();
                    String birthDateInput = edtBirthDate.getText().toString().trim();
                    if(!namesInput.isEmpty() &&
                            !documentInput.isEmpty() &&
                            !addressInput.isEmpty() &&
                            !birthDateInput.isEmpty()){
                        isFirstStepSuccess = 6;
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    } else {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }

                } else {
                    spinnerDocumentT.setError("Debes seleccionar un tipo de documento");
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public int getAge (int year, int month) {

        Calendar today = new GregorianCalendar();
        today.setTime(new Date());
        int yearsInBetween = today.get(Calendar.YEAR) - year;
        int monthsDiff = today.get(Calendar.MONTH) - month;
        if (monthsDiff <=0){
            yearsInBetween--;
        }
        return yearsInBetween;
    }

    private void abrirLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(intent);
        finish();
    }
}
