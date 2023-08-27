package bo.edu.ucb.sis213.view;

import javax.swing.*;

import bo.edu.ucb.sis213.bl.AtmBL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositoView {
    private JFrame frame;
    private JTextField cantidadField;
    private JButton aceptarButton;
    private JButton cancelarButton;

    public DepositoView(AtmBL bl) {
        frame = new JFrame("Depósito");
        frame.setSize(550, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel lblIngreseLaCantidad = new JLabel("Ingrese la cantidad a depositar: ");
        lblIngreseLaCantidad.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblIngreseLaCantidad.setBounds(65, 65, 368, 35);
        cantidadField = new JTextField();
        cantidadField.setBounds(65, 137, 396, 35);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(0, 139, 139));
        inputPanel.setLayout(null);
        inputPanel.add(lblIngreseLaCantidad);
        inputPanel.add(cantidadField);
        frame.getContentPane().add(inputPanel, BorderLayout.CENTER);

        aceptarButton = new JButton("Aceptar");
        aceptarButton.setBounds(116, 225, 136, 35);
        inputPanel.add(aceptarButton);
        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(266, 225, 136, 35);
        inputPanel.add(cancelarButton);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cantidadStr = cantidadField.getText();
                int opdep = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de dep\u00F3sitar "+cantidadStr+" Bs. ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opdep == JOptionPane.YES_OPTION) {
                    try {
                        System.out.println("cant: "+cantidadStr);
                        if(bl.realizarDeposito(cantidadStr)){//si guardo?
                            JOptionPane.showMessageDialog(null, "Dep\u00F3sito realizado con \u00E9xito. Su nuevo saldo es: " + bl.getSaldo()+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            close();
                        }else{
                            JOptionPane.showMessageDialog(frame, bl.getTextoE(), bl.getTituloE(), JOptionPane.WARNING_MESSAGE);
                            return;
                        }   
                    } catch (Exception ex) {
                        System.out.println("Error S TT");
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al realizar el dep\u00F3sito: "+ex, "Error", JOptionPane.ERROR_MESSAGE);
                        close();
                    }
                }else if (opdep == JOptionPane.NO_OPTION) {
                    System.out.println("\nNo continuar con el deposito");
                    return;
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
