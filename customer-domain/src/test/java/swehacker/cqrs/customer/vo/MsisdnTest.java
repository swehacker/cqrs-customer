package swehacker.cqrs.customer.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import swehacker.cqrs.customer.core.vo.Msisdn;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MsisdnTest {

    @Nested
    @DisplayName("GIVEN correct formatted MSISDN ")
    class ValidMsisdn {
        @ParameterizedTest(name = "WHEN calling new email with msisdn: {0} THEN no exception should be thrown")
        @ValueSource(strings = {"+46703000089", "46703000089", "0046703000089"})
        void testCorrectFormattedMsisdn(String msisdn) {
            assertDoesNotThrow(() -> new Msisdn(msisdn));
        }
    }

    @Nested
    @DisplayName("GIVEN incorrect msisdn")
    class InvalidMsisdn {
        @ParameterizedTest(name = "WHEN calling new Msisdn({0}) THEN throw IllegalArgumentException")
        @ValueSource(strings = {"", " ", "++46703000089", "A4670300089", "467030A0089", "*4670300089", "467030[0089"})
        void testIncorrectFormattedMsisdn(String msisdn) {
            assertThrows(IllegalArgumentException.class, () -> new Msisdn(msisdn));
        }
    }
}