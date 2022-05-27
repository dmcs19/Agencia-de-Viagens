package tps.tp4;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Voo {

    private String codigo;
    private Companhia companhia;
    private Piloto piloto;
    private Aeroporto origem;
    private Aeroporto destino;
    String hora_partida;
    String hora_chegada;
    String date;
    private Date partida;
    private Date chegada;
    private Date data;
    private int duracao;
    private Aviao aviao;
    private String estado;

    public Voo(String codigo, Aviao aviao, Companhia companhia, Piloto piloto, Aeroporto origem, Aeroporto destino, String hora_partida, String hora_chegada, String date) throws ParseException{
        this.codigo = codigo;
        this.aviao = aviao;
        this.companhia = companhia;
        this.piloto = piloto;
        this.origem = origem;
        this.destino = destino;
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dia = new SimpleDateFormat("dd-MM-yyyy");
        this.hora_partida = hora_partida;
        this.hora_chegada = hora_chegada;
        this.date = date;
        this.partida = hora.parse(hora_partida);
        this.chegada = hora.parse(hora_chegada);
        this.data = dia.parse(date);
        this.estado = "A tempo";
        this.novo_voo();
    }

    //adicionar o voo ao registo: piloto; aviao; companhia; aeroporto
    private void novo_voo(){
        piloto.novo_voo(this);
        aviao.novo_voo(this);
        companhia.novo_voo(this);
        origem.novo_voo(this);
        destino.novo_voo(this);
    }

    //muda o estado do voo para completo
    public void completo(){
        this.estado = "Completo";
    }

    //muda o estado do voo para cancelado
    public void cancelado(){
        this.estado = "Cancelado";
    }

    //muda o estado do voo para atrasado
    public void atrasado(){
        this.estado = "Atrasado";
    }

    //retorna a duraçao do voo em horas
    public int getDuracao(){
        long diferenca = chegada.getTime() - partida.getTime();
        duracao = (int)(diferenca / (1000 * 60 * 60)) % 24;
        if(duracao <= 0){
            duracao += 24;
        }
        return duracao;
    }

    //retorna o aeroporto de onde sai o voo
    public Aeroporto getOrigem(){
        return origem;
    }

    //retorna a hora de partida
    public Date getPartida(){
        return partida;
    }

    //retorna a data do voo
    public Date getDate(){
        return data;
    }

    //retorna o estado do voo
    public String getEstado(){
        return estado;
    }

    //verifica se dois voos sao iguais
    public boolean equals(Voo voo){
        if(codigo.equals(voo.codigo)){
            return true;
        }
        else{
            return false;
        }
    }

    //da print das informações do voo
    public String toString(){
        return "Código: " + codigo + " | Companhia: " + companhia.toString() + " | Origem: " + origem.toString() + " | Destino: " + destino.toString() + " | Partida: " + hora_partida + " | Chegada: " + hora_chegada + " | Data: " + date + " | Estado: " + estado;
    }

    
}
