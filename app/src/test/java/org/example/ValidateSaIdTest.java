package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {
    @Test
    public void testValidIds() {
        assertTrue(ValidateSaId.isIdNumberValid("2001014800086"));
        assertTrue(ValidateSaId.isIdNumberValid("2909035800085"));
    }

    @Test
    public void testInvalidLength() {
        assertFalse(ValidateSaId.isIdNumberValid("200101480008")); // too short
        assertFalse(ValidateSaId.isIdNumberValid("20010148000866")); // too long
    }

    @Test
    public void testNonDigitCharacters() {
        assertFalse(ValidateSaId.isIdNumberValid("20010148000A6"));
        assertFalse(ValidateSaId.isIdNumberValid("abcdefghijklm"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(ValidateSaId.isIdNumberValid("2013014800086")); // invalid month 13
        assertFalse(ValidateSaId.isIdNumberValid("2001324800086")); // invalid day 32
    }

    @Test
    public void testInvalidCitizenship() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800089")); // citizenship not 0 or 1
    }

    @Test
    public void testInvalidLuhn() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800080")); // invalid Luhn checksum
    }
}
