package com.solvd.instagram.parser;

import com.solvd.instagram.models.User;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class UserStaxWriter {

    public static void writeUsers(List<User> users, String fileName) throws XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            XMLStreamWriter writer = factory.createXMLStreamWriter(outputStream, "UTF-8");
            try {
                writer.writeStartDocument("UTF-8", "1.0");
                writer.writeStartElement("users");

                for (User user : users) {
                    writer.writeStartElement("user");

                    writeElement(writer, "id", user.getId().toString());
                    writeElement(writer, "firstName", user.getFirstName().toString());
                    writeElement(writer, "lastName", user.getLastName().toString());
                    writeElement(writer, "dateOfBirth", user.getDateOfBirth().toString());
                    writeElement(writer, "emailAddress", user.getEmailAddress("").toString());
                    writeElement(writer, "phoneNumber", user.getPhoneNumber().toString());
                    if (user.getUserTypeId() != null) {
                        writeElement(writer, "userTypeId", user.getUserTypeId().toString());
                    }
                    if (user.getProfileId() != null) {
                        writeElement(writer, "profileId", user.getProfileId().toString());
                    }
                    writer.writeEndElement();
                }
                writer.writeEndElement();
                writer.writeEndDocument();
            } finally {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    throw new XMLStreamException(e);
                }
            }
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    public static void writeElement(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
        writer.writeStartElement(name);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }
}
