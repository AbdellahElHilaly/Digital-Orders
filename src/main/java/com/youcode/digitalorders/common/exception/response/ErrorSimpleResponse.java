package com.youcode.digitalorders.common.exception.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
public class ErrorSimpleResponse {
    private  LocalDateTime timestamp;
    private  String message;
    private List<String> details;
    private  String path;

}
