package com.example.study.unit;

import com.example.study.common.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers =  TestController.class)
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generalExceptionReturnsJsonString() throws Exception {
        MvcResult result = mockMvc.perform(get("/general-exception")).andReturn();
        String content = result.getResponse().getContentAsString();

        assertThat(content).contains("\"message\":\"서버 오류가 발생했습니다.\"");
        assertThat(content).contains("\"code\":\"INTERNAL_SERVER_ERROR\"");
        assertThat(content).contains("\"status\":500");
    }

    @Test
    void customExceptionReturnsJsonString() throws Exception {
        MvcResult result = mockMvc.perform(get("/custom-exception")).andReturn();
        String content = result.getResponse().getContentAsString();

        // JSON 문자열 직접 검증
        assertThat(content).contains("\"message\":\"Custom error\"");
        assertThat(content).contains("\"code\":\"\""); //TestController에서 익명클래스를 예외로 던져서 빈문자열임.
        assertThat(content).contains("\"status\":409");
    }

}
