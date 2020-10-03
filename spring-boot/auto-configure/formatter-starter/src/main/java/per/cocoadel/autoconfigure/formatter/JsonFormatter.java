package per.cocoadel.autoconfigure.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json格式化
 */
public class JsonFormatter implements Formatter{

    private final ObjectMapper objectMapper;

    public JsonFormatter() {
        this.objectMapper = new ObjectMapper();
    }

    public JsonFormatter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String format(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("解析失败！");
        }
    }
}
