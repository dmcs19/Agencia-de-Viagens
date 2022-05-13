package tps.tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Aeroporto {

    private String nome;
    private String abreviatura;
    private ArrayList<Voo> historico_voos = new ArrayList<Voo>();
    private ArrayList<Voo> proximos_voos = new ArrayList<Voo>();
    private ArrayList<Voo> partidas = new ArrayList<Voo>();
    private ArrayList<Voo> chegadas = new ArrayList<Voo>();
    
    public Aeroporto(String nome, String abreviatura){
        this.nome = nome;
        this.abreviatura = abreviatura;
    }

    public void novo_voo(Voo voo){
        //adiciona o voo a lista de proximos voos
        proximos_voos.add(voo);
        //adiciona o voo a lista de partidas ou chegada
        if(this.equals(voo.getOrigem())){
            partidas.add(voo);
        }else{
            chegadas.add(voo);
        }
        ordenar_voos();
    }

    public void voo_completo(Voo voo){
        //adicionna o voo ao historico de voos e remove-o da lista: proximos voos; partidas; chegadas
        historico_voos.add(voo);
        for(int i = 0; i< proximos_voos.size(); i++){
            if(proximos_voos.get(i).equals(voo)){
                proximos_voos.remove(i);
            }
        }
        for(int i = 0; i< partidas.size(); i++){
            if(partidas.get(i).equals(voo)){
                partidas.remove(i);
            }
        }
        for(int i = 0; i< chegadas.size(); i++){
            if(chegadas.get(i).equals(voo)){
                chegadas.remove(i);
            }
        }
        ordenar_voos();
    }

    public void voo_cancelado(Voo voo){
        //remove o voo que esta na lista dos proximos voos e que foi cancelado
        for(int i = 0; i< proximos_voos.size(); i++){
            if(proximos_voos.get(i).equals(voo)){
                proximos_voos.remove(i);
            }
        }
        ordenar_voos();
    }

    public String getProximosVoos(){
        //mostra a lista dos proximos voos do aeroporto
        String voos;
        if(proximos_voos.size() == 0){
            voos = "Este aeroporto não tem voos marcados.\n";
        }else{
            voos = "Próximos voos:\n";
            for (int i = 0; i < proximos_voos.size(); i++){
                voos += proximos_voos.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    public String getHistoricoVoos(){
        //mostra a lista do historico de voos do aeroporto
        String voos;
        if(historico_voos.size() == 0){
            voos = "Este aeroporto ainda não teve nenhum voo.\n";
        }else{
            voos = "Voos realizados:\n";
            for (int i = 0; i < historico_voos.size(); i++){
                voos += historico_voos.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    public String getProximasPartidas(){
        //mostra a lista das proximas partidas do aeroporto
        String voos;
        if(partidas.size() == 0){
            voos = "Este aeroporto não tem partidas previstas.\n";
        }else{
            voos = "Próximas partidas:\n";
            for (int i = 0; i < partidas.size(); i++){
                voos += partidas.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    public String getProximasChegadas(){
        //mostra a lista das proximas chegadas do aeroporto
        String voos;
        if(chegadas.size() == 0){
            voos = "Este aeroporto não tem chegadas previstas.\n";
        }else{
            voos = "Próximas chegadas:\n";
            for (int i = 0; i < chegadas.size(); i++){
                voos += chegadas.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    private void ordenar_voos(){
        //ordena a lista dos proximos voos por data e caso sejam no mesmo dia ordena por hora
        Collections.sort(proximos_voos, Comparator.comparing(Voo::getPartida));
        Collections.sort(proximos_voos, Comparator.comparing(Voo::getDate));

        //ordena a lista das proximas partidas por data e caso sejam no mesmo dia ordena por hora
        Collections.sort(partidas, Comparator.comparing(Voo::getPartida));
        Collections.sort(partidas, Comparator.comparing(Voo::getDate));

        //ordena a lista das proximas chegadas por data e caso sejam no mesmo dia ordena por hora
        Collections.sort(chegadas, Comparator.comparing(Voo::getPartida));
        Collections.sort(chegadas, Comparator.comparing(Voo::getDate));

        //ordena a lista do historico voos por data e caso sejam no mesmo dia ordena por hora
        Collections.sort(historico_voos, Comparator.comparing(Voo::getPartida));
        Collections.sort(historico_voos, Comparator.comparing(Voo::getDate));
        Collections.reverse(historico_voos);
    }

    public boolean equals(Aeroporto aeroporto){
        //verifica se dois aeroportos sao iguais
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
