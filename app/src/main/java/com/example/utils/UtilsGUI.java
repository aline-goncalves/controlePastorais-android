package com.example.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import com.example.controlepastorais.R;

public class UtilsGUI {
    public static void errorWarning(Context context, int idText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.warning);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(idText);

        builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void confimAction(Context context, String message, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.confirmation);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, listener);
        builder.setNegativeButton(R.string.no, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String validateTextField(Context context, EditText editText, int idErrorMessage){
        String text = editText.getText().toString();

        if (UtilsString.isEmptyString(text)){
            UtilsGUI.errorWarning(context, idErrorMessage);
            editText.setText(null);
            editText.requestFocus();
            return null;

        }else{
            return text.trim();
        }
    }
}
