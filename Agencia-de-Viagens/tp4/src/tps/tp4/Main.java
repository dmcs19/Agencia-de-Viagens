package tps.tp4;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Companhia tap = new Companhia("TAP");
        Aviao a1 = new Aviao("boeing 777", 350, tap);
        Piloto p1 = new Piloto("Um Berto");
        Aeroporto lis = new Aeroporto("Humberto Delgado", "LIS");
        Aeroporto jfk = new Aeroporto("Humberto Delgado", "LIS");
        Voo v1 = new Voo("ABC123", a1, tap, p1, lis, jfk, "8:00", "16:00", "20-05-2022");
        Voo v2 = new Voo("XYZ780", a1, tap, p1, jfk, lis, "23:00", "4:00", "18-05-2022");
    }
}
