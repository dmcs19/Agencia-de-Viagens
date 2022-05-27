package tps.tp4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Aeroporto {

    private String nome;
    private String abreviatura;
    private ArrayList<Voo> voos = new ArrayList<Voo>();
    
    public Aeroporto(String nome, String abreviatura){
        this.nome = nome;
        this.abreviatura = abreviatura;
    }

    //adiciona vo voo a lista de voos
    public void novo_voo(Voo voo){
        voos.add(voo);
    }

    //mostra na consola a lista dos proximos voos do aeroporto
    public String getProximosVoos(){
        ordenar_voos();
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Proximos voos:\n";
            //percorre todos os voos e adiciona a string aqueles que tem o estado "A tempo" ou "Atrasado"
            for(int i = 0; i < voos.size(); i++){
                if(voos.get(i).getEstado().equals("A tempo") || voos.get(i).getEstado().equals("Atrasado")){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Este aeroporto não tem voos marcados.";
        }
    }

    //mostra na consola a lista do historico de voos do aeroporto
    public String getHistoricoVoos(){
        ordenar_voos();
        reverse_voos();
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Histórico de voos:\n";
            //percorre todos os voos e adiciona a string aqueles que tem o estado "Completo"
            for(int i = 0; i < voos.size(); i++){
                if(voos.get(i).getEstado().equals("Completo")){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Este aeroporto não realizou nenhum voo.";
        }
    }

    //mostra na consola a lista das proximas partidas do aeroporto
    public String getProximasPartidas(){
        ordenar_voos();
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Próximas partidas:\n";
            //percorre todos os voos e adiciona a string aqueles que ainda nao sairam e que saem do aeroporto no qual é chamada a funçao
            for(int i = 0; i < voos.size(); i++){
                if((voos.get(i).getEstado().equals("A tempo") || voos.get(i).getEstado().equals("Atrasado")) && (voos.get(i).getOrigem().equals(this))){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Este aeroporto não tem nenhuma partida agendada.";
        }
    }

    //mostra na consola a lista das proximas cheagadas ao aeroporto
    public String getProximasChegadas(){
        ordenar_voos();
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Próximas chegadas:\n";
            //percorre todos os voos e adiciona a string aqueles que ainda nao sairam e que nao saem do aeroporto no qual é chamada a funçao
            for(int i = 0; i < voos.size(); i++){
                if((voos.get(i).getEstado().equals("A tempo") || voos.get(i).getEstado().equals("Atrasado")) && !(voos.get(i).getOrigem().equals(this))){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Este aeroporto não tem nenhuma chegada agendada.";
        }
    }

    //mostra na consola a lista das partidas do aeroporto num determinado dia
    public String getPartidasData(String date) throws ParseException{
        ordenar_voos();
        //tranforma a string num formato Date
        SimpleDateFormat dia = new SimpleDateFormat("dd-MM-yyyy");
        Date data = dia.parse(date);
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Partidas no dia " + date + ":\n";
            //percorre todos os voos e adiciona a string aqueles que ainda nao sairam e que saem do aeroporto no qual é chamada a funçao e que tem a data pedida
            for(int i = 0; i < voos.size(); i++){
                if((voos.get(i).getEstado().equals("A tempo") || voos.get(i).getEstado().equals("Atrasado")) && (voos.get(i).getOrigem().equals(this)) && (data.equals(voos.get(i).getDate()))){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Este aeroporto não tem nenhuma partida agendada para o dia " + date;
        }
    }

    //mostra na consola a lista das chegadas do aeroporto num determinado dia
    public String getChegadasData(String date) throws ParseException{
        ordenar_voos();
        //tranforma a string num formato Date
        SimpleDateFormat dia = new SimpleDateFormat("dd-MM-yyyy");
        Date data = dia.parse(date);
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Chegadas no dia " + date + ":\n";
            //percorre todos os voos e adiciona a string aqueles que ainda nao sairam e que chegam do aeroporto no qual é chamada a funçao e que tem a data pedida
            for(int i = 0; i < voos.size(); i++){
                if((voos.get(i).getEstado().equals("A tempo") || voos.get(i).getEstado().equals("Atrasado")) && !(voos.get(i).getOrigem().equals(this)) && (data.equals(voos.get(i).getDate()))){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Este aeroporto não tem nenhuma chegada agendada para o dia " + date;
        }
    }

    //ordena a lista dos proximos voos por data e caso sejam no mesmo dia ordena por hora
    private void ordenar_voos(){
        Collections.sort(voos, Comparator.comparing(Voo::getPartida));
        Collections.sort(voos, Comparator.comparing(Voo::getDate));
    }

    //troca a ordem dos voos
    private void reverse_voos(){
        Collections.reverse(voos);
    }

    //verifica se dois aeroportos sao iguais
    public boolean equals(Aeroporto aeroporto){
        if(nome.equals(aeroporto.nome)){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return abreviatura;
    }
}
