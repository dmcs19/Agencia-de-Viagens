package tps.tp4;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Voo {

    private String codigo;
    private Companhia companhia;
    private Piloto piloto;
    private Aeroporto origem;
    private Aeroporto destino;
    private String partida;
    private String chegada;
    private String data;
    private Date hora_partida;
    private Date hora_chegada;
    private Date date;
    private int duracao;
    private Aviao aviao;

    public Voo(String codigo, Aviao aviao, Companhia companhia, Piloto piloto, Aeroporto origem, Aeroporto destino, String partida, String chegada, String data) throws ParseException{
        this.codigo = codigo;
        this.aviao = aviao;
        this.companhia = companhia;
        this.piloto = piloto;
        this.origem = origem;
        this.destino = destino;
        this.partida = partida;
        this.chegada = chegada;
        this.data = data;
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dia = new SimpleDateFormat("dd-MM-yyyy");
        date = dia.parse(data);
        hora_partida = hora.parse(partida);
        hora_chegada = hora.parse(chegada);
        this.novo_voo();
    }

    private void novo_voo(){
        //adicionar o voo ao registo: piloto; aviao; companhia; aeroportos
        piloto.novo_voo(this);
        aviao.novo_voo(this);
        companhia.novo_voo(this);
        origem.novo_voo(this);
        destino.novo_voo(this);
    }

    public void completo(){
        //do o voo como terminado e envia essa informacao para: piloto, aviao, companhia, aeroportos
        piloto.voo_completo(this);
        aviao.voo_completo(this);
        companhia.voo_completo(this);
        origem.voo_completo(this);
        destino.voo_completo(this);
    }

    public void cancelado(){
        piloto.voo_cancelado(this);
        aviao.voo_cancelado(this);
        companhia.voo_cancelado(this);
        origem.voo_cancelado(this);
        destino.voo_cancelado(this);
    }

    public int getDuracao(){
        //retorna a duraçao do voo em horas
        long diferenca = hora_chegada.getTime() - hora_partida.getTime();
        duracao = (int)(diferenca / (1000 * 60 * 60)) % 24;
        if(duracao <= 0){
            duracao += 24;
        }
        return duracao;
    }

    public Aeroporto getOrigem(){
        //retorna o aeroporto de onde sai o voo
        return origem;
    }

    public Date getPartida(){
        return hora_partida;
    }

    public Date getDate(){
        //retorna a data do voo
        return date;
    }

    public boolean equals(Voo voo){
        //verifica se dois voos sao iguais
        if(codigo.equals(voo.codigo)){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString(){
        return "Código: " + codigo + " | Companhia: " + companhia.toString() + " | Origem: " + origem.toString() + " | Destino: " + destino.toString() + " | Partida: " + partida + " | Chegada: " + chegada + " | Data: " + data;
    }
}
