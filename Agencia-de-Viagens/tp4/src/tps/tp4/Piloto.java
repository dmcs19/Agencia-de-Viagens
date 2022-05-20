package tps.tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Piloto {
    
    private String nome;
    private int tempo_voo = 0;
    private ArrayList<Voo> voos = new ArrayList<Voo>();

    public Piloto(String nome){
        this.nome = nome;
    }

    //adiciona o voo a lista de proximos voos
    public void novo_voo(Voo voo){
        voos.add(voo);
    }

    //mostra na consola a lista dos proximos voos do piloto
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
            return "Este piloto não tem voos marcados.";
        }
    }

    //mostra na consola a lista do historico de voos do piloto
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
            return "Este piloto não realizou nenhum voo.";
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

    //retorna o tempo de voo do piloto
    public int getTempoVoo(){
        return tempo_voo;
    }

    //retorna as informacoes do piloto
    public String toString(){
        return "Piloto:\nNome: " + nome + " | Tempo_de_voo: " + tempo_voo + "h\n";
    }
}
