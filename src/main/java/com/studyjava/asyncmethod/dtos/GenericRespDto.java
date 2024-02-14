package com.studyjava.asyncmethod.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericRespDto {

    String requestName;
    HttpStatusCode status;
    Object responseBody;
    Class<?> responseType;
}
