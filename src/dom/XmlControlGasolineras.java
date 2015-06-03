/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dom;

import Datos.Gasolineras;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gamba
 */
public class XmlControlGasolineras extends XmlControlDOM{
    File file = null;
	private Gasolineras gasolineras = null;

	public XmlControlGasolineras() {
		super();
		gasolineras = new Gasolineras();
	}

	public XmlControlGasolineras(Gasolineras gasolineras) {
		super();
		this.gasolineras = gasolineras;
	}

	public XmlControlGasolineras(File file, Gasolineras gasolineras) {
		super();
		this.file = file;
		this.gasolineras = gasolineras;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Gasolineras getGasolineras() {
		return gasolineras;
	}

	public void setGasolineras(Gasolineras gasolineras) {
		this.gasolineras = gasolineras;
	}
	
	public Document recupera(File file) {
		Document doc = null;
		try {
			if (this.getFile()!=null) {
				doc = this.instanciarDocument(this.getFile());
			}else
				doc= this.instanciarDocument(file);
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public Gasolineras llegir(Document doc) {
		Element arrel = doc.getDocumentElement();
		
		NodeList nList = arrel.getChildNodes();
		gasolineras.clear();
		
		for (int i = 0; i < nList.getLength(); i++) {
			if (nList.item(i).getNodeType()==Node.ELEMENT_NODE) {
				gasolineras.add(XmlControlGasolinera.llegir((Element) nList.item(i)));
			}
			
		}
		
		return this.getGasolineras();
	}
	
	public void escriureGasolineras(Document doc){
		Element eGasolineras;
		
		eGasolineras = doc.createElement("gasolineras");
		doc.appendChild(eGasolineras);
		
		for(int i=0; i<gasolineras.size(); i++){
			XmlControlGasolinera.escriure(gasolineras.get(i), doc, eGasolineras);
		}
	}
}
