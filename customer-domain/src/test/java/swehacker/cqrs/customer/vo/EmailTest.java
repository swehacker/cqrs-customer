package swehacker.cqrs.customer.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Nested
    class ValidEmails {
        @DisplayName("GIVEN a valid email WHEN calling new email THEN no exception should be thrown")
        @ParameterizedTest(name = "Email {0} is supported.")
        @ValueSource(strings = { "mysite@ourearth.com", "my.ownsite@ourearth.org", "mysite@you.me.net"})
        void testValidEmail(String email) {
            assertDoesNotThrow(() -> new Email(email));
        }
    }


    @Nested
    class InvalidEmails {

        @Test
        @DisplayName("GIVEN email without the @ character WHEN creating new email THEN throw an acception")
        void testMissingAtCharacter() {
            assertThrows(IllegalArgumentException.class, () -> new Email("mysite.ourearth.com"));
        }

        @Test
        @DisplayName("GIVEN Top Level domain start with dot WHEN creating new email THEN throw an acception")
        void testTldStartsWithADot() {
            assertThrows(IllegalArgumentException.class, () -> new Email("mysite@.com.my"));
        }

        @Test
        @DisplayName("GIVEN .b is not a valid tld WHEN creating new email THEN throw an acception")
        void testDotBIsNotAValidTld() {
            assertThrows(IllegalArgumentException.class, () -> new Email("mysite123@gmail.b"));
        }

        @Test
        @DisplayName("GIVEN an email should not be start with a . WHEN creating new email THEN throw an acception")
        void testEmailStartingWithDotCharacter() {
            assertThrows(IllegalArgumentException.class, () -> new Email(".mysite@mysite.org"));
        }

        @Test
        @DisplayName("GIVEN character, digit, underscore, and dash in address WHEN creating new email THEN throw an acception")
        void testInvalidCharacters() {
            assertThrows(IllegalArgumentException.class, () -> new Email("mysite()*@gmail.com"));
        }

        @Test
        @DisplayName("GIVEN double dots in address WHEN creating new email THEN throw an acception")
        void testDoubleDotsIsNotAllowed() {
            assertThrows(IllegalArgumentException.class, () -> new Email("mysite..1234@yahoo.com"));
        }
    }
}