package Util;

import java.awt.Dimension;
import static java.lang.Integer.parseInt;
import java.util.Calendar;
import javax.swing.JInternalFrame;

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
    public static String getStringDateFromCalendar(Calendar aux) {
        String dia = String.valueOf(aux.get(Calendar.DAY_OF_MONTH)).length() == 1?
                "0" + String.valueOf(aux.get(Calendar.DAY_OF_MONTH)):
                String.valueOf(aux.get(Calendar.DAY_OF_MONTH));
  
        String mes = String.valueOf(aux.get(Calendar.MONTH)).length() == 1?
                "0" + String.valueOf(aux.get(Calendar.MONTH)):
                String.valueOf(aux.get(Calendar.MONTH));
        
        String nascimento = "" +
            dia+"/"+
            mes+"/"+
            aux.get(Calendar.YEAR);
        return nascimento;
    }
    public static Calendar getCalendarDateFromString(String aux) {
        Calendar nascimento = Calendar.getInstance();
        nascimento.set(
            parseInt(aux.split("/",3)[2]),
            parseInt(aux.split("/",3)[1]),
            parseInt(aux.split("/",3)[0])
        );
        
        return nascimento;
    }
    public static void centralizaInternalFrame(JInternalFrame frame,Dimension desktopSize) {
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }
    
}
