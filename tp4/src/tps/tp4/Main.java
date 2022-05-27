package tps.tp4;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Companhia tap = new Companhia("TAP");
        Aviao a1 = new Aviao("boeing 777", 350, tap);
        Piloto p1 = new Piloto("Um Berto");
        Aeroporto lis = new Aeroporto("Humberto Delgado", "LIS");
        Aeroporto jfk = new Aeroporto("John F. Kennedy", "JFK");
        Voo v1 = new Voo("ABC123", a1, tap, p1, lis, jfk, "8:00", "16:00", "20-05-2022");
        Voo v2 = new Voo("XYZ780", a1, tap, p1, jfk, lis, "23:00", "4:00", "18-05-2022");

        JFrame frame = new JFrame("Agência de viagens - Diogo Saraiva");
        frame.setSize(1000, 800);
        frame.setResizable(false);

        //Panels
        //Panel Menu
        JPanel p_menu = new JPanel();
        frame.setContentPane(p_menu);
        frame.setLayout(null);
        //Panel Login
        JPanel p_login = new JPanel();

        //Buttons
        //Buttons Menu
        JButton b_login = new JButton("LogIn");
        b_login.addActionListener(e -> {
            //trocar de panel
        });
        b_login.setBounds(400, 250, 200, 100);
        p_menu.add(b_login);
        JButton b_signin = new JButton("SingIn");
        b_signin.setBounds(400, 400, 200, 100);
        p_menu.add(b_signin);

        frame.setVisible(true);
    }
}
