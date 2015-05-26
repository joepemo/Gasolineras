/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;
import org.apache.poi.hssf.usermodel.*;
import org.w3c.dom.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *Exigencias del programa: poner un subn¡vel más, recoger en sax y guardar en hibernate
 * @author Evaristo
 */
public class ExcelToXml {
   
   public void generateXML(File excelFile) {
      try { //Inicializamos el XML
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document document = builder.newDocument();
         
         Element rootElement = document.createElement("gasolineras");
         document.appendChild(rootElement);
            //Creamos el elemento principal
         InputStream input = new FileInputStream(excelFile);
         HSSFWorkbook workbook = new HSSFWorkbook(input);
         HSSFSheet spreadsheet = workbook.getSheetAt(0);

         for (int i = 5; i <= spreadsheet.getLastRowNum(); i++) {
         Element stmtElement1 = document.createElement("gasolinera");
         rootElement.appendChild(stmtElement1);
         
         Attr attr = document.createAttribute("id");
	 attr.setValue(Integer.toString(i-4));
	 stmtElement1.setAttributeNode(attr);
         
         HSSFRow filaActiva = spreadsheet.getRow(i);
            //Añadiendo los elementos
         
         Element rotulo = document.createElement("rotulo");
         stmtElement1.appendChild(rotulo);

         rotulo.appendChild(document.createTextNode(filaActiva.getCell(18).getStringCellValue()));
         
         Element provincia = document.createElement("provincia");
         stmtElement1.appendChild(provincia);

         provincia.appendChild(document.createTextNode(filaActiva.getCell(0).getStringCellValue()));
         
         
         Element localidad = document.createElement("localidad");
         stmtElement1.appendChild(localidad);

         localidad.appendChild(document.createTextNode(filaActiva.getCell(3).getStringCellValue()));
         
         Element cp = document.createElement("codigoPostal");
         stmtElement1.appendChild(cp);

         cp.appendChild(document.createTextNode(filaActiva.getCell(4).getStringCellValue()));
         
         Element direccion = document.createElement("direccion");
         stmtElement1.appendChild(direccion);

         direccion.appendChild(document.createTextNode(filaActiva.getCell(5).getStringCellValue()));
         
         Element gasolinas = document.createElement("combustibles");
         stmtElement1.appendChild(gasolinas);
         
         Element gasolina95 = document.createElement("Gasolina95");
         gasolinas.appendChild(gasolina95);

         gasolina95.appendChild(document.createTextNode(filaActiva.getCell(9).getStringCellValue()));
         
         Element gasoleo = document.createElement("gasoleo");
         gasolinas.appendChild(gasoleo);

         gasoleo.appendChild(document.createTextNode(filaActiva.getCell(10).getStringCellValue()));         
         
         Element horario = document.createElement("horario");
         stmtElement1.appendChild(horario);

         horario.appendChild(document.createTextNode(filaActiva.getCell(21).getStringCellValue()));
                   
         }
                
         TransformerFactory tFactory = TransformerFactory.newInstance();

         Transformer transformer = tFactory.newTransformer();
            //Add indentation to output
         transformer.setOutputProperty
         (OutputKeys.INDENT, "yes");
         transformer.setOutputProperty(
            "{http://xml.apache.org/xslt}indent-amount", "2");

         DOMSource source = new DOMSource(document);
         StreamResult result = new StreamResult(new File("gasolineras.xml"));
         transformer.transform(source, result);
      } catch (IOException e) {
         System.out.println("IOException " + e.getMessage());
      } catch (ParserConfigurationException e) {
         System.out
            .println("ParserConfigurationException " + e.getMessage());
      } catch (TransformerConfigurationException e) {
         System.out.println("TransformerConfigurationException "+ e.getMessage());
      } catch (TransformerException e) {
         System.out.println("TransformerException " + e.getMessage());
      }
   }
}

