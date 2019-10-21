package com.marcos.loginwebservice.alertas;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marcos.loginwebservice.R;

public class ClsAlertErro {
    private AlertDialog.Builder builder = null;
    private AlertDialog alert = null;

    public void message(Context con, String s) {

        LayoutInflater inflater = (LayoutInflater) con
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.error_layout, null);

        builder = new AlertDialog.Builder(con);
        builder.setView(view);

        alert = builder.create();

        Button btOkError = (Button) view.findViewById(R.id.btOkError);
        TextView lblDescricaoError = (TextView) view
                .findViewById(R.id.lblErrorDescricao);
        lblDescricaoError.setText(s);

        btOkError.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();

            }
        });

        alert.show();
    }
    public void messageSucess(Context con, String s) {//esse metodo não é utilizado no login 04/10/2019

        LayoutInflater inflater = (LayoutInflater) con
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.sucess_layout, null);

        builder = new AlertDialog.Builder(con);
        builder.setView(view);

        alert = builder.create();

        Button btOkError = (Button) view.findViewById(R.id.btOkSucesso);
        TextView lblDescricaoError = (TextView) view
                .findViewById(R.id.txtDescricaoSucesso);
        lblDescricaoError.setText(s);

        btOkError.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();

            }
        });

        alert.show();
    }
}
