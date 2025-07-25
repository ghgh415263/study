package com.example.study.unit;

import com.example.study.webcommon.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/custom-exception")
    public String throwCustom() {
        throw new CustomException("Custom error", HttpStatus.CONFLICT) {};
    }

    @GetMapping("/general-exception")
    public String throwGeneral() {
        throw new RuntimeException("Unexpected");
    }
}
