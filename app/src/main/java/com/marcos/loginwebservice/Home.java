package com.marcos.loginwebservice;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    //teste para pegar usuario logado
    private static String user_log;


    TextView usuarioLogado;


//    public Home(String usuarioLogadoApp) {
//        usuarioLogado.setText(usuarioLogadoApp);//ainda em teste 07/10/2019 elias
//    }


    //fim teste 04/10/2019


    //teste logout 09/10/2019
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//chama layout principal

        //tirar barra superior
        getSupportActionBar().hide();

        recuperarComponentes();

        CriaEventos();

    }

    //region recuperar Componentes
    private void recuperarComponentes() {
        usuarioLogado = (TextView) findViewById(R.id.txtLogadoAPP);

        //setUser_log();
        //teste logout
        btnClose = (ImageButton) findViewById(R.id.btnClose);
        //fim teste


    }
    //endregion


    //region Eventos
    private void CriaEventos() {

        //region teste logout 09/10/2019
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutApp();
            }
        });
        //endregion
    }
    //endregion


    //metodos teste para pegar usuario logado no app

    public static String getUser_log() {
        return user_log;
    }

    public static void setUser_log() {
        Home.user_log = user_log;
    }


    //region logout no app
    private void logoutApp() {
        alertaLogout();
    }

    private void alertaLogout() {

        //cria o gerenciador do alerta
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        //defini titulo para o alerta
        alerta.setTitle("Aviso");

        //defini icone para o alerta  //defini icone para o alert em caso de utiliza padrão e não customizado
        alerta.setIcon(R.drawable.close_app);//se caso não quiser não sou obrigado a colocar icone no alerta 09/10/2019

        //definir a mensagem exibida no meu alerta
        alerta.setMessage("Deseja realmente realizar lout do aplicativo ?");

        //defini botao com positivo para alerta
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               //alterando valor da minha preferences paar falso pois usuario  realizou logout
                SharedPreferences SM = getSharedPreferences("USUARIO_LOGADO", 0);
                SharedPreferences.Editor edit = SM.edit();
                edit.putBoolean("usuarioLogado", false);
                edit.commit();


                //redireciona para fora do app
                Intent intentLogout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentLogout);

                //finaliza atividade atual
                finish();
            }
        });

        //defini o botão com opção de negativo
        alerta.setNegativeButton("NÂO", null);

        //exibe alerta
        alerta.show();
    }
    //endregion

}
