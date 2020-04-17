package com.example.nearbydiscosnevents.Utils;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.DialogInterface;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.RegisterActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class CustomDialog {

    Activity activity;
    AlertDialog dialog;
    TextView tviewLoader;
    MKLoader loader;

    public CustomDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void startLoadingDialog(String origin){
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




        builder.setView(viewInflater);
        builder.setCancelable(false);


        dialog = builder.create();
        dialog.show();
    }

    public void dismissLoadingDialog() {
        dialog.dismiss();
    }
}
