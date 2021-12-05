package com.ercan.response;

import com.ercan.enums.ResponseStatusEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


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