package tps.tp4;

import java.util.ArrayList;

public class SignIn {
    
    private String nome_utilizador;
    private ArrayList<String> nomes_usados = new ArrayList<String>();
    private ArrayList<String> emails_usados = new ArrayList<String>();
    private ArrayList<String> passwords_usadas = new ArrayList<String>();
    private String email;
    private String password;
    private String funcao;

    public SignIn(String nome_utilizador, String email, String password){
        if(nome_usado())
            throw new IllegalStateException("Este nome de utilizador já está a ser utilizado.");
        this.nome_utilizador = nome_utilizador;
        nomes_usados.add(nome_utilizador);

        if(email_usado())
            throw new IllegalStateException("Este email já está a ser usado.");
        this.email = email;
        emails_usados.add(email);

        this.password = password;
        passwords_usadas.add(password);

        verificar_funcao();
    }

    private void verificar_funcao(){
        //obter o que vem depois do @ para verificar a funcao do utilizador da conta
        String[] divisao = email.split("@");
        String[] prefixo = divisao[1].split(".");
        //atribuir uma determinada funcao ao utilizador dependendo do email
        if(prefixo[0].equals("administrador")){
            this.funcao = "Administrador";
        }else if(prefixo[0].equals("piloto")){
            this.funcao = "Piloto";
        }else{
            this.funcao = "Cliente";
        }
    }

    //verifica se o nome de utilizador ja foi utilizado
    public boolean nome_usado(){
        for(int i = 0; i < nomes_usados.size(); i++){
            if(nomes_usados.get(i).equals(nome_utilizador)){
                return true;
            }
        }
        return false;
    }

    //verifica se um email ja foi usado
    public boolean email_usado(){
        for(int i = 0; i < emails_usados.size(); i++){
            if(emails_usados.get(i).equals(email)){
                return true;
            }
        }
        return false;
    }

    //verifica se o utilizador existe e se a password esta correta
    public boolean verificar_utilizador(String utilizador_email, String password){
        for(int i = 0; i < emails_usados.size(); i++){
            //verifica se o utilizador existe
            if(emails_usados.get(i).equals(utilizador_email) || nomes_usados.get(i).equals(utilizador_email)){
                //verifica se a password esta certa
                if(passwords_usadas.get(i).equals(password)){
                    return true;
                }
            }
        }
        return false;
    }

    //retorna a funcao da conta
    public String getFuncao(){
        return funcao;
    }
}
