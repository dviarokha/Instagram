package com.solvd.instagram.parser;

import com.solvd.instagram.models.User;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserStaxReader {
    public static List<User> readUsers(String fileName) throws FileNotFoundException, XMLStreamException {
        List<User> users = new ArrayList<>();
        User currentUser = null;
        String elementContent = null;
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(fileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            switch (event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    currentElement = startElement.getName().getLocalPart();

                    if ("user".equals(currentElement)) {
                        currentUser = new User();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (!characters.isWhiteSpace()) {
                        elementContent = characters.getData();
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    String endElementName = endElement.getName().getLocalPart();

                    if (currentUser != null && elementContent != null) {
                        switch (endElementName) {
                            case "id":
                                currentUser.setId(Long.parseLong(elementContent));
                                break;
                            case "firstName":
                                currentUser.setFirstName(elementContent);
                                break;
                            case "lastName":
                                currentUser.setLastName(elementContent);
                                break;
                            case "dateOfBirth":
                                currentUser.setDateOfBirth(LocalDate.parse(elementContent));
                                break;
                            case "emailAddress":
                                currentUser.setEmailAddress(elementContent);
                                break;
                            case "phoneNumber":
                                currentUser.setPhoneNumber(elementContent);
                                break;
                            case "userTypeId":
                                currentUser.setUserTypeId(Long.parseLong(elementContent));
                                break;
                            case "profileId":
                                currentUser.setProfileId(Long.parseLong(elementContent));
                                break;

                        }
                    }
                    if ("user".equals(endElementName)) {
                        users.add(currentUser);
                    }
            }
        }
        reader.close();
        return users;

    }
}


