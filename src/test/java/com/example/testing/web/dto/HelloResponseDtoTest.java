package com.example.testing.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest(){

    int amount = 1000;
    String name= "test";

    HelloResponseDto dto = new HelloResponseDto(name, amount);

    assertThat(dto.getName()).isEqualTo(name);
    assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
