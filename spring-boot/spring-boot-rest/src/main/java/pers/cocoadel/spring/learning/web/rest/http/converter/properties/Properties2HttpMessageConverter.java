package pers.cocoadel.spring.learning.web.rest.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

public class Properties2HttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public Properties2HttpMessageConverter() {
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        List<Charset> charsets = headers.getAcceptCharset();
        Charset charset = CollectionUtils.isEmpty(charsets) ? StandardCharsets.UTF_8 : charsets.get(0);
        OutputStream body = outputMessage.getBody();
        Writer writer = new OutputStreamWriter(body, charset);
        properties.store(writer, "From Properties2HttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        HttpHeaders headers = inputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = null;
        if (contentType != null) {
            charset = contentType.getCharset();
        }
        charset = charset == null ? StandardCharsets.UTF_8 : charset;
        InputStream inputStream = inputMessage.getBody();
        Reader reader = new InputStreamReader(inputStream);

        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
