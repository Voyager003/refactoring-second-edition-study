package ex.refactoringsecondeditionstudy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ex.refactoringsecondeditionstudy.ch01.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class StatementTest {
    private Statement statement;
    private ObjectMapper objectMapper;
    private Invoice invoice;
    private Plays plays;

    @BeforeEach
    void setUp() throws IOException {
        statement = new Statement();
        objectMapper = new ObjectMapper();

        invoice = loadInvoice();
        plays = loadPlays();
    }

    private Invoice loadInvoice() throws IOException {
        var resource = new ClassPathResource("json/invoices.json");
        Map<String, Object> invoiceMap = objectMapper.readValue(resource.getFile(), new TypeReference<>() {});

        String customer = (String) invoiceMap.get("customer");
        List<Map<String, Object>> performancesMap = (List<Map<String, Object>>) invoiceMap.get("performances");

        List<Performance> performances = performancesMap.stream()
                .map(perfMap -> new Performance(
                        (String) perfMap.get("playID"),
                        ((Number) perfMap.get("audience")).intValue()
                ))
                .toList();

        return new Invoice(customer, performances);
    }

    private Plays loadPlays() throws IOException {
        var resource = new ClassPathResource("json/plays.json");
        Map<String, Object> playsData = objectMapper.readValue(resource.getFile(), new TypeReference<>() {});

        Map<String, Map<String, String>> playsMap = (Map<String, Map<String, String>>) playsData.get("playMap");

        Map<String, Play> playMap = playsMap.entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new Play(
                                entry.getValue().get("name"),
                                PlayType.valueOf(entry.getValue().get("type").toUpperCase())
                        )
                ));

        return new Plays(playMap);
    }

    @Test
    @DisplayName("statement 메소드 실행 결과 출력")
    void printStatementResult() throws Exception {
        Invoice invoice = loadInvoice();
        Plays plays = loadPlays();

        String result = statement.statement(invoice, plays);

        System.out.println(result);
    }
}