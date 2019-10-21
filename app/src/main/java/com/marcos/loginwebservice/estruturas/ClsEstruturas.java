package com.marcos.loginwebservice.estruturas;

public class ClsEstruturas {
//unica classe de estruturas do joão que não utiliza GETs e SETs   03/10/2019

    public static class login {//joão nunca utilizou essa parte da estrutura para nada !!! 03/10/2019
        public String user;
        public String password;
    }


    public static class Usuario {

        public static String usuario_nome;
        public static long usuario_cpf;
        public static int usuario_codigo;
        public static String usuario_crea;
        public static Empresa empresa;//joão não utiliza esse campo
    }

    public static class Empresa {

        public static String empresa_nome;
        public static String empresa_cnpj;
        public static String empresa_codigo;
        public static String empresa_endereco;
        public static String empresa_uf;
    }


    public static class UsuarioLogado {//principal utilizado para validação

        public static Usuario usuario;
        public static Empresa empresa;
    }

    public static class Servico {

        public static int servicoSelecionado;
    }
}
