// package bo.edu.ucb.sis213.Controller;

// import bo.edu.ucb.sis213.dao.BackModel;
// // import bo.edu.ucb.sis213.dao.DatabaseConfig;
// import bo.edu.ucb.sis213.view.CambiarPinView;
// import bo.edu.ucb.sis213.view.ConsultaView;
// import bo.edu.ucb.sis213.view.DepositoView;
// import bo.edu.ucb.sis213.view.LoginView;
// import bo.edu.ucb.sis213.view.MenuView;
// import bo.edu.ucb.sis213.view.RetiroView;

// import java.sql.Connection;
// import java.sql.SQLException;

// import javax.swing.JFrame;
// import javax.swing.JOptionPane;
// import javax.swing.JPasswordField;

// public class Controller {
//     private Connection connection;
//     private BackModel model;
    

//     public Controller(Connection connection) {
//         this.connection = connection;
//         this.model = new BackModel(connection);
//     }    
//     //Menú
//     // public boolean validarEntrada(String usuario, String pin){
//     //     return model.validarCredenciales(usuario, pin);
//     }
//     public void mostrarMenu(String usuario){
//         new MenuView(connection, usuario);
//     }
//     //Consulta
//     public void consultarSaldo() {
//         new ConsultaView(model.getSaldo());
//     }
//     //Deposito
//     public void mostrarDepositoView() {
//         new DepositoView(connection);
//     }
//     public boolean realizarDeposito(String cantidadStr){
//         return model.realizarDeposito(cantidadStr);//si guardo?
//     }
//     //Retiro
//     public void mostrarRetiroView() {
//         new RetiroView(connection); // Pasa el controlador a la vista
//     }
//     public boolean realizarRetiro(String cantidadStr){
//         return model.realizarRetiro(cantidadStr);//si guardo?
//     }
//     //pin
//     public void mostrarCambioPINView() {
//        new CambiarPinView(connection); // Pasa el controlador a la vista
//     }
//     public boolean cambioPIN(int nuevoPin, int confirmarNuevoPin) throws SQLException{
//         return model.cambiarPIN(nuevoPin,confirmarNuevoPin);
//     }
//     // public void validarPIN(int currentPIN, int i){
//     //     if (currentPIN == model.getPinActual()) {
//     //                 mostrarCambioPINView();
//     //             }else {
//     //                 System.out.println("PIN incorrecto.");
//     //                 showMessageDialog(null, "PIN incorrecto. Tiene "+(i)+" intentos.\n(Si se le acaba los intentos, su sesión se cerrara)", "Error", JOptionPane.WARNING_MESSAGE);
//     //                 confirmarPIN(i-1);
//     //             }

//     // }
//     public void validarPIN(int i, JFrame frame){
//         // int currentPIN=0;
//         System.out.println(i);
//         JPasswordField pinField = new JPasswordField();
//                 int option = JOptionPane.showConfirmDialog(null, pinField, "Ingrese su PIN", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//                 if (option == JOptionPane.OK_OPTION) {
//                     String pinStr=String.valueOf(pinField.getPassword());
//                     if(i>0){//si aún tiene intentos, solo se le dio 3
//                         if(pinStr.isEmpty() || pinStr==" "){
//                             JOptionPane.showMessageDialog(null, "Campo Vacio. Tiene "+(i)+" intentos.\n(Si se le acaba los intentos, su sesi\u00F3n se cerrara)", "Error", JOptionPane.WARNING_MESSAGE);
//                             validarPIN(i-1, frame);        
//                         }else{
//                             System.out.println(pinStr);
//                             System.out.println(model.getPinActual());
//                             if (pinStr.equalsIgnoreCase(model.getPinActual())) {
//                                     mostrarCambioPINView();
//                                 }else {
//                                     System.out.println("PIN incorrecto.");
//                                     JOptionPane.showMessageDialog(null, "PIN incorrecto. Tiene "+(i)+" intentos.\n(Si se le acaba los intentos, su sesión se cerrara)", "Error", JOptionPane.WARNING_MESSAGE);
//                                     validarPIN(i-1, frame);
//                                 }
//                         }
//                     }else{
//                         JOptionPane.showMessageDialog(null, "PIN incorrecto. Ha excedido el n\u00FAmero de intento(s).", "Error", JOptionPane.ERROR_MESSAGE);
//                         // close();
//                         frame.dispose();
//                         LoginView();
//                     }
                    
//                 } else {
//                     // JOptionPane.showMessageDialog(null, " Cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
//                     //solo se va a cerrar
//                 }
//     }

//     //volver al login
//     public void LoginView() {
//         new LoginView(); // Pasa el controlador a la vista
//     }
//     // public int PinActual(){
//     //     return model.getPinActual();
//     // }

//     // public static void main(String[] args) {
//     //     Connection connection = null;
//     //     try {
//     //         connection = DatabaseConfig.getConnection();
            
//     //     } catch (SQLException ex) {
//     //         System.err.println("No se puede conectar a la Base de Datos");//joption1
//     //         ex.printStackTrace();
//     //         System.exit(1);
//     //     }

//     //     new LoginView(connection);
//     // }
// }