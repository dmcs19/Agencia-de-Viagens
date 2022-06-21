package tps.tp4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Main {

    private static ArrayList<Conta> contas = new ArrayList<Conta>();
    private static ArrayList<Companhia> companhias = new ArrayList<Companhia>();
    private static ArrayList<Aviao> avioes = new ArrayList<Aviao>();
    private static ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
    private static ArrayList<Aeroporto> aeroportos = new ArrayList<Aeroporto>();
    private static ArrayList<Voo> voos = new ArrayList<Voo>();

    private static String data_regresso = "";
    private static String email_piloto = "";
    private static int index;
    private static int index_account;

    public static void main(String[] args) throws ParseException, TransformerException, ParserConfigurationException, IOException {
        
        //abre e le o fichero BaseDados.xml e cria os objetos lá presentes
        try {

            File inputFile = new File("XML/BaseDados.xml");
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            
            XPath xpath = XPathFactory.newInstance().newXPath();
            String expression = "/BaseDados/*";
            NodeList nList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
           
            //percorre todo o xml e cria os objetos
            for(int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if(nNode.getNodeName().equals("Conta")){
                    //cria e adiciona uma conta ao arraylist de contas se o arraylist estiver vazio ou se nou houver outra conta igual
                    Conta c = Conta.build(nNode);
                    boolean add = true;
                    if(contas.size() > 0){
                        for(int j = 0; j < contas.size(); j++){
                            if(c.equals(contas.get(j))){ 
                                add = false;
                            }
                        }
                        if(add){
                            contas.add(c);
                        }
                    }else{
                        contas.add(c);
                    }
                }else if(nNode.getNodeName().equals("Companhia")){
                    //cria e adiciona uma companhia ao arraylist de companhias se o arraylist estiver vazio ou se nou houver outra companhia igual
                    Companhia C = Companhia.build(nNode);
                    boolean add = true;
                    if(companhias.size() > 0){
                        for(int j = 0; j < companhias.size(); j++){
                            if(C.equals(companhias.get(j))){ 
                                add = false;
                            }
                        }
                        if(add){
                            companhias.add(C);
                        }
                    }else{
                        companhias.add(C);
                    }
                }else if(nNode.getNodeName().equals("Aviao")){
                    //cria e adiciona um aviao ao arraylist de avioes se o arraylist estiver vazio ou se nao houver outro aviao igual
                    Aviao a = Aviao.build(nNode);
                    boolean add = true;
                    if(avioes.size() > 0){
                        for(int j = 0; j < companhias.size(); j++){
                            if(a.equals(avioes.get(j))){ 
                                add = false;
                            }
                        }
                        if(add){
                            avioes.add(a);
                        }
                    }else{
                        avioes.add(a);
                    }
                }else if(nNode.getNodeName().equals("Piloto")){
                    //cria e adiciona um piloto ao arraylist de pilotos se o arraylist estiver vazio ou se nao houver outro piloto igual
                    Piloto P = Piloto.build(nNode);
                    boolean add = true;
                    if(pilotos.size() > 0){
                        for(int j = 0; j < pilotos.size(); j++){
                            if(P.equals(pilotos.get(j))){ 
                                add = false;
                            }
                        }
                        if(add){
                            pilotos.add(P);
                        }
                    }else{
                        pilotos.add(P);
                    }
                }else if(nNode.getNodeName().equals("Aeroporto")){
                    //cria e adiciona um aeroporto ao arraylist de aeroportos se o arraylist estiver vazio ou se nao houver outro aeroporto igual
                    Aeroporto A = Aeroporto.build(nNode);
                    boolean add = true;
                    if(aeroportos.size() > 0){
                        for(int j = 0; j < aeroportos.size(); j++){
                            if(A.equals(aeroportos.get(j))){ 
                                add = false;
                            }
                        }
                        if(add){
                            aeroportos.add(A);
                        }
                    }else{
                        aeroportos.add(A);
                    }
                }else if(nNode.getNodeName().equals("Voo")){
                    //cria e adiciona um voo ao arraylist de voos se o arraylist estiver vazio ou se nao houver outro voo igual
                    Voo V = Voo.build(nNode, companhias, avioes, pilotos, aeroportos);
                    boolean add = true;
                    if(voos.size() > 0){
                        for(int j = 0; j < voos.size(); j++){
                            if(V.equals(voos.get(j))){ 
                                add = false;
                            }
                        }
                        if(add){
                            voos.add(V);
                        }
                    }else{
                        voos.add(V);
                    }
                }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Frame
        JFrame frame = new JFrame("Agência de viagens - Diogo Saraiva");
        frame.setSize(1000, 800);
        frame.setResizable(false);


        //Panels
        // Menu panel
        JPanel p_menu = new JPanel();
        p_menu.setLayout(null);
        frame.setContentPane(p_menu);
        // Login panel
        JPanel p_login = new JPanel();
        p_login.setLayout(null);
        // SignUp panel
        JPanel p_signup = new JPanel();
        p_signup.setLayout(null);
        // BuyFligths panel
        JPanel p_buyFligths = new JPanel();
        p_buyFligths.setLayout(null);
        // Piloto panel
        JPanel p_piloto = new JPanel();
        p_piloto.setLayout(null);
        // Admin panel
        JPanel p_admin = new JPanel();
        p_admin.setLayout(null);



        //Menu items
        // LogIn button
        JButton b_login = new JButton("LogIn");
        b_login.addActionListener(e -> {
            // trocar para o panel de login
            frame.setContentPane(p_login);
            frame.revalidate();
        });
        b_login.setBounds(400, 250, 200, 100);
        p_menu.add(b_login);
        // signUp button
        JButton b_signup = new JButton("SignUp");
        b_signup.setBounds(400, 400, 200, 100);
        b_signup.addActionListener(e -> {
            // trocar para o panel de signup
            frame.setContentPane(p_signup);
            frame.revalidate();
        });
        p_menu.add(b_signup);



        //Login Items
        // Login label
        JLabel l_login = new JLabel("Login");
        l_login.setBounds(375, 100, 200, 100);
        l_login.setFont(new Font("Arial", Font.BOLD, 35));
        p_login.add(l_login);
        // UsernameEmail Label
        JLabel l_email = new JLabel("Email");
        l_email.setBounds(375, 200, 250, 30);
        p_login.add(l_email);
        // Username Field
        JTextField tf_usernameField = new JTextField();
        tf_usernameField.setBounds(375, 230, 250, 30);
        p_login.add(tf_usernameField);
        // Password Label
        JLabel l_password = new JLabel("Password");
        l_password.setBounds(375, 275, 250, 30);
        p_login.add(l_password);
        // Password Field
        JPasswordField tf_passwordField = new JPasswordField();
        tf_passwordField.setBounds(375,305, 250, 30);
        p_login.add(tf_passwordField);
        //credentials error
        JLabel l_credentials = new JLabel("O teu email ou password estão erradas.");
        l_credentials.setBounds(375,340, 300, 30);
        l_credentials.setForeground(Color.RED);
        l_credentials.setVisible(false);
        p_login.add(l_credentials);
        // Login button
        JButton b1_login = new JButton("Login");
        b1_login.setBounds(375, 380, 250, 30);
        b1_login.addActionListener(e ->{
            String email = tf_usernameField.getText().toString();
            char[] password_array = tf_passwordField.getPassword();
            String password = "";
            for(int i = 0; i < password_array.length; i++){
                password += password_array[i];
            }
            tf_passwordField.setText("");
            boolean exists = false;
            index_account = 0;
            for(int i = 0; i < contas.size(); i++){
                if(contas.get(i).validarConta(email, password)){
                    exists = true;
                    index_account = i;
                }
            }
            if(exists && contas.get(index_account).getEstatuto().equals("Administrador")){
                //passar para o painel de administrador
                l_credentials.setVisible(false);
                tf_usernameField.setText("");
            }else if(exists && contas.get(index_account).getEstatuto().equals("Piloto")){
                //passar para o painel do piloto
                l_credentials.setVisible(false);
                tf_usernameField.setText("");
                email_piloto = email;
                //obter o nome do piloto e o indice no array
                String[]parts = email_piloto.split("@");
                String[]nomes = parts[0].split("_");
                String nome_proprio = nomes[0].substring(0, 1).toUpperCase() + nomes[0].substring(1);
                String apelido = nomes[1].substring(0, 1).toUpperCase() + nomes[1].substring(1);
                String nome_completo = nome_proprio + " " + apelido;
                for(int i = 0; i < pilotos.size(); i++){
                    if(nome_completo.equals(pilotos.get(i).getNome())){
                        index = i;
                    }
                }
                // criar um label com o nome do piloto
                //nome piloto label
                JLabel l_piloto = new JLabel("Piloto: " + pilotos.get(index).getNome());
                l_piloto.setBounds(25, 20, 500, 100);
                l_piloto.setFont(new Font("Arial", Font.BOLD, 35));
                p_piloto.add(l_piloto);
                // criar as tabelas com os voos do piloto
                //tabela de todos os voos do piloto
                int voos_total = pilotos.get(index).getNumVoos();
                int num_proximos_voos = pilotos.get(index).getNumProximosVoos();
                int num_historico_voos = pilotos.get(index).getNumHistoricoVoos();
                String[] columnsName = {"Código", "Avião", "Origem", "Destino", "Partida", "Chegada", "Data", "Estado"};
                Object[][] data_total = new Object[voos_total + 1][8];
                Object[][] data_proximos = new Object[num_proximos_voos + 1][8];
                Object[][] data_historico = new Object[num_historico_voos + 1][8];
                //adicionar os detalhes dos voos a tabela
                data_total[0][0] = "Código";
                data_total[0][1] = "Avião";
                data_total[0][2] = "Origem";
                data_total[0][3] = "Destino";
                data_total[0][4] = "Partida";
                data_total[0][5] = "Chegada";
                data_total[0][6] = "Data";
                data_total[0][7] = "Estado";
                data_proximos[0][0] = "Código";
                data_proximos[0][1] = "Avião";
                data_proximos[0][2] = "Origem";
                data_proximos[0][3] = "Destino";
                data_proximos[0][4] = "Partida";
                data_proximos[0][5] = "Chegada";
                data_proximos[0][6] = "Data";
                data_proximos[0][7] = "Estado";
                data_historico[0][0] = "Código";
                data_historico[0][1] = "Avião";
                data_historico[0][2] = "Origem";
                data_historico[0][3] = "Destino";
                data_historico[0][4] = "Partida";
                data_historico[0][5] = "Chegada";
                data_historico[0][6] = "Data";
                data_historico[0][7] = "Estado";
                ArrayList<Voo> voos_piloto = pilotos.get(index).getVoos();
                ArrayList<Voo> proximos_voos_piloto = pilotos.get(index).getProximosVoos();
                ArrayList<Voo> historico_voos_piloto = pilotos.get(index).getHistoricoVoos();
                if(voos_total > 0){
                    for(int i = 1; i < voos_total + 1; i++){
                        for(int j = 0; j < 8; j++){
                            if(j == 0){
                                data_total[i][j] = voos_piloto.get(i - 1).getCodigo();
                            }else if(j == 1){
                                data_total[i][j] = voos_piloto.get(i - 1).getAviao().getModelo();
                            }else if(j == 2){
                                data_total[i][j] = voos_piloto.get(i - 1).getOrigem().getNome();
                            }else if(j == 3){
                                data_total[i][j] = voos_piloto.get(i - 1).getDestino().getNome();
                            }else if(j == 4){
                                data_total[i][j] = voos_piloto.get(i - 1).getPartida();
                            }else if(j == 5){
                                data_total[i][j] = voos_piloto.get(i - 1).getChegada();
                            }else if(j == 6){
                                data_total[i][j] = voos_piloto.get(i - 1).getDate();
                            }else{
                                data_total[i][j] = voos_piloto.get(i - 1).getEstado();
                            }
                        }
                    }
                }
                if(num_proximos_voos > 0){
                    for(int i = 1; i < num_proximos_voos + 1; i++){
                        for(int j = 0; j < 8; j++){
                            if(j == 0){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getCodigo();
                            }else if(j == 1){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getAviao().getModelo();
                            }else if(j == 2){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getOrigem().getNome();
                            }else if(j == 3){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getDestino().getNome();
                            }else if(j == 4){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getPartida();
                            }else if(j == 5){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getChegada();
                            }else if(j == 6){
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getDate();
                            }else{
                                data_proximos[i][j] = proximos_voos_piloto.get(i - 1).getEstado();
                            }
                        }
                    }
                }
                if(num_historico_voos > 0){
                    for(int i = 1; i < num_historico_voos + 1; i++){
                        for(int j = 0; j < 8; j++){
                            if(j == 0){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getCodigo();
                            }else if(j == 1){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getAviao().getModelo();
                            }else if(j == 2){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getOrigem().getNome();
                            }else if(j == 3){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getDestino().getNome();
                            }else if(j == 4){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getPartida();
                            }else if(j == 5){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getChegada();
                            }else if(j == 6){
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getDate();
                            }else{
                                data_historico[i][j] = historico_voos_piloto.get(i - 1).getEstado();
                            }
                        }
                    }
                }
                TableModel model_total = new DefaultTableModel(data_total, columnsName){
                    public boolean isCellEditable(int row, int column){
                        return false;//This causes all cells to be not editable
                    }
                };
                JTable table_total = new JTable(model_total){
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                        Component c = super.prepareRenderer(renderer, row, column);
                        if(!c.getBackground().equals(getSelectionBackground())) {
                            Color coleur = (row == 0 ? Color.CYAN : Color.WHITE);
                            c.setBackground(coleur);
                            coleur = null;
                        }
                        return c;
                    }
                };
                TableModel model_proximos = new DefaultTableModel(data_proximos, columnsName){
                    public boolean isCellEditable(int row, int column){
                        return false;//This causes all cells to be not editable
                    }
                };
                JTable table_proximos = new JTable(model_proximos){
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                        Component c = super.prepareRenderer(renderer, row, column);
                        if(!c.getBackground().equals(getSelectionBackground())) {
                            Color cor = (row == 0 ? Color.CYAN : Color.WHITE);
                            c.setBackground(cor);
                            cor = null;
                        }
                        return c;
                    }
                };
                TableModel model_historico = new DefaultTableModel(data_historico, columnsName){
                    public boolean isCellEditable(int row, int column){
                        return false;//This causes all cells to be not editable
                    }
                };
                JTable table_historico = new JTable(model_historico){
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                        Component c = super.prepareRenderer(renderer, row, column);
                        if(!c.getBackground().equals(getSelectionBackground())) {
                            Color cor = (row == 0 ? Color.CYAN : Color.WHITE);
                            c.setBackground(cor);
                            cor = null;
                        }
                        return c;
                    }
                };
                table_total.setBounds(25, 150, 725, 585);
                table_proximos.setBounds(25, 150, 725, 585);
                table_historico.setBounds(25, 150, 725, 585);
                p_piloto.add(table_proximos);
                p_piloto.add(table_historico);
                p_piloto.add(table_total);
                table_proximos.setVisible(true);
                table_historico.setVisible(false);
                table_total.setVisible(false);
                // botoes para alterar as tabelas
                // button proximos voos
                JButton b_proximos_voos = new JButton("Próximos Voos");
                b_proximos_voos.setBounds(795, 250, 150, 75);
                b_proximos_voos.addActionListener(l ->{
                    table_proximos.setVisible(true);
                    table_historico.setVisible(false);
                    table_total.setVisible(false);
                });
                p_piloto.add(b_proximos_voos);
                // button historico voos
                JButton b_historico_voos = new JButton("Histórico Voos");
                b_historico_voos.setBounds(795, 350, 150, 75);
                b_historico_voos.addActionListener(l ->{
                    table_historico.setVisible(true);
                    table_proximos.setVisible(false);
                    table_total.setVisible(false);
                });
                p_piloto.add(b_historico_voos);
                // button total voos
                JButton b_total_voos = new JButton("Todos os Voos");
                b_total_voos.setBounds(795, 450, 150, 75);
                b_total_voos.addActionListener(l ->{
                    table_total.setVisible(true);
                    table_proximos.setVisible(false);
                    table_historico.setVisible(false);
                });
                p_piloto.add(b_total_voos);
                //terminar sessao button
                JButton b1_terminar = new JButton("Terminar Sessão");
                b1_terminar.setBounds(800, 25, 150, 75);
                b1_terminar.addActionListener(l ->{
                    p_piloto.removeAll();
                    frame.setContentPane(p_menu);
                    frame.revalidate();
                });
                p_piloto.add(b1_terminar);
                frame.setContentPane(p_piloto);
                frame.revalidate();
            }else if(exists && contas.get(index_account).getEstatuto().equals("Cliente")){
                //passar para o painel de Cliente
                l_credentials.setVisible(false);
                tf_usernameField.setText("");
                frame.setContentPane(p_buyFligths);
                frame.revalidate();
            }else{
                l_credentials.setVisible(true);
            }
        });
        p_login.add(b1_login);
        // NewUser
        JLabel l_newUser = new JLabel("New User?");
        l_newUser.setBounds(375, 415, 75, 30);
        l_newUser.setForeground(Color.gray);
        p_login.add(l_newUser);
        // signUp redirect button
        JButton l_signUp = new JButton("SignUp");
        l_signUp.setBounds(425, 415, 80, 30);
        l_signUp.addActionListener(e ->{
            // trocar para o panel de signUp
            l_credentials.setVisible(false);
            tf_usernameField.setText("");
            tf_passwordField.setText("");
            frame.setContentPane(p_signup);
            frame.revalidate();
        });
        // apagar o background do botao para deixar apenas o texto
        l_signUp.setBorderPainted(false); 
        l_signUp.setContentAreaFilled(false); 
        l_signUp.setFocusPainted(false); 
        l_signUp.setOpaque(false);
        p_login.add(l_signUp);
        JButton b_voltar = new JButton("Voltar");
        b_voltar.setBounds(20, 20, 100, 50);
        b_voltar.addActionListener(e -> {
            // trocar para o panel Menu
            l_credentials.setVisible(false);
            tf_usernameField.setText("");
            tf_passwordField.setText("");
            frame.setContentPane(p_menu);
            frame.revalidate();
        });
        p_login.add(b_voltar);



        //SignUo Items
        // signUp label
        JLabel l1_signUp = new JLabel("SignUp");
        l1_signUp.setBounds(375, 100, 200, 100);
        l1_signUp.setFont(new Font("Arial", Font.BOLD, 35));
        p_signup.add(l1_signUp);
        // UsernameEmail Label
        JLabel l1_email = new JLabel("Email");
        l1_email.setBounds(375, 200, 250, 30);
        p_signup.add(l1_email);
        // Username Field
        JTextField tf1_usernameField = new JTextField();
        tf1_usernameField.setBounds(375, 230, 250, 30);
        p_signup.add(tf1_usernameField);
        // Password Label
        JLabel l1_password = new JLabel("Password");
        l1_password.setBounds(375, 275, 250, 30);
        p_signup.add(l1_password);
        // Password Field
        JPasswordField tf1_passwordField = new JPasswordField();
        tf1_passwordField.setBounds(375,305, 250, 30);
        p_signup.add(tf1_passwordField);
        //credentials error
        JLabel l1_credentials = new JLabel("O email que inseriste já está a ser utilizado.");
        l1_credentials.setBounds(375,340, 400, 30);
        l1_credentials.setForeground(Color.RED);
        l1_credentials.setVisible(false);
        p_signup.add(l1_credentials);
        JLabel l2_credentials = new JLabel("O email que inseriste não é válido.");
        l2_credentials.setBounds(375,340, 400, 30);
        l2_credentials.setForeground(Color.RED);
        l2_credentials.setVisible(false);
        p_signup.add(l2_credentials);
        JLabel l3_credentials = new JLabel("A tua password tem de ter pelo menos um caracter.");
        l3_credentials.setBounds(375,340, 400, 30);
        l3_credentials.setForeground(Color.RED);
        l3_credentials.setVisible(false);
        p_signup.add(l3_credentials);
        // SignUp button
        JButton b1_signup = new JButton("SignUp");
        b1_signup.setBounds(375, 380, 250, 30);
        b1_signup.addActionListener(e ->{
            String email = tf1_usernameField.getText().toString();
            char[] password_array = tf1_passwordField.getPassword();
            String password = "";
            for(int i = 0; i < password_array.length; i++){
                password += password_array[i];
            }
            if(password.length() == 0){
                l1_credentials.setVisible(false);
                l2_credentials.setVisible(false);
                l3_credentials.setVisible(true);
            }else{
                tf1_passwordField.setText("");
                boolean exists = false;
                for(int i = 0; i < contas.size(); i++){
                    if(email.equals(contas.get(i).getEmail())){
                        exists = true;
                    }
                }
                if(exists){
                    l1_credentials.setVisible(true);
                    l2_credentials.setVisible(false);
                    l3_credentials.setVisible(false);
                }else{
                    Conta c = new Conta(email, password);
                    if(c.isValidEmailAddress()){
                        l1_credentials.setVisible(false);
                        l2_credentials.setVisible(false);
                        l3_credentials.setVisible(false);
                        contas.add(c);
                        //escrever xml
                        try {
                            writeXml();
                        } catch (FileNotFoundException | TransformerException | ParserConfigurationException e1) {
                            e1.printStackTrace();
                        }
                        //mudar para o painel de login
                        l1_credentials.setVisible(false);
                        l2_credentials.setVisible(false);
                        l3_credentials.setVisible(false);
                        tf1_usernameField.setText("");
                        tf1_passwordField.setText("");
                        //criar um piloto no xml se a conta for para um piloto
                        if(c.getEstatuto().equals("Piloto")){
                            String[]parts = email.split("@");
                            String[]nomes = parts[0].split("_");
                            String nome_proprio = nomes[0].substring(0, 1).toUpperCase() + nomes[0].substring(1);
                            String apelido = nomes[1].substring(0, 1).toUpperCase() + nomes[1].substring(1);
                            String nome_completo = nome_proprio + " " + apelido;
                            Piloto p = new Piloto(nome_completo, 0);
                            pilotos.add(p);
                            try {
                                writeXml();
                            } catch (FileNotFoundException | TransformerException | ParserConfigurationException e1) {
                                e1.printStackTrace();
                            }
                        }
                        frame.setContentPane(p_login);
                        frame.revalidate();
                    }else{
                        l1_credentials.setVisible(false);
                        l2_credentials.setVisible(true);
                        l3_credentials.setVisible(false);
                    }
                }
            }
        });
        p_signup.add(b1_signup);
        //Already user
        JLabel l_alreadyUser = new JLabel("Already User?");
        l_alreadyUser.setBounds(375, 415, 100, 30);
        l_alreadyUser.setForeground(Color.gray);
        p_signup.add(l_alreadyUser);
        // Login redirect button
        JButton l1_login = new JButton("LogIn");
        l1_login.setBounds(440, 415, 80, 30);
        l1_login.addActionListener(e ->{
            // trocar para o panel de LogIn
            l1_credentials.setVisible(false);
            l2_credentials.setVisible(false);
            l3_credentials.setVisible(false);
            tf1_usernameField.setText("");
            tf1_passwordField.setText("");
            frame.setContentPane(p_login);
            frame.revalidate();
        });
        // apagar o background do botao para deixar apenas o texto
        l1_login.setBorderPainted(false); 
        l1_login.setContentAreaFilled(false); 
        l1_login.setFocusPainted(false); 
        l1_login.setOpaque(false);
        p_signup.add(l1_login);
        //voltar button
        JButton b1_voltar = new JButton("Voltar");
        b1_voltar.setBounds(20, 20, 100, 50);
        b1_voltar.addActionListener(e ->{
            // trocar para o panel Menu
            l1_credentials.setVisible(false);
            l2_credentials.setVisible(false);
            l3_credentials.setVisible(false);
            tf1_usernameField.setText("");
            tf1_passwordField.setText("");
            frame.setContentPane(p_menu);
            frame.revalidate();
        });
        p_signup.add(b1_voltar);



        //buy_fligths Items
        //de e para labels
        JLabel l_de = new JLabel("De");
        l_de.setBounds(110, 255, 200, 20);
        JLabel l_para = new JLabel("Para");
        l_para.setBounds(335, 255, 200, 20);
        p_buyFligths.add(l_de);
        p_buyFligths.add(l_para);
        //De e para combobox
        JComboBox <String> aeroportos_partida = new JComboBox<>();
        aeroportos_partida.setBounds(85, 275, 200, 40);
        JComboBox <String> aeroportos_chegada = new JComboBox<>();
        aeroportos_chegada.setBounds(325, 275, 200, 40);
        aeroportos_partida.addItem("");
        aeroportos_chegada.addItem("");
        for(int i = 0; i < aeroportos.size(); i++){
            aeroportos_partida.addItem(aeroportos.get(i).getCidade() + "(" + aeroportos.get(i).getAbreviatura() + ")");
            aeroportos_chegada.addItem(aeroportos.get(i).getCidade() + "(" + aeroportos.get(i).getAbreviatura() + ")");
        }
        p_buyFligths.add(aeroportos_partida);
        p_buyFligths.add(aeroportos_chegada);
        //troca buttons
        //imagem do botao
        BufferedImage buttonIcon = ImageIO.read(new File("img/trocabotao.png"));
        JButton b_troca = new JButton(new ImageIcon(buttonIcon));
        b_troca.setBorder(BorderFactory.createEmptyBorder());
        b_troca.setContentAreaFilled(false);
        b_troca.setBounds(285, 275, 40, 40);
        b_troca.addActionListener(e ->{
            //trocar a partida com o destino
        });
        p_buyFligths.add(b_troca);
        // partida textfield
        JTextField tf_partida = new JTextField();
        tf_partida.setBounds(550, 275, 150, 40);
        p_buyFligths.add(tf_partida);
        //regresso textfield
        JTextField tf_regresso = new JTextField();
        tf_regresso.setBounds(725, 275, 150, 40);
        tf_regresso.setText("dd-MM-yyyy");
        p_buyFligths.add(tf_regresso);
        //partida label
        JLabel l_partida = new JLabel("Partida");
        l_partida.setBounds(565, 255, 200, 20);
        p_buyFligths.add(l_partida);
        //regresso label
        JLabel l_regresso = new JLabel("Regresso");
        l_regresso.setBounds(740, 255, 200, 20);
        p_buyFligths.add(l_regresso);
        //Ida e Ida e volta checkbox
        JCheckBox cb_ida = new JCheckBox("Ida");
        cb_ida.setBounds(300, 200, 50, 20);
        cb_ida.setSelected(true);
        tf_regresso.setEnabled(false);
        JCheckBox cb_ida_volta = new JCheckBox("Ida e Volta");
        cb_ida_volta.setBounds(400, 200, 100, 20);
        cb_ida.addActionListener(e ->{
            if(cb_ida.isSelected()){
                cb_ida_volta.setSelected(false);
                data_regresso = tf_regresso.getText();
                tf_regresso.setText("dd-MM-yyyy");
                tf_regresso.setEnabled(false);
            }
            if(!cb_ida.isSelected() && !cb_ida_volta.isSelected()){
                cb_ida.setSelected(true);
            }
        });
        cb_ida_volta.addActionListener(e ->{
            if(cb_ida_volta.isSelected()){
                cb_ida.setSelected(false);
                tf_regresso.setText(data_regresso);
                tf_regresso.setEnabled(true);
            }
            if(!cb_ida.isSelected() && !cb_ida_volta.isSelected()){
                cb_ida_volta.setSelected(true);
            }
        });
        p_buyFligths.add(cb_ida);
        p_buyFligths.add(cb_ida_volta);
        //procurar voo button
        JButton b_procurar = new JButton("Procurar Voos ->");
        b_procurar.setBounds(600, 375, 200, 50);
        b_procurar.addActionListener(e ->{
            //procurar os voos pedidos
            if(cb_ida.isSelected()){
                //voos so de ida
            }else{
                //voos de ida e volta
            }
        });
        p_buyFligths.add(b_procurar);
        //terminar sessao button
        JButton b_terminar = new JButton("Terminar Sessão");
        b_terminar.setBounds(800, 25, 150, 75);
        b_terminar.addActionListener(e ->{
            aeroportos_partida.setSelectedIndex(0);
            aeroportos_chegada.setSelectedIndex(0);
            cb_ida.setSelected(true);
            cb_ida_volta.setSelected(false);
            tf_partida.setText("");
            data_regresso = "";
            tf_regresso.setText("dd-MM-yyyy");
            tf_regresso.setEnabled(false);
            frame.setContentPane(p_menu);
            frame.revalidate();
        });
        p_buyFligths.add(b_terminar);



        // Inicializacao dos frames
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //atualiza o documento xml da base de dados
    private static void writeXml() throws TransformerException, FileNotFoundException, ParserConfigurationException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        
        FileOutputStream output = new FileOutputStream("XML/BaseDados.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document newDoc = dBuilder.newDocument();
        Element BaseDados = newDoc.createElement("BaseDados");

        //adiciona ao documento todos os objetos de todas as classes
        if(contas.size() > 0){
            for(int i = 0; i < contas.size(); i++){
                BaseDados.appendChild(contas.get(i).createElement(newDoc));
            }
        }
        if(companhias.size() > 0){
            for(int i = 0; i < companhias.size(); i++){
                BaseDados.appendChild(companhias.get(i).createElement(newDoc));
            }
        }
        if(avioes.size() > 0){
            for(int i = 0; i < avioes.size(); i++){
                BaseDados.appendChild(avioes.get(i).createElement(newDoc));
            }
        }
        if(pilotos.size() > 0){
            for(int i = 0; i < pilotos.size(); i++){
                BaseDados.appendChild(pilotos.get(i).createElement(newDoc));
            }
        }
        if(aeroportos.size() > 0){
            for(int i = 0; i < aeroportos.size(); i++){
                BaseDados.appendChild(aeroportos.get(i).createElement(newDoc));
            }
        }
        if(voos.size() > 0){
            for(int i = 0; i < voos.size(); i++){
                BaseDados.appendChild(voos.get(i).createElement(newDoc));
            }
        }

        newDoc.appendChild(BaseDados);

        // pretty print XML
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        DOMSource source = new DOMSource(newDoc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}
