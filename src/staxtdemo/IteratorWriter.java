/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staxtdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

/**
 *
 * @author loanbui
 */
public class IteratorWriter {
    public static void main(String[] args) {
        List<Person> users = IteratorReader.getAllUsers(new File("src\\staxtdemo\\file\\person.xml"));
        int i=1;
        for (Person user: users) {
            
            System.out.println("No:"+i+" "+user.toString());i++;
        }
        Person p = new Person();
        p.setId("jonathan.smith");
        p.setFamily("Smith");
        p.setGiven("Jonathan");
        p.setPhoto("personal-images/Jonathan.Smith.jpg");
        p.setSubordinates("robert.taylor helen.jackson michelle.taylor");
        p.setEmail("jonathan.smith@gmail.com");
        p.setHref("http://www.example.com/na/jonathan-smith.html");
        users.add(p);
        writeUsers2Xml(new File("src\\staxtdemo\\file\\person.xml"), users);
    }
    
    public static void writeUsers2Xml(File file, List<Person> users) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(file));
            
            XMLEventFactory ef = XMLEventFactory.newInstance();
            
            StartDocument s_document = ef.createStartDocument("UTF-8", "1.0");
            EndDocument e_document = ef.createEndDocument();
            
          
              
            
            StartElement s_personel = ef.createStartElement("", "", "personnel");
            EndElement e_personel = ef.createEndElement("", "", "personnel");
            
            StartElement s_person = ef.createStartElement("", "", "person");
            EndElement e_person = ef.createEndElement("", "", "person");
           
            
            StartElement s_name = ef.createStartElement("", "", "name");
            StartElement s_given = ef.createStartElement("", "", "given");
            EndElement e_given = ef.createEndElement("", "", "given");
            StartElement s_family = ef.createStartElement("", "", "family");
            EndElement e_family = ef.createEndElement("", "", "family");            
            EndElement e_name = ef.createEndElement("", "", "name");
            
            StartElement s_email = ef.createStartElement("", "", "email");
            EndElement e_email = ef.createEndElement("", "", "email");
            
            StartElement s_link = ef.createStartElement("", "", "link");
            EndElement e_link = ef.createEndElement("", "", "link");
            
            StartElement s_url = ef.createStartElement("", "", "url");
            EndElement e_url = ef.createEndElement("", "", "url");
            
            writer.add(s_document);
            
            writer.add(s_personel);
            for (Person user: users) {
                writer.add(s_person);
                Attribute id = ef.createAttribute("id", user.getId());
                Attribute photo = ef.createAttribute("photo", user.getPhoto());
                writer.add(id); writer.add(photo);
                
                writer.add(s_name);
                writer.add(s_given);
                writer.add(ef.createCharacters(user.getGiven()));
                writer.add(e_given);
                writer.add(s_family);
                writer.add(ef.createCharacters(user.getFamily()));
                writer.add(e_family);
                writer.add(e_name);
                
                writer.add(s_email);
                writer.add(ef.createCharacters(user.getEmail()));
                writer.add(e_email);
                
                writer.add(s_link);
                if(user.getSubordinates().length()<30){
                     Attribute link = ef.createAttribute("manager", user.getSubordinates());
                    writer.add(link);
                }else{
                    Attribute link = ef.createAttribute("subordinates", user.getSubordinates());
                    writer.add(link);
                }
               
                
                
                writer.add(e_link);
                
                writer.add(s_url);
                Attribute url = ef.createAttribute("href", user.getHref());
                writer.add(url);
                writer.add(e_url);
                
                writer.add(e_person);
            }
            
            writer.add(e_personel);
            writer.add(e_document);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IteratorWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(IteratorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
