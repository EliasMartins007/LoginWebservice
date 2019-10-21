package com.marcos.loginwebservice.webservices;

import android.content.Context;
import android.os.StrictMode;

import com.marcos.loginwebservice.Home;
import com.marcos.loginwebservice.MainActivity;
import com.marcos.loginwebservice.callback.ICallbackWebService;
import com.marcos.loginwebservice.estruturas.ClsEstruturas;//para estrutura usuario 03/10/2019

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketTimeoutException;

public class ClsWebservices {

    private SoapObject soap;

    public final String SOAP_ACTION = "http://tempuri.org/";
    public final String OPERTION_NAME = "";//não utilizado antes ? 03/10/2019
    public final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public String SOAP_ADDRESS = "";
    private final int TIMEOUT = 600000;
    private Context myContext;

    private ICallbackWebService callback;//callback no ecv joão

    public ClsWebservices() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //endereço do webservice 03/10/2019
        SOAP_ADDRESS = "http://201.76.53.19/thoth/pro/ecv/wsthoth/wsmobile.asmx";// locaweb 03/10/20019 elias
        //     SOAP_ADDRESS = "http://mrsilvatads.com.br/teste/wsMobile.asmx"; // endereço marcos clinica teste 04/12019
    }


    //validar LoginUser 03/10/2019
    @SuppressWarnings("statitc-acess")//olhar porque de colocar isso aki 03/10/2019
    public boolean validarLogin(String login, String pwd, String idDevice) {

        boolean resul = true;


        try {
            soap = new SoapObject(WSDL_TARGET_NAMESPACE, "MOBILE_loginUGCPRAXIS");

            soap.addProperty("login", login);
            soap.addProperty("senha", pwd);
            soap.addProperty("identify", idDevice);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;//somente se webservice for e, DOTNET 03/10/2019
            envelope.setOutputSoapObject(soap);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, TIMEOUT);
            SoapObject response = null;
            httpTransport.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");//somente se for XML 03/10/2019

            httpTransport.call(SOAP_ACTION + "MOBILE_loginUGCPRAXIS", envelope);
            if (envelope.getResponse() != null) {
                response = (SoapObject) envelope.getResponse();

                SoapObject dataSet = (SoapObject) response.getProperty(1);
                if (dataSet.getPropertyCount() > 0) {
                    SoapObject table = (SoapObject) dataSet.getProperty(0);
                    SoapObject row = (SoapObject) table.getProperty(0);

                    ClsEstruturas.UsuarioLogado.usuario.usuario_nome = row.getProperty("usuario_nome").toString();//inicialmente não mostra dentro da estrutura ! 03/10/2019 elias
                    // ClsEstruturas.UsuarioLogado.usuario.usuario_cpf = Long.parseLong(row.getProperty("usurario_cpf").toString());//da erro nessa linha
                    ClsEstruturas.UsuarioLogado.usuario.usuario_crea = row.getProperty("usuario_crea").toString();
                    //não coloquei os demais campos
                    //codigo , empresa

                    //teste usuario logado elias 04/10/2019  tarde
                    Home.setUser_log();

                    //fim teste

                    resul = true;
                } else {
                    throw new Exception("Ocorreu um problema ao verificar informações de usuario no webservice !\n");
                }

            } else {
                throw new Exception("Ocorreu um problema ao verificar informações de usuario no webservice !\n");
            }
        } catch (SocketTimeoutException s) {
            if (s.getMessage() != null) {
                callback.messageReceive(s.getMessage().toString());
            } else {
            }
            resul = false;

        } catch (Exception e) {
            callback.messageReceive(e.getMessage().toString());
            resul = false;
        }
        return resul;

    }

    //teste joão 03/10/2019
    public void setCallBack(ICallbackWebService i) {
        callback = i;
    }
}
