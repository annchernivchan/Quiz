package parser;

import entities.Question;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlParser implements Parser {

    @Override
    public String to(Object object) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Question.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(object, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    @Override
    public <T> T from(String text, Class<T> objectClass) {
        return JAXB.unmarshal(new StringReader(text), objectClass);
    }
}
