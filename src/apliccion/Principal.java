/*Partir todas las clases en utilidades dom, sax, hibernate tipo la ioc ,
  lo que tengo hasta la fecha no sirve para nada, modular en todo lo posible
  el código
 */
package apliccion;

import java.io.File;
import utilidades.UtilidadesXls;
import java.io.IOException;
import utilidades.ExcelToXml;

/**
 *
 * @author Evaristo
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        UtilidadesXls desc = new UtilidadesXls();
        desc.DevuelveXls("http://geoportalgasolineras.es/descargarPreciosTodasEstaciones.do?tipoBusqueda=0");
        ExcelToXml excel = new ExcelToXml();
        File input = new File("precioGasolineras.xls");
        excel.generateXML(input);
        System.out.println("Archivo Xml creado correctamente");
    }
    
}
