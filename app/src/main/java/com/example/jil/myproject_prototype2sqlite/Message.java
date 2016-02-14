package com.example.jil.myproject_prototype2sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by JIL on 01/02/16.
 */
public class Message {
    public static void messageToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void messageSnackBar(View view, String message, View.OnClickListener onClickListener)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", onClickListener).show();
    }

}


