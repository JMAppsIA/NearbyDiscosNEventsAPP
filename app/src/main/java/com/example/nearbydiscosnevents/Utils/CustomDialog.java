package com.example.nearbydiscosnevents.Utils;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nearbydiscosnevents.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;

public class CustomDialog {

    Activity activity;
    AlertDialog dialog;
    TextView tviewLoader;
    MKLoader loader;

    public CustomDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void startLoadingDialog(String origin, String message){
        AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View viewInflater = inflater.inflate(R.layout.custom_loader, null);
        loader = viewInflater.findViewById(R.id.mkLoader);
        tviewLoader = viewInflater.findViewById(R.id.tvLoader);
        switch (origin){
            case "LOGIN": {
                tviewLoader.setText("Validando datos");
                break;
            }
            case "REGISTER": {
                tviewLoader.setText("Registrando usuario");
                break;
            }
            default: {
                tviewLoader.setText("Cargando");
            }

        }

        if(!message.equals("")){
            loader.setVisibility(View.GONE);
            tviewLoader.setText(message);
        }


        builder.setView(viewInflater);
        builder.setCancelable(false);


        dialog = builder.create();
        dialog.show();
    }

    public void dismissLoadingDialog() {
        dialog.dismiss();
    }
}
