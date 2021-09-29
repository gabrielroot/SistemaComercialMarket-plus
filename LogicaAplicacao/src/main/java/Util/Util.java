package Util;

import java.awt.Dimension;
import static java.lang.Integer.parseInt;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class Util {

    /**
     * 
     * @param date
     * @return Object Calendar
     */
    public static String getStringDateFromCalendar(Calendar date) {
        String dia = String.valueOf(date.get(Calendar.DAY_OF_MONTH)).length() == 1?
                "0" + String.valueOf(date.get(Calendar.DAY_OF_MONTH)):
                String.valueOf(date.get(Calendar.DAY_OF_MONTH));
  
        String mes = String.valueOf(date.get(Calendar.MONTH)).length() == 1?
                "0" + String.valueOf(date.get(Calendar.MONTH)):
                String.valueOf(date.get(Calendar.MONTH));

        String nascimento = "" +
            dia+"/"+
            mes+"/"+
            date.get(Calendar.YEAR);
        return nascimento;
    }
    
    /**
     *
     * @param date
     * Formato esperado: DD/MM/AAAA
     * @return
     */
    public static Calendar getCalendarDateFromString(String date) {
        Calendar nascimento = Calendar.getInstance();
        nascimento.set(
            parseInt(date.split("/",3)[2]),
            parseInt(date.split("/",3)[1]),
            parseInt(date.split("/",3)[0])
        );
     nascimento.get(0);
        return nascimento;
    }
    
    public static void centralizaInternalFrame(JInternalFrame frame,Dimension desktopSize) {
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }
    

    public Icon icone(String caminho){
        Icon img = new ImageIcon(new ImageIcon(getClass().getResource(caminho)).getImage()
        .getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
        
        return img;
    }    

    /**
     *
     * @param type
     * "confirma" ->
     * "permissao" ->
     * "erro" ->
     * "sucesso" ->
     * "informacao"
     * @param message
     * Caso informe uma string vazia (""), atribui-se uma mensagem padrão:
     * "confirma" -> Tem Certeza??
     * "permissao" -> Você não tem permissão de acesso à este recurso.
     * "erro" -> Você não tem permissão de acesso à este recurso.
     * "sucesso" -> Operação bem Sucedida!
     * "informacao" -> null
     * @param frame
     * @return
     * "confirma" -> YES == TRUE, NO == FALSE
     * "permissao" -> TRUE
     * "erro" -> TRUE
     * "sucesso" -> TRUE
     */
    public boolean abrirJOptionPane(String type, String message,JInternalFrame frame){
        boolean result = true;
        switch(type){
            case "confirma":
                if(message.length() == 0){
                    message = "Tem Certeza??";
                }
                result = JOptionPane.showInternalConfirmDialog(frame, message, "Confirmação", JOptionPane.YES_NO_OPTION, 0, icone("/question.png")) == JOptionPane.YES_OPTION;
                break;
            case "permissao":
                if(message.length() == 0){
                    message = "Você não tem permissão de acesso à este recurso.";
                }
                JOptionPane.showMessageDialog(frame, message, "Permissão negada", JOptionPane.PLAIN_MESSAGE, icone("/no-permission.png"));
                break;
            case "erro":
                if(message.length() == 0){
                    message = "Ocorreu um erro na operação.";
                }
                JOptionPane.showMessageDialog(frame, message, "Oops!", JOptionPane.PLAIN_MESSAGE, icone("/error.png"));
                break;
            case "sucesso":
                if(message.length() == 0){
                    message = "Operação bem Sucedida!";
                }
                JOptionPane.showMessageDialog(frame, message, "Sucesso!", JOptionPane.PLAIN_MESSAGE, icone("/ok-success.png"));
                break;
            case "informacao":
                JOptionPane.showMessageDialog(frame, message,"Informação", JOptionPane.INFORMATION_MESSAGE,icone("/info.png"));
        }

        return result;
    }
}
