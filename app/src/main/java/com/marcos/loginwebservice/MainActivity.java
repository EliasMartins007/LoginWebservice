package com.marcos.loginwebservice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.marcos.loginwebservice.alertas.ClsAlertErro;
import com.marcos.loginwebservice.asynctasks.ClsAsyncValidarLogin;
import com.marcos.loginwebservice.callback.ICallBackGeneral;
import com.marcos.loginwebservice.global.ClsInfoDevice;

public class MainActivity extends AppCompatActivity {

    private Button btLogar;
    private TextView lblLinkAcesso;//não existe no meu escopo 03/10/2019
    private EditText txtLogin;
    private EditText txtSenha;

    SharedPreferences appPref;
    boolean isFirstTime;
    final String PREFS_NAME = "MyPrefsFile";
    private ClsAlertErro alert; // classe de erro
    private ClsAsyncValidarLogin aLogin;
    private ClsInfoDevice iDevice;  // não criei essa classe 03/10/2019  acrescentei 04/10/2019 elias
    private Context myContext;
    //private FileINI fileINI; //não criei essa classe 03/10/2019


    //preferences login
    SharedPreferences SM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();//teste elias 09/10/2019


        setContentView(R.layout.loginpage);//(R.layout.activity_main)


        myContext = this;//teste 04/10/2019


        //teste 04/10/2019
        verificarFirstTime();//adicionei atarde para teste
        //fim teste
        recuperarComponentes();//seria o iniciar componentes do joão 03/10/2019

        eventos();

        //preferences login
        SM = getSharedPreferences("USUARIO_LOGADO", 0);//cria preferences,   diferent de outros metodos utilizo o get para criar ao invez de set para colocar valor 09/10/2019
        Boolean isLogin = SM.getBoolean("usuarioLogado", false);//por default recebe falso
        if (isLogin) {
            Intent intent = new Intent(MainActivity.this, Home.class);

            //inicia intenção
            startActivity(intent);
            //finalilza atividade anterior
            finish();

            return;
        }


    }

    private void eventos() {
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validardados();
            }
        });


    }


    //recuperaer componentes
    private void recuperarComponentes() {
        alert = new ClsAlertErro();

        //teste 04/10/2019 para idevice
        iDevice = new ClsInfoDevice();//estava vindo nulo pois não tinha instanciado a classe 04/10/2019


        btLogar = (Button) findViewById(R.id.btLogar);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);


    }


    private void validardados() {
        try {
            if (txtLogin.getText().toString().equals("")) {
                throw new Exception("Favor informar o usuario !");
            }
            if (txtSenha.getText().toString().equals("")) {
                throw new Exception("Favor informar a senha !");
            }
            //asyncValidarLogin
            //original
            aLogin = new ClsAsyncValidarLogin(this, txtLogin.getText().toString(), txtSenha.getText().toString(),
                    iDevice.getID(this));//, iDevice.getId(this) // 04/10/2019 elias alterei contrutor para 2 parametros


            //teste elias
//            aLogin = new ClsAsyncValidarLogin(this, txtLogin.getText().toString(), txtSenha.getText().toString());  //meu contrutor sem device 04/10/2019
//não foi possivel mudar a autenticação pois seria no serviço   webservice    questão do device


            aLogin.setCallBack(new ICallBackGeneral() {

                @Override
                public void messageReceive(String str, int tipo) {
                    if (tipo != 0) {
                        showErro(str);
                    } else {
                        usuarioLogado();
                    }
                }
            });
            aLogin.execute();


        } catch (Exception ex) {
            //   Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            alert.message(myContext, ex.getMessage().toString()); // estava fechando app  devido a não ter inicializazo meu myContext com this  resolvido 04/10/209
        }
    }


    protected void showErro(String err) {
        alert.message(this, err);

    }

    private void usuarioLogado() {

        //teste preferencias logado 09/10/2019
        SharedPreferences.Editor edit = SM.edit();
        edit.putBoolean("usuarioLogado", true);//usuario realizou login no app 09/10/2019
        edit.commit();

        //fim teste


        Intent home = new Intent(MainActivity.this, Home.class);
        startActivity(home);
        //teste pegar usuario logado 04/10/2019

        //fim  teste
        super.finish();//destroy tela anterior apos login 04/10/2019 elias
    }


    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void verificarFirstTime() {
        appPref = getSharedPreferences(PREFS_NAME, 0);

        isFirstTime = appPref.getBoolean("my_first_time", true);
        if (isFirstTime) {
            //  createShortCut();
            appPref.edit().putBoolean("my_first_time", false).commit();
        }

    }


}
