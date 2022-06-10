package com.g3.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private Integer status;
    private Date date;
    private String message;
    private List<String> details;

    public ErrorMessage(Integer status, Date date, String message) {
        this.status = status;
        this.date = date;
        this.message = message;
    }
}
