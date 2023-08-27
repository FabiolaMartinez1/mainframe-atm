package bo.edu.ucb.sis213.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bo.edu.ucb.sis213.bl.AtmBL;

import javax.swing.border.LineBorder;

public class MenuView {
    private JPanel mainMenuPanel;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton changePinButton;
    private JButton exitButton;
    private JFrame frame;
    private JButton btnNewButton;

    public MenuView(AtmBL bl) {
        // this.controller = new Controller(connection);
        frame = new JFrame("ATM App - Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(868, 526);

        initMainMenu(bl.getNombre(),bl);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    private void initMainMenu(String username, AtmBL bl) {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBorder(new LineBorder(new Color(0, 139, 139), 0, true));
        mainMenuPanel.setBackground(new Color(15, 15, 112));

        checkBalanceButton = new JButton("Consultar saldo");
        checkBalanceButton.setForeground(new Color(255, 255, 255));
        checkBalanceButton.setBackground(new Color(0, 128, 128));
        checkBalanceButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        checkBalanceButton.setBounds(104, 118, 259, 102);
        checkBalanceButton.setBorder(new LineBorder(new Color(0, 139, 139), 5, true));
        
        depositButton = new JButton("Realizar un dep\u00F3sito");
        depositButton.setForeground(new Color(255, 255, 255));
        depositButton.setBackground(new Color(0, 128, 128));
        depositButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        depositButton.setBounds(104, 263, 259, 102);
        depositButton.setBorder(new LineBorder(new Color(0, 139, 139), 5, true));
        
        withdrawButton = new JButton("Realizar un retiro");
        withdrawButton.setForeground(new Color(255, 255, 255));
        withdrawButton.setBackground(new Color(0, 128, 128));
        withdrawButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        withdrawButton.setBounds(518, 263, 259, 102);
        withdrawButton.setBorder(new LineBorder(new Color(0, 139, 139), 5, true));
        
        changePinButton = new JButton("Cambiar PIN");
        changePinButton.setForeground(new Color(255, 255, 255));
        changePinButton.setBackground(new Color(0, 128, 128));
        changePinButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
        changePinButton.setBounds(518, 118, 259, 102);
        changePinButton.setBorder(new LineBorder(new Color(0, 139, 139), 5, true));
        
        exitButton = new JButton("Cerrar sesi\u00F3n");
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        exitButton.setBounds(234, 409, 396, 48);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultaView(bl);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DepositoView(bl);
            }
        });
        
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RetiroView(bl);
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField pinField = new JPasswordField();
                int option = JOptionPane.showConfirmDialog(null, pinField, "Ingrese su PIN", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        String pinStr=String.valueOf(pinField.getPassword());
                        if(bl.validarPIN(pinStr)){
                            new CambiarPinView(bl);
                        }else{
                            JOptionPane.showMessageDialog(null, "PIN incorrecto", "Error", JOptionPane.CANCEL_OPTION);
                        }
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "PIN incorrecto.", "Error", JOptionPane.CANCEL_OPTION);
                    }
                }else{
                    return;
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oplogout = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de cerrar su sesión ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (oplogout == JOptionPane.YES_OPTION) {
                    close();
                    new LoginView(bl);
                }else{
                    return;
                }
                
            }
        });
        mainMenuPanel.setLayout(null);

        JLabel label = new JLabel("Bienvenido, " + username);
        label.setForeground(UIManager.getColor("Button.light"));
        label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
        label.setBounds(84, 23, 463, 85);
        mainMenuPanel.add(label);
        mainMenuPanel.add(checkBalanceButton);
        mainMenuPanel.add(depositButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(changePinButton);
        mainMenuPanel.add(exitButton);

        frame.getContentPane().add(mainMenuPanel);
        
        btnNewButton = new JButton("Salir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton.setBounds(749, 452, 95, 27);
        mainMenuPanel.add(btnNewButton);
    }
    public void close() {
        frame.dispose();
    }
}
