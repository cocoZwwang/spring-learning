package per.cocoadel.autoconfigure.formatter;

/**
 * 格式化接口
 */
public interface Formatter {

    /**
     * 对内容进行格式化
     * @param object 格式化对象
     * @return 格式化后的内容
     */
    String format(Object object);
}
