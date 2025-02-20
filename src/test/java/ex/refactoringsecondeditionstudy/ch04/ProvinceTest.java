package ex.refactoringsecondeditionstudy.ch04;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProvinceTest {
    private static final DataLoader DATA_LOADER = new DataLoader(
            ProvinceTest.class.getClassLoader(),
            new ObjectMapper()
    );

    private Province asia;

    @BeforeEach
    void setUp() {
        asia = new Province(DATA_LOADER.sampleProvinceData());
    }

    @Test
    void shortfall() {
        final int shortfall = asia.getShortfall();

        assertThat(shortfall).isEqualTo(5);
    }

    @Test
    void profit() {
        final int profit = asia.getProfit();

        assertThat(profit).isEqualTo(230);
    }

    @Test
    void changeProduction() {
        final Province noProducer = new Province(new ProvinceData("No Producer", new ArrayList<>(), 30, 20));

        // Assert
        SoftAssertions.assertSoftly(softly -> {
            assertThat(noProducer.getShortfall()).isEqualTo(30);
            assertThat(noProducer.getProfit()).isZero();
        });
    }
}
