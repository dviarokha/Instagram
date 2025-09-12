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
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserStaxReader {
    public static List<User> readUsers(String fileName) throws XMLStreamException {
        List<User> users = new ArrayList<>();
        User currentUser = null;
        String elementContent = null;
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = null;

        try (
                InputStream InputStream = new FileInputStream(fileName)) {
                reader = factory.createXMLEventReader(InputStream);

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
                            addUserField(currentUser, endElementName, elementContent);
                        }
                        if ("user".equals(endElementName)) {
                            users.add(currentUser);
                        }
                        elementContent = null;
                        break;
                }
            }
        } catch (IOException e) {
            throw new XMLStreamException("Error while reading file" + fileName, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    throw new XMLStreamException("Error closing XML reader", e);
                }
            }
        }
        return users;
    }

    private static void addUserField(User user, String fieldName, String fieldValue) {
        switch (fieldName) {
            case "id":
                user.setId(Long.parseLong(fieldValue));
                break;
            case "firstName":
                user.setFirstName(fieldValue);
                break;
            case "lastName":
                user.setLastName(fieldValue);
                break;
            case "dateOfBirth":
                user.setDateOfBirth(LocalDate.parse(fieldValue));
                break;
            case "emailAddress":
                user.setEmailAddress(fieldValue);
                break;
            case "phoneNumber":
                user.setPhoneNumber(fieldValue);
                break;
            case "userTypeId":
                user.setUserTypeId(Long.parseLong(fieldValue));
                break;
            case "profileId":
                user.setProfileId(Long.parseLong(fieldValue));
                break;
        }
    }
}


