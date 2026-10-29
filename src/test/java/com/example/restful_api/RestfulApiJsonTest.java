package com.example.restful_api;

import com.example.restful_api.entity.CashCard;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class RestfulApiJsonTest {
    @Autowired
    private JacksonTester<CashCard[]> jsonList;

    @Autowired
    private JacksonTester<CashCard> json;

    private CashCard[] cashCards;

    @BeforeEach
    void setUp() {
        cashCards = Arrays.array(new CashCard(99L, 123.45, "kelvin"), new CashCard(100L, 1.00, "kelvin"), new CashCard(101L, 150.00, "kelvin"));
    }

    @Test
    public void cashCardSerialization() throws IOException {
        assertThat(jsonList.write(cashCards).equals("list.json"));
    }

    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
                [
                    {"id": 99, "amount": 123.45, "owner" : "kelvin"},
                    {"id": 100, "amount": 1.00, "owner" : "kelvin"},
                    {"id": 101, "amount": 150.00, "owner" : "kelvin"}
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(cashCards);
    }
}
