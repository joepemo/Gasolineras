/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Evaristo
 */
public class UtilidadesDOM {
    
	public static Document instanciarDocument() throws ParserConfigurationException{
		Document doc = null;
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		return doc;
	}
	
	public static Document instanciarDocument(File fXmlFile) throws SAXException, IOException, ParserConfigurationException {
		Document doc = null;
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc ;
		
	}
	
	public static void escriureDocumentA3TextXml(Document doc, File file) throws TransformerFactoryConfigurationError, TransformerException {
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		
		StreamResult result = new StreamResult(file);
		DOMSource soure = new DOMSource();
		trans.transform(soure, result);
	}
	
	public static String getValorEtiqueta(String etiqueta, Element element) {
		Node nValue = element.getElementsByTagName(etiqueta).item(0);
		return nValue.getChildNodes().item(0).getNodeValue();
	}
	
	public static Element getElementEtiqueta(String etiqueta, Element element) {
		return (Element) element.getElementsByTagName(etiqueta).item(0);
	}
}


