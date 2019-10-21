package com.marcos.loginwebservice.global;

import android.content.Context;
import android.os.Build;


public class ClsInfoDevice {
    //construtor vazio 04/10/2019
    public ClsInfoDevice() {
    }

    public String getID(Context c) {
        return new ClsDeviceID().getID(c);
    }

    public String getFabricante() {
        return Build.MANUFACTURER.toUpperCase();
    }

    public String getSistema() {
        return "ANDROID" + Build.VERSION.RELEASE.toUpperCase();
    }

    public String getModelo() {
        return Build.MODEL.toUpperCase();
    }

}
