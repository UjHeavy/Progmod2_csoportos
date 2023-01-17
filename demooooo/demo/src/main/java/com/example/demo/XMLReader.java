package com.example.demo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    public static final String filename = "D:\\demooooo\\demo\\src\\main\\resources\\messages.xml";
    public Document doc;

    List<Entity> listEntity= new ArrayList<>();

        {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            Document document = null;
            try {
                document = documentBuilder.parse(new File(filename));
                doc = document;
            } catch (SAXException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            document.getDocumentElement().normalize();
            NodeList list = document.getElementsByTagName("chat");

                for (int temp = 0; temp < list.getLength(); temp++) {

                    Node node = list.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String id = element.getElementsByTagName("id").item(0).getTextContent();
                        String setter = element.getElementsByTagName("setter").item(0).getTextContent();
                        String getter = element.getElementsByTagName("getter").item(0).getTextContent();
                        String message = element.getElementsByTagName("message").item(0).getTextContent();
                        String status = element.getElementsByTagName("status").item(0).getTextContent();
                        Entity entity = new Entity();
                        entity.setSetter(String.valueOf(setter));
                        entity.setGetter(String.valueOf(getter));
                        entity.setMessage(String.valueOf(message));
                        entity.setStatus(MessageStatusEnum.valueOf(status));
                        listEntity.add(entity);
                    }
                }
        }
    public List<Entity> load() {
        return listEntity;
    }
   public void save(Entity entity) throws TransformerException {
            Element root = doc.getDocumentElement();
            Element cd = doc.createElement("chat");
            Element id = doc.createElement("id");
            Element setter = doc.createElement("setter");
            Element getter = doc.createElement("getter");
            Element message = doc.createElement("message");
            Element status = doc.createElement("status");
            id.appendChild(doc.createTextNode(String.valueOf(entity.getId())));
            setter.appendChild(doc.createTextNode(entity.getSetter()));
            getter.appendChild(doc.createTextNode(entity.getGetter()));
            message.appendChild(doc.createTextNode(entity.getMessage()));
            status.appendChild(doc.createTextNode(String.valueOf(entity.getStatus())));
            cd.appendChild(id);
            cd.appendChild(setter);
            cd.appendChild(getter);
            cd.appendChild(message);
            cd.appendChild(status);
            root.appendChild(cd);

       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = transformerFactory.newTransformer();
       DOMSource source = new DOMSource(doc);
       StreamResult result = new StreamResult(filename);
       transformer.transform(source, result);
   }


}

