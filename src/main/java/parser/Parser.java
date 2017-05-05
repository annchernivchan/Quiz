package parser;

public interface Parser {

    String to(Object object);
    <T> T from(String text, Class<T> objectClass);


}
