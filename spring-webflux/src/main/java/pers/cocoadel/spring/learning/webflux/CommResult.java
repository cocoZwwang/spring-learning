package pers.cocoadel.spring.learning.webflux;

import lombok.Data;

@Data
public class CommResult<T> {
    private String error;

    private String message;

    private T body;

}
