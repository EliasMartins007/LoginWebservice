package com.marcos.loginwebservice.asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.marcos.loginwebservice.callback.ICallBackGeneral;
import com.marcos.loginwebservice.callback.ICallbackWebService;
import com.marcos.loginwebservice.webservices.ClsWebservices;

public class ClsAsyncValidarLogin extends AsyncTask<Void, Void, Void> {

    private ProgressDialog dialog = null;
    private Context myContext = null;
    private ClsWebservices ws = null;
    private boolean sucess = true;
    private String messageError = "";

    private String deviceID = "";
    private String login = "";
    private String password = "";

    private ICallBackGeneral callBack = null;  // seria o calback general 03/10/2019   ????

    public ClsAsyncValidarLogin(Context c, String user, String senha, String id) {//, String id voltei com id 04/10/2019
        myContext = c;
        deviceID = id;// descomentei 04/10/2019
        login = user;
        password = senha;

    }


    //teste construtor 2 elias   ainda em teste  não foi possivel mudar a autenticação pois seria no serviço   webservice    questão do device
    public ClsAsyncValidarLogin(Context c, String user, String senha) {
    myContext = c;
    login = user;
    password = senha;

    }


    //fim

    @Override
    protected Void doInBackground(Void... params) {
        ws = new ClsWebservices();

        ws.setCallBack(new ICallbackWebService() {
            @Override
            public void messageReceive(String str) {
                sucess = false;
                messageError = str;
            }
        });

        if (!ws.validarLogin(login, password, deviceID))//esta parando aki com url do marcos 04/10/2019
            sucess = false;

        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(myContext, "Facial Clinica",
                "VALIDANDO INFORMAÇÕES NO WEBSERVICE \n POR FAVOR AGUARDE ... \n", true);
    }

    @Override
    protected void onPostExecute(Void result) {
        dialog.dismiss();
        if (!sucess && messageError.equals("")) {
            callBack.messageReceive(
                    "Usuario ou senha invalidos", 1);
        } else if (!sucess && !messageError.equals("")) {
            callBack.messageReceive(
                    "Ocorreu um erro ao tentar se conectar no servidor para validar Login !!! \n", 1);//esta dando erro aki 04/10/2019 elias com url da locaweb
        } else {
            callBack.messageReceive("", 0);
        }

    }

    public void setCallBack(ICallBackGeneral i) {
        callBack = i;
    }
}
