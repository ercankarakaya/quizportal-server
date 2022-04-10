package com.ercan.dtos.responses;

import com.ercan.enums.ResponseStatusEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response {
    String message;
    ResponseStatusEnum status;
    Object data;

    public Response(String message, ResponseStatusEnum status) {
        this.message = message;
        this.status = status;
    }

}