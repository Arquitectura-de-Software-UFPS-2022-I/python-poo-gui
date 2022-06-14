import java.awt.*;
import javax.swing.*;
 public class  MensajeAlerta extends JFrame {
    //Se realiza el mensaje de AVISO!!, cuando se va a realizar la accion de eliminar un paquete

    public MensajeAlerta() {
   
        
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    Object[] textoOpciones={"Cancelar","Aceptar"};
        int opcion = JOptionPane.showOptionDialog(null,
        "Borrando este paquete eliminara de forma definitiva los archivos. \n¿Desea continuar?", "BluePy:Pregunta",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.WARNING_MESSAGE, null, textoOpciones,
        textoOpciones[0]);
    }
    
     public static void main(String[] args) {
         MensajeAlerta g= new MensajeAlerta();
         
     }
 }