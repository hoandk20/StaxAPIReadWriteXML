/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staxtapidemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author loanbui
 */
public class CursorWriter {
    public static void main(String[] args) {
        List<User> users = CursorReader.getAllUser();
        writeUsers2Xml(users);
    }
    
    public static void writeUsers2Xml (List<User> users) {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = factory.createXMLStreamWriter(new FileOutputStream("users_out.xml"));
            streamWriter.writeStartDocument("UTF-8", "2.0");
            streamWriter.writeCharacters("\n");
            streamWriter.writeStartElement("users");
            
            
            for (int i = 0; i < users.size(); i ++) {
                streamWriter.writeCharacters("\n\t");
                User user = users.get(i);
                streamWriter.writeStartElement("user");
                streamWriter.writeAttribute("uid", user.getUid());
                writeSimpleElement(streamWriter, "username", user.getUsername());
                writeSimpleElement(streamWriter, "password", user.getPassword());
                writeSimpleElement(streamWriter, "role", user.getRole());
                writeSimpleElement(streamWriter, "email", user.getEmail());
                streamWriter.writeCharacters("\n\t");
                streamWriter.writeEndElement();
            }
            streamWriter.writeCharacters("\n");
            streamWriter.writeEndElement();
            streamWriter.flush();
            streamWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CursorWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(CursorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeSimpleElement(XMLStreamWriter streamWriter, String tag, String content) {
        try {
            streamWriter.writeCharacters("\n\t\t");
            streamWriter.writeStartElement(tag);
            streamWriter.writeCharacters(content);
            streamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            Logger.getLogger(CursorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
