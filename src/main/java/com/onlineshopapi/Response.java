package com.onlineshopapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String code;
    private String message;
    private boolean success;
    private Object data;
}
