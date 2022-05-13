package tps.tp4;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {

        //Criar Aeroportos
        Aeroporto lis = new Aeroporto("Humberto Delgado", "LIS");
        Aeroporto jfk = new Aeroporto("John F. Kennedy", "JFK");
        Aeroporto muc = new Aeroporto("Aeroporto de Munique", "MUC");

        //Criar Companhias
        Companhia tap = new Companhia("TAP");
        Companhia emirates = new Companhia("Emirates");

        //Criar Avioes
        Aviao a1 = new Aviao("Airbus A330", 350, tap);
        Aviao a2 = new Aviao("Boeing 777", 550, emirates);
        Aviao a3 = new Aviao("Cessna 208", 14, tap);
        Aviao a4 = new Aviao("Boeing 757", 295, emirates);
        Aviao a5 = new Aviao("Boeing 717", 223, tap);

        //Criar pilotos
        Piloto p1 = new Piloto("Um Berto");
        Piloto p2 = new Piloto("Dois Berto");

        //Criar voos
        Voo v1 = new Voo("ABC123", a2, tap, p1, lis, muc, "8:00", "16:00", "13-06-2022");
        Voo v2 = new Voo("XYZ789", a4, tap, p1, jfk, lis, "4:00", "18:00", "12-07-2022");

        //Testes
        System.out.println(lis.getProximosVoos());
        System.out.println(lis.getProximasPartidas());
    }
}
