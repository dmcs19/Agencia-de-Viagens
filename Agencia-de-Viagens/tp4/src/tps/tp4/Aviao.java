package tps.tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Aviao {

    private String modelo;
    private int capacidade;
    private ArrayList<Voo> voos = new ArrayList<Voo>();

    public Aviao(String modelo, int capacidade, Companhia companhia){
        this.modelo = modelo;
        this.capacidade = capacidade;
        companhia.adicionar_aviao(this);
    }

    //adiciona o voo a lista de proximos voos
    public void novo_voo(Voo voo){
        voos.add(voo);
    }

    //mostra na consola a lista dos proximos voos do aviao
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
            return "Este avião não tem voos marcados.";
        }
    }

    //mostra na consola a lista do historico de voos do aviao
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
            return "Este avião não realizou nenhum voo.";
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

    //verifica se dois avioes são iguais
    public boolean equals(Aviao aviao){
        if(this.modelo == aviao.modelo){
            return true;
        }else{
            return false;
        }
    }

    //retorna as informacoes do aviao
    public String toString(){
        return "Modelo: " + modelo + " | Capacidade: " + capacidade + "\n";
    }
}
