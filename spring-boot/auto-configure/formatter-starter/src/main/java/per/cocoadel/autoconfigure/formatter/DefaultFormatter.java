package per.cocoadel.autoconfigure.formatter;

public class DefaultFormatter implements Formatter{

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}
