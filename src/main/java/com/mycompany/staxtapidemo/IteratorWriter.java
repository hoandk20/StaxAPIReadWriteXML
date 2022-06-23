/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staxtapidemo;

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
        List<User> users = IteratorReader.getAllUsers(new File("src/main/java/user.xml"));
        writeUsers2Xml(new File("src/main/java/user_iterator_out.xml"), users);
    }
    
    public static void writeUsers2Xml(File file, List<User> users) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(file));
            
            XMLEventFactory ef = XMLEventFactory.newInstance();
            
            StartDocument s_document = ef.createStartDocument("UTF-8", "1.0");
            EndDocument e_document = ef.createEndDocument();
            
            StartElement s_users = ef.createStartElement("", "", "users");
            EndElement e_users = ef.createEndElement("", "", "users");
            
            StartElement s_user = ef.createStartElement("", "", "user");
            EndElement e_user = ef.createEndElement("", "", "user");
           
            
            StartElement s_username = ef.createStartElement("", "", "username");
            EndElement e_username = ef.createEndElement("", "", "username");
            
            StartElement s_pass = ef.createStartElement("", "", "password");
            EndElement e_pass = ef.createEndElement("", "", "password");
            
            StartElement s_role = ef.createStartElement("", "", "role");
            EndElement e_role = ef.createEndElement("", "", "role");
            
            StartElement s_email = ef.createStartElement("", "", "email");
            EndElement e_email = ef.createEndElement("", "", "email");
            
            writer.add(s_document);
            writer.add(s_users);
            for (User user: users) {
                writer.add(s_user);
                Attribute uid = ef.createAttribute("uid", user.getUid());
                writer.add(uid);
                
                writer.add(s_username);
                writer.add(ef.createCharacters(user.getUsername()));
                writer.add(e_username);
                
                writer.add(s_pass);
                writer.add(ef.createCharacters(user.getPassword()));
                writer.add(e_pass);
                
                writer.add(s_role);
                writer.add(ef.createCharacters(user.getRole()));
                writer.add(e_role);
                
                writer.add(s_email);
                writer.add(ef.createCharacters(user.getEmail()));
                writer.add(e_email);
                
                writer.add(e_user);
            }
            
            writer.add(e_users);
            writer.add(e_document);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IteratorWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(IteratorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
