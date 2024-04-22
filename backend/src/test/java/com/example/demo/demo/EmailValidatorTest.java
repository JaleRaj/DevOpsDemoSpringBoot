package com.example.demo.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EmailValidatorTest {

    @Test
    public void testEmailValidationWithCorrectFormat() {
        // Angenommen, dies ist eine E-Mail mit korrektem Format.
        String validEmail = "test@example.com";
        assertTrue(EmailValidator.isValidEmail(validEmail),
                   "Die E-Mail-Adresse sollte als gültig anerkannt werden.");
    }

    @Test
    public void testEmailValidationWithMissingAtSymbol() {
        // Eine E-Mail-Adresse ohne "@"-Zeichen sollte als ungültig erkannt werden.
        String emailWithoutAt = "testexample.com";
        assertFalse(EmailValidator.isValidEmail(emailWithoutAt),
                    "Die E-Mail-Adresse ohne '@' sollte als ungültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithMultipleAtSymbols() {
        // Eine E-Mail-Adresse mit mehreren "@"-Zeichen sollte als ungültig erkannt werden.
        String invalidEmail = "test@@example.com";
        assertFalse(EmailValidator.isValidEmail(invalidEmail),
                    "Die E-Mail-Adresse mit mehreren '@' sollte als ungültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithNoLocalPart() {
        // Eine E-Mail-Adresse ohne lokalen Teil vor dem "@" sollte als ungültig erkannt werden.
        String invalidEmail = "@example.com";
        assertFalse(EmailValidator.isValidEmail(invalidEmail),
                    "Die E-Mail-Adresse ohne lokalen Teil sollte als ungültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithNoDomain() {
        // Eine E-Mail-Adresse ohne Domain nach dem "@" sollte als ungültig erkannt werden.
        String invalidEmail = "test@";
        assertFalse(EmailValidator.isValidEmail(invalidEmail),
                    "Die E-Mail-Adresse ohne Domain sollte als ungültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithInvalidCharacters() {
        // Eine E-Mail-Adresse mit ungültigen Zeichen sollte als ungültig erkannt werden.
        // Beispiel für ungültige Zeichen: Leerzeichen, Kommas, Semikolons, etc.
        String invalidEmailWithSpace = "test @example.com";
        String invalidEmailWithComma = "test,email@example.com";
        String invalidEmailWithSemicolon = "test;email@example.com";

        assertFalse(EmailValidator.isValidEmail(invalidEmailWithSpace),
                    "Die E-Mail-Adresse mit einem Leerzeichen sollte als ungültig erkannt werden.");
        assertFalse(EmailValidator.isValidEmail(invalidEmailWithComma),
                    "Die E-Mail-Adresse mit einem Komma sollte als ungültig erkannt werden.");
        assertFalse(EmailValidator.isValidEmail(invalidEmailWithSemicolon),
                    "Die E-Mail-Adresse mit einem Semikolon sollte als ungültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithSubdomains() {
        // E-Mail-Adresse mit Subdomains sollte als gültig erkannt werden.
        String validEmail = "test.email@subdomain.example.com";
        assertTrue(EmailValidator.isValidEmail(validEmail),
                   "Die E-Mail-Adresse mit Subdomains sollte als gültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithTopLevelDomainOnly() {
        // Eine E-Mail-Adresse, die nur aus einem TLD besteht, sollte als ungültig erkannt werden.
        String invalidEmail = "test@.com";
        assertFalse(EmailValidator.isValidEmail(invalidEmail),
                    "Die E-Mail-Adresse, die nur aus einem TLD besteht, sollte als ungültig erkannt werden.");
    }

    @Test
    public void testEmailValidationWithIpAddress() {
        // Überprüfen, ob die Methode eine E-Mail mit einer IP-Adresse anstelle einer Domain als ungültig erkennt wird.
        String emailWithIpAddress = "user@[192.168.1.1]";
        
        assertFalse(EmailValidator.isValidEmail(emailWithIpAddress),
                    "Die E-Mail mit einer IP-Adresse anstelle einer Domain sollte als ungültig erkannt werden.");
    }

    @Test
    void testEmailValidationWithTrailingDot() {
        // Eine E-Mail-Adresse mit einem Punkt am Ende der Domain sollte als ungültig erkannt werden.
        String invalidEmail = "test@example.com.";
        assertFalse(EmailValidator.isValidEmail(invalidEmail),
                    "Die E-Mail-Adresse mit einem Punkt am Ende der Domain sollte als ungültig erkannt werden.");
    }
    
    @Test
    void testEmailValidationWithNull() {
        // Nullmitgeben
        String invalidEmail = null;
        assertFalse(EmailValidator.isValidEmail(invalidEmail),
                    "Null sollte als ungültig erkannt werden.");
    }
}
