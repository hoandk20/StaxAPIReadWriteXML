/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.staxtapidemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author loanbui
 */
public class CursorReader {

    public static void main(String[] args) {
        List<User> users = getAllUser();
        for (User user: users) {
            System.out.println(user.toString());
        }
    }

    public static List<User> getAllUser() {
        List<User> users = null;
        User currentUser = null;
        String currentText = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileReader fileReader;
        try {
            fileReader = new FileReader("src/main/java/user.xml");
            XMLStreamReader streamReader = factory.createXMLStreamReader(fileReader);
            while (streamReader.hasNext()) {
                int category = streamReader.next();
                switch (category) {
                    case XMLStreamConstants.START_ELEMENT:
                        String tagname = streamReader.getLocalName();
                        if ("users".equals(tagname)) {
                            users = new ArrayList<>();
                        }
                        if ("user".equals(tagname)) {
                            currentUser = new User();
                            currentUser.setUid(streamReader.getAttributeValue(0));
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        currentText = streamReader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        String endtag = streamReader.getLocalName();
                        switch (endtag) {
                            case "username":
                                if (currentUser != null) {
                                    currentUser.setUsername(currentText);
                                }
                                break;
                            case "password":
                                if (currentUser != null) {
                                    currentUser.setPassword(currentText);
                                }
                                break;
                            case "role":
                                if (currentUser != null) {
                                    currentUser.setRole(currentText);
                                }
                                break;
                            case "email":
                                if (currentUser != null) {
                                    currentUser.setEmail(currentText);
                                }
                                break;
                            case "user":
                                users.add(currentUser);
                                break;    
                        }
                        break;

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CursorReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(CursorReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
}
