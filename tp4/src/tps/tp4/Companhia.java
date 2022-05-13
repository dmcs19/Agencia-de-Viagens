package tps.tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Companhia {
    
    private String nome;
    private ArrayList<Voo> historico_voos = new ArrayList<Voo>();
    private ArrayList<Voo> proximos_voos = new ArrayList<Voo>();
    private ArrayList<Aviao> avioes = new ArrayList<Aviao>();

    public Companhia(String nome){
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

    public void adicionar_aviao(Aviao aviao){
        //adiciona um aviao a companhia
        avioes.add(aviao);
    }

    public void remover_aviao(Aviao aviao){
        //remove um aviao da companhia
        for(int i = 0; i < avioes.size(); i++){
            if(avioes.get(i).equals(aviao)){
                avioes.remove(i);
                break;
            }
        }
    }

    public String getProximosVoos(){
        //mostra a lista dos proximos voos da companhia
        String voos;
        if(proximos_voos.size() == 0){
            voos = "Esta companhia não tem voos marcados.\n";
        }else{
            voos = "Próximos voos:\n";
            for (int i = 0; i < proximos_voos.size(); i++){
                voos += proximos_voos.get(i).toString() + "\n";
            }
        }
        return voos;
    }

    public String getHistoricoVoos(){
        //mostra a lista do historico de voos da companhia
        String voos;
        if(historico_voos.size() == 0){
            voos = "Esta companhia ainda não teve nenhum voo.\n";
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

    public String getAvioes() {
        //devolve os avioes que a companhia tem
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

    public String toString(){
        return nome;
    }
}
