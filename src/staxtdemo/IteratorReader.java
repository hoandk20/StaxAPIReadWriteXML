/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxtdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author loanbui
 */
public class IteratorReader {
    public static void main(String[] args) {
        List<Person> users = getAllUsers(new File("src\\staxtdemo\\file\\person.xml"));
        for (Person user: users) {
            System.out.println(user.toString());
        }
    }
    
    public static List<Person> getAllUsers (File file) {
        List<Person> users = new ArrayList<>();
        Person tmpUser = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String event_name = startElement.getName().toString();
                    
                    if ("person".equals(event_name)) {
                        tmpUser = new Person();
                         Attribute uid = startElement.getAttributeByName(new QName("id"));
                         Attribute photo = startElement.getAttributeByName(new QName("photo"));
                         
                        tmpUser.setId(uid.getValue());    
                         tmpUser.setPhoto(photo.getValue()); 
                    } 
                    
                    if ("given".equals(event_name)) {
                        tmpUser.setGiven(reader.getElementText());
                    }
                    if ("family".equals(event_name)) {
                        tmpUser.setFamily(reader.getElementText());
                    }
                    if ("email".equals(event_name)) {
                        tmpUser.setEmail(reader.getElementText());
                    }
                    if ("link".equals(event_name)) {
                        Attribute s = startElement.getAttributeByName(new QName("subordinates"));
                        Attribute s1 = startElement.getAttributeByName(new QName("manager"));
                        if(s!=null){
                            tmpUser.setSubordinates(s.getValue());
                        }else{
                            tmpUser.setSubordinates(s1.getValue());
                        }            
                    } 
                    if ("url".equals(event_name)) {
                         Attribute s1 = startElement.getAttributeByName(new QName("href"));
                          tmpUser.setHref(s1.getValue());
                    } 
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String end_tag = endElement.getName().toString();
                    if ("person".equals(end_tag)) {
                        users.add(tmpUser);
                        tmpUser = null;
                    }
                }
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IteratorReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(IteratorReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
