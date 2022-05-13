package tps.tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Piloto {
    
    private String nome;
    private int tempo_voo = 0;
    private ArrayList<Voo> historico_voos = new ArrayList<Voo>();
    private ArrayList<Voo> proximos_voos = new ArrayList<Voo>();

    public Piloto(String nome){
        this.nome = nome;
    }

    public void novo_voo(Voo voo){
        //adiciona o voo a lista de proximos voos
        proximos_voos.add(voo);
        ordenar_voos();
    }

    public void voo_completo(Voo voo){
        //adicionna o voo ao historico de voos e remove-o da lista dos proximos voos
        historico_voos.add(voo);
        for(int i = 0; i< proximos_voos.size(); i++){
            if(proximos_voos.get(i).equals(voo)){
                proximos_voos.remove(i);
            }
        }
        //adiciona a duracao do voo ao tempo de voo do piloto em horas
        tempo_voo += voo.getDuracao();
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
        //mostra a lista dos proximos voos do piloto
        String voos;
        if(proximos_voos.size() == 0){
            voos = "Este piloto não tem voos marcados.";
        }else{
            voos = "Próximos voos:\n";
            for (int i = 0; i < proximos_voos.size(); i++){
                voos += proximos_voos.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    public String getHistoricoVoos(){
        //mostra a lista do historico de voos do piloto
        String voos;
        if(historico_voos.size() == 0){
            voos = "Este piloto ainda não realizou nenhum voo.";
        }else{
            voos = "Voos realizados:\n";
            for (int i = 0; i < historico_voos.size(); i++){
                voos += historico_voos.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    private void ordenar_voos(){
        //ordena a lista dos proximos voos por data e caso sejam no mesmo dia ordena por hora
        Collections.sort(proximos_voos, Comparator.comparing(Voo::getPartida));
        Collections.sort(proximos_voos, Comparator.comparing(Voo::getDate));

        //ordena a lista do historico voos por data e caso sejam no mesmo dia ordena por hora
        Collections.sort(historico_voos, Comparator.comparing(Voo::getPartida));
        Collections.sort(historico_voos, Comparator.comparing(Voo::getDate));
        Collections.reverse(historico_voos);
    }

    public int getTempoVoo(){
        //retorna o tempo de voo do piloto
        return tempo_voo;
    }

    public String toString(){
        return "Piloto:\nNome: " + nome + " | Tempo_de_voo: " + tempo_voo + "h\n";
    }
}
