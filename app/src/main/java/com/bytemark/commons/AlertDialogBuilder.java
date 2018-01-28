package com.bytemark.commons;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.bytemark.R;


public class AlertDialogBuilder {

    public static AlertDialog getDialog(Context context,String message, DialogInterface.OnClickListener listener)
    {
       return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.ok), listener)
                .setNegativeButton(context.getString(R.string.cancel), listener)
                .create();

    }

}
