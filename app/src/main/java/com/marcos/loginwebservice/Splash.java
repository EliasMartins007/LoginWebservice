package com.marcos.loginwebservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;


public class Splash extends AppCompatActivity {


    //definindo variavel de tempo de execução da minha splash no futuro será alterado tempo pelo webservice da minha hospedagem para atualizar informçãoes 09/10/2019 elias
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tirar barra superio
        //      getSupportActionBar().hide();  // não posso chamar esse metodo aki pois esta em full screen a tela !!! 09/10/2019 elias

        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//tornar teela full 09/10/2019

        mostrarSplash();
    }


    //region mostrar splash
    private void mostrarSplash() {
        Toast.makeText(this, "ATUALIZANDO AGENDAMENTOS !", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iniciarAPP();
            }
        }, SPLASH_TIME_OUT);
    }

    //endregion

    //region iniciar APP
    private void iniciarAPP() {
        Intent home = new Intent(Splash.this, MainActivity.class);
        startActivity(home);
        finish();
    }
    //endregion

}
