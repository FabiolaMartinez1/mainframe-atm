package bo.edu.ucb.sis213.view;

import javax.swing.*;

import bo.edu.ucb.sis213.bl.AtmBL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarPinView {
    private JFrame frame;
    private JPasswordField nuevoPinField;
    private JPasswordField confirmarPinField;
    private JButton aceptarButton;
    private JButton cancelarButton;


    public CambiarPinView(AtmBL bl) {
        frame = new JFrame("Cambiar PIN");
        frame.setSize(700, 440);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 139, 139));
        JLabel nuevoPinLabel = new JLabel("Ingrese el nuevo PIN:");
        nuevoPinLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        nuevoPinLabel.setBounds(121, 53, 301, 47);
        JLabel confirmarPinLabel = new JLabel("Confirmar nuevo PIN:");
        confirmarPinLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        confirmarPinLabel.setBounds(121, 182, 301, 47);
        nuevoPinField = new JPasswordField();
        nuevoPinField.setBounds(121, 110, 464, 30);
        confirmarPinField = new JPasswordField();
        confirmarPinField.setBounds(121, 239, 464, 30);
        mainPanel.setLayout(null);
        mainPanel.add(nuevoPinLabel);
        mainPanel.add(nuevoPinField);
        mainPanel.add(confirmarPinLabel);
        mainPanel.add(confirmarPinField);

        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        
                aceptarButton = new JButton("Aceptar");
                aceptarButton.setBounds(190, 322, 136, 35);
                mainPanel.add(aceptarButton);
                cancelarButton = new JButton("Cancelar");
                cancelarButton.setBounds(340, 322, 136, 35);
                mainPanel.add(cancelarButton);
                
                        cancelarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                close();
                            }
                        });
                
                        aceptarButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String nuevoPinStr = String.valueOf(nuevoPinField.getPassword());
                                    String confirmarNuevoPinStr = String.valueOf(confirmarPinField.getPassword());
                                    if(bl.validarCambioPIN(nuevoPinStr, confirmarNuevoPinStr)){//valida campos vacios, comprobar si son iguales, si no se repite el pin anterior
                                        int oppin = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de cambiar de PIN", "Confirmar", JOptionPane.YES_NO_OPTION);
                                        if (oppin == JOptionPane.YES_OPTION) {
                                            if(bl.cambiarPIN(nuevoPinStr)){
                                                JOptionPane.showMessageDialog(null, "Cambio de pin realizado con \u00E9xito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                                close();
                                            }else{
                                                JOptionPane.showMessageDialog(frame, bl.getTextoE(), bl.getTituloE(), JOptionPane.WARNING_MESSAGE);
                                                return;
                                            }
                                        }else if (oppin == JOptionPane.NO_OPTION) {
                                            System.out.println("\nNo continuar con el cambio");
                                            return;
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(frame, bl.getTextoE(), bl.getTituloE(), JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                                    close();
                                }
                            }
                        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void close() {
        frame.dispose();
    }
}
