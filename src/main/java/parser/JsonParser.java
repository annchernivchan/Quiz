package parser;

import com.google.gson.Gson;

public class JsonParser implements Parser {

    private static Gson gson = new Gson();

   @Override
    public String to(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T from(String text, Class<T> objectClass) {
        return gson.fromJson(text, objectClass);
    }
}
