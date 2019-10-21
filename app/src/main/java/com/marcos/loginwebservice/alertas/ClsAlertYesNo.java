package com.marcos.loginwebservice.alertas;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marcos.loginwebservice.R;
import com.marcos.loginwebservice.callback.ICallbackYesNo;

public class ClsAlertYesNo {
    private AlertDialog.Builder builder = null;
    private AlertDialog alert = null;
    private ICallbackYesNo callback;

    public void showMessage(Context myContext, String str) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dialog_yesno, null);

        builder = new AlertDialog.Builder(myContext);
        builder.setView(view);

        alert = builder.create();

        Button btsim = (Button) view.findViewById(R.id.btsim);
        Button btnao = (Button) view.findViewById(R.id.btnao);
        TextView lblDescricao = (TextView) view.findViewById(R.id.lbldescricaomsg);
        lblDescricao.setText(str);

        btsim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();
                callback.messageReceive(true);

            }
        });

        btnao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alert.dismiss();
                callback.messageReceive(false);

            }
        });

        alert.show();
    }

    public void setCallback(ICallbackYesNo i) {
        callback = i;
    }

}
