package tps.tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Companhia {
    
    private String nome;
    private ArrayList<Voo> voos = new ArrayList<Voo>();
    private ArrayList<Aviao> avioes = new ArrayList<Aviao>();

    public Companhia(String nome){
        this.nome = nome;
    }

    //adiciona o voo a lista de voos
    public void novo_voo(Voo voo){
        voos.add(voo);
    }

    //adiciona um aviao a companhia
    public void adicionar_aviao(Aviao aviao){
        avioes.add(aviao);
    }

    //remove um aviao da companhia
    public void remover_aviao(Aviao aviao){
        for(int i = 0; i < avioes.size(); i++){
            if(avioes.get(i).equals(aviao)){
                avioes.remove(i);
                break;
            }
        }
    }

    //mostra na consola a lista dos proximos voos da companhia
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
            return "Esta companhia não tem voos marcados.";
        }
    }

    //mostra na consola a lista do historico de voos da companhia
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
            return "Esta companhia não realizou nenhum voo.";
        }
    }

    //mostra na consola a lista de voos em atraso da companhia
    public String getVoosAtrasados(){
        ordenar_voos();
        String vooString = "";
        boolean tem_voos = false;
        if(voos.size() > 0){
            vooString = "Proximos voos em atraso:\n";
            //percorre todos os voos e adiciona a string aqueles que tem o estado "Atrasado"
            for(int i = 0; i < voos.size(); i++){
                if(voos.get(i).getEstado().equals("Atrasado")){
                    vooString += voos.get(i).toString() + "\n";
                    tem_voos = true;
                }
            }
        }
        //verifica se houve algum voo adicionado a string
        if(tem_voos == true){
            return vooString;
        }else{
            return "Esta companhia não tem voos em atraso.";
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

    //devolve os avioes que a companhia tem
    public String getAvioes() {
        String aviao;
        if(avioes.size() == 0){
            aviao = "Esta companhia não tem nenhum avião.\n";
        }else{
            aviao = "Avioes:\n";
            for(int i = 0; i < avioes.size(); i++){
                aviao += avioes.get(i).toString();
            }
        }
        return aviao;
    }

    //retona o nome da companhia
    public String toString(){
        return nome;
    }
}
