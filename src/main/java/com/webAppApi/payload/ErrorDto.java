package com.webAppApi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ErrorDto {
    private String error;
    private Date date;
    private String url;

    public ErrorDto() {
    }

    public ErrorDto(String error, Date date, String url) {
        this.error = error;
        this.date = date;
        this.url = url;
    }
}
