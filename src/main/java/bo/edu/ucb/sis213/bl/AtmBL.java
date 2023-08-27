package bo.edu.ucb.sis213.bl;

import bo.edu.ucb.sis213.dao.HistoricoDao;
import bo.edu.ucb.sis213.dao.UsuarioDao;

public class AtmBL {
    private UsuarioDao usuarioDao;
    private HistoricoDao historicoDao;

    private static int usuarioId;
    private int usuarioPin;
    private String usuarioNombre;
    private double usuarioSaldo;
    private int intentosRestantes;
    private String textoE;
    private String tituloE;

    public AtmBL() {
        this.usuarioDao = new UsuarioDao();
        this.historicoDao = new HistoricoDao();
        intentosRestantes = 3;
        usuarioSaldo=0;
        usuarioId=0;
        usuarioPin=0;
        usuarioNombre=null;
        textoE="texto";
        tituloE="titulo";
        System.out.println(usuarioId);
        
    }
    public double getSaldo(){
        return usuarioDao.getSaldo(usuarioId);
    }
    public String getNombre(){
        return usuarioDao.getUsuarioNombre(usuarioId);
    }
    public int getPin(){
        return usuarioDao.getUsuarioPIN(usuarioId);
    }
    public int getID(){
        return usuarioDao.getUsuarioID(usuarioNombre, usuarioPin);
    }
    public String getTextoE(){
        return textoE;
    }
    public String getTituloE(){
        return tituloE;
    }
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public boolean comprobarCamposVacios(String ca1, String ca2) {
        if((ca1.isEmpty() && ca2.isEmpty()) || ca1.isBlank() && ca2.isBlank()){
            textoE="Ambos campos se encuentran vacios";
            tituloE="Campos vacios";
            // JOptionPane.showMessageDialog(null, "Ambos campos se encuentran vacios", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(ca1.isEmpty() || ca1.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un usuario", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            textoE="Debe llenar el primer campo";
            tituloE="Campos vacios";
            return false;
        }else if(ca2.isEmpty() || ca2.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar el PIN", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            textoE="Debe llenar el segundo campo";
            tituloE="Campos vacios";
            return false;
        }else{
            return true; 
        }
    }
    public boolean comprobarCantidad(String c, int tipo){
        double cantidad=0.0;
        if(c.isEmpty() || c.isBlank()){
            // JOptionPane.showMessageDialog(null, "Debe ingresar un monto.", "Error", JOptionPane.WARNING_MESSAGE);
            textoE="Debe ingresar un monto.";
            tituloE="Campo vacio";
            return false;
        }else{
            try {
                cantidad = Double.parseDouble(c);
                if (cantidad <= 0) {
                    // JOptionPane.showMessageDialog(null, "Cantidad no v\u00E1lida.", "Error", JOptionPane.ERROR_MESSAGE);
                    textoE="Cantidad no v\u00E1lida.";
                    tituloE="Error";
                    return false;
                }else{
                    if(tipo==2){
                        if(cantidad>getSaldo()){
                            System.out.println("Saldo insuficiente.");
                            textoE="Saldo insuficiente";
                            tituloE="Error";
                            return false;
                            // JOptionPane.showMessageDialog(null, "Saldo insuficiente.", "Error", JOptionPane.WARNING_MESSAGE);
                        }else{
                            return true;
                        }
                    }else{
                        return true;
                    }
                }
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "Monto no num\u00E9rico", "Error", JOptionPane.ERROR_MESSAGE);
                textoE="Monto no numerico";
                tituloE="Error";
                return false;
            }
        }
    }

    public boolean validarLoginBL(String usuario, String pin) {
        if(comprobarCamposVacios(usuario, pin)){
                    try {
                        int npin=Integer.parseInt(pin);
                        if(usuarioDao.getUsuarioID(usuario, npin) > 0 ){
                            usuarioId=usuarioDao.getUsuarioID(usuario, Integer.parseInt(pin));
                            usuarioPin=usuarioDao.getUsuarioPIN(usuarioId);
                            usuarioNombre=usuarioDao.getUsuarioNombre(usuarioId);
                            System.out.println(usuarioId);
                            usuarioSaldo=usuarioDao.getSaldo(usuarioId);
                            System.out.println("tamos aqui TT"+usuarioSaldo);
                            return true;
                        }else {
                            intentosRestantes--;
                            System.out.println("false"+intentosRestantes);
                            if (intentosRestantes > 0) {
                                System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
                                // JOptionPane.showMessageDialog(null, "PIN incorrecto. Le quedan " + intentosRestantes + " intentos.", "Error", JOptionPane.WARNING_MESSAGE);
                                textoE="PIN incorrecto. Le quedan " + intentosRestantes + " intentos.";
                                tituloE="Error";
                                return false;
                            } else {
                                System.out.println("PIN incorrecto. Ha excedido el número de intento(s)."); //joption3
                                // JOptionPane.showMessageDialog(null, "PIN incorrecto. Ha excedido el n\u00FAmero de intentos.", "Error", JOptionPane.ERROR_MESSAGE);
                                textoE="PIN incorrecto. Ha excedido el numero de intentos.";
                                tituloE="Error";
                                // System.exit(0);
                                return false;
                            }
                        }
                    } catch (Exception e) {//si la contraseña es string, no se le dira al usuario del error, solo se le descontaran intentos. Esto por más seguridad sobre el valor del pin
                        intentosRestantes--;
                        System.out.println("false"+intentosRestantes);
                        if (intentosRestantes > 0) {
                            System.out.println("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");//joption2
                            // JOptionPane.showMessageDialog(null, "PIN incorrecto. Le quedan " + intentosRestantes + " intentos.", "Error", JOptionPane.WARNING_MESSAGE);
                            textoE="PIN incorrecto. Le quedan " + intentosRestantes + " intentos.";
                            tituloE="Error";
                            e.printStackTrace();
                            return false;
                        } else {
                            System.out.println("PIN incorrecto. Ha excedido el número de intento(s)."); //joption3
                            // JOptionPane.showMessageDialog(null, "PIN incorrecto. Ha excedido el n\u00FAmero de intentos.", "Error", JOptionPane.ERROR_MESSAGE);
                            textoE="PIN incorrecto. Ha excedido el numero de intentos.";
                            tituloE="Error";
                            e.printStackTrace();
                            // System.exit(0);
                            return false;
                        }
                }
            }else{
                return false;
            }
    }

    public boolean realizarDeposito(String cantidadStr) {
        try {
            double cantidad = Double.parseDouble(cantidadStr);
            System.out.println("saldo antes:"+usuarioSaldo+"\ncant "+cantidad);
            usuarioSaldo=getSaldo()+cantidad;
            usuarioDao.actualizarSaldo(usuarioSaldo,usuarioId);
            System.out.println("Saldo despues==" + usuarioSaldo);
            System.out.println("Saldo==" + getSaldo());
            historicoDao.registrarOperacion(usuarioId,"Deposito", cantidad);
            System.out.println("\nDepósito realizado con éxito. Su nuevo saldo es: $" + usuarioSaldo);//joption5
            // JOptionPane.showMessageDialog(null, "Dep\u00F3sito realizado con \u00E9xito. Su nuevo saldo es: " + usuarioSaldo+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            textoE="No se pudo depositar";
            tituloE="Error";
            return false;
            // JOptionPane.showMessageDialog(null, "Error al realizar el dep\u00F3sito.", "Error", JOptionPane.ERROR_MESSAGE);
        }            
    }
    
    public boolean realizarRetiro(String cantidadStr){
        try {
            double cantidad = Double.parseDouble(cantidadStr);
            System.out.println("antes TT"+(usuarioSaldo));
            System.out.println("cant: "+cantidad);
            usuarioSaldo=getSaldo()-cantidad;
            usuarioDao.actualizarSaldo(usuarioSaldo, usuarioId); 
            System.out.println("despues"+(usuarioSaldo));
            System.out.println(getSaldo()+"getsaldo");
            historicoDao.registrarOperacion(usuarioId, "Retiro", -cantidad); 
            System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + usuarioSaldo);
            // JOptionPane.showMessageDialog(null, "Retiro realizado con \u00E9xito. Su nuevo saldo es: " + usuarioSaldo+" Bs.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            textoE="No se pudo retirar";
            tituloE="Error";
            return false;
        }
    }

    public boolean validarPIN(String pinStr){
        int pin=Integer.parseInt(pinStr);
        if(pin==getPin()){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarCambioPIN(String newPINStr, String confirmPINStr){
        if(comprobarCamposVacios(newPINStr, confirmPINStr)){
            try {
                int newPIN = Integer.parseInt(newPINStr);
                int confirmPIN = Integer.parseInt(confirmPINStr);
                if (newPIN == confirmPIN) {
                    if(getPin()==newPIN){//pin nuevo igual que el antiguo
                        textoE="Esta ingresando el mismo PIN";
                        tituloE="Observación";    
                        return false;
                        // JOptionPane.showMessageDialog(null, "Esta ingresando el mismo PIN", "Observaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        return true;
                    }
                } else {
                    System.out.println("Los PINs no coinciden.");
                    textoE="Los pin no coinciden";
                    tituloE="Error";
                    return false;
                    // JOptionPane.showMessageDialog(null, "Los PINs no coinciden.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                textoE="El pin debe ser n\u00FAmerico (enteros)";
                tituloE="Error";
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean cambiarPIN(String newPINStr){
        try {
            int newPIN = Integer.parseInt(newPINStr);
            usuarioPin=newPIN;
            usuarioDao.actualizarPIN(newPIN, usuarioId);
            historicoDao.registrarOperacion(usuarioId, "Cambio de PIN", 0.0);
            // JOptionPane.showMessageDialog(null, "PIN actualizado con \u00E9xito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            textoE="No se pudo cambiar el pin";
            tituloE="Error";
            return false;
        }
    }
}
