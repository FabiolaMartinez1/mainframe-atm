package bo.edu.ucb.sis213.view;

import javax.swing.*;

import bo.edu.ucb.sis213.bl.AtmBL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RetiroView {
    private JFrame frame;
    private JTextField cantidadField;
    private JButton aceptarButton;
    private JButton cancelarButton;

    public RetiroView(AtmBL bl) {
        frame = new JFrame("Retiro");
        frame.setSize(550, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel label = new JLabel("Ingrese la cantidad a retirar: ");
        label.setFont(new Font("Tahoma", Font.BOLD, 22));
        label.setBounds(65, 65, 368, 35);
        cantidadField = new JTextField();
        cantidadField.setBounds(65, 137, 396, 35);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(0, 139, 139));
        inputPanel.setLayout(null);
        inputPanel.add(label);
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
                        try {
                            String cantidadStr = cantidadField.getText();
                            if(bl.comprobarCantidad(cantidadStr,2)){//comprobar campo
                                int opre = JOptionPane.showConfirmDialog(null, "\u00BFEsta seguro de retirar "+cantidadStr+" Bs. ?", "Confirmar", JOptionPane.YES_NO_OPTION);
                                if (opre == JOptionPane.YES_OPTION) {
                                    if(bl.realizarRetiro(cantidadStr)){
                                        JOptionPane.showMessageDialog(null, "Retiro realizado con \u00E9xito. Su nuevo saldo es: " + bl.getSaldo()+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                        close();
                                    }else{
                                        JOptionPane.showMessageDialog(frame, bl.getTextoE(), bl.getTituloE(), JOptionPane.WARNING_MESSAGE);
                                        return;
                                    }
                                    System.out.println("Saldo==" + bl.getSaldo());
                                }else if (opre == JOptionPane.NO_OPTION) {
                                    System.out.println("\nNo continuar con el retiro");
                                    return;
                                }
                            }else{
                                JOptionPane.showMessageDialog(frame, bl.getTextoE(), bl.getTituloE(), JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Monto no v\u00E1lido \n"+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
