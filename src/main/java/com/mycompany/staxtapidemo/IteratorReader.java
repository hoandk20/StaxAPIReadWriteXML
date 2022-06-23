/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staxtapidemo;

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
        List<User> users = getAllUsers(new File("src/main/java/user.xml"));
        for (User user: users) {
            System.out.println(user.toString());
        }
    }
    
    public static List<User> getAllUsers (File file) {
        List<User> users = new ArrayList<>();
        User tmpUser = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(file));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String event_name = startElement.getName().toString();
                    if ("user".equals(event_name)) {
                        tmpUser = new User();
                        Attribute uid = startElement.getAttributeByName(new QName("uid"));
                        tmpUser.setUid(uid.getValue());         
                    } 
                    
                    if ("username".equals(event_name)) {
                        tmpUser.setUsername(reader.getElementText());
                    }
                    if ("password".equals(event_name)) {
                        tmpUser.setPassword(reader.getElementText());
                    }
                    if ("role".equals(event_name)) {
                        tmpUser.setRole(reader.getElementText());
                    }
                    if ("email".equals(event_name)) {
                        tmpUser.setEmail(reader.getElementText());
                    } 
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String end_tag = endElement.getName().toString();
                    if ("user".equals(end_tag)) {
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
