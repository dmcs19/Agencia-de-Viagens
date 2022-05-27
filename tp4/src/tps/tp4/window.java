package tps.tp4;

import javax.swing.*;

public class window{

    public static void main(String[] args) {
        JFrame frame = new JFrame("A mãe do Chico");
        frame.setSize(600, 400);

        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        frame.setContentPane(panel);

        JLabel label = new JLabel("Saraiva comeu a...");
        panel.add(label);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Mãe");
        comboBox.addItem("Tia");
        comboBox.addItem("Avó");
        panel.add(comboBox);

        JLabel label1 = new JLabel("do Chico");
        panel.add(label1);

        //textfield
        JTextField textField = new JTextField();
        textField.addActionListener(e ->{
            System.out.println("A vanda é boa como o milho!");
        });
        panel.add(textField);

        JCheckBox checkBox = new JCheckBox("Seleciona se já comeste a mãe do chico.");
        checkBox.addActionListener(e -> {
            if(checkBox.isSelected()){
                System.out.println("Comi");
            }else{
                System.out.println("Não quis");
            }
        });
        panel.add(checkBox);

        //button
        JButton button = new JButton("Perfura-me!");
        button.addActionListener(e -> {
            if(checkBox.isSelected()) {
                System.out.println("O Saraiva comeu a " + textField.getText());
            }else{
                System.out.println("O Saraiva não quis comer a " + textField.getText());
            }
        });
        panel.add(button);

        frame.setVisible(true);
    }
}