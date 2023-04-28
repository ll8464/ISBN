package com.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkAValidISBN10(){
        ValidateISBN validator = new ValidateISBN();
       boolean result = validator.checkISBN("0140449116");
       assertTrue("first value",result);
       result = validator.checkISBN("0140177396");
       assertTrue("second value",result);
    }

    @Test
    public void checkAValidISBN13(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781465449184");
        assertTrue("first value",result);
        result = validator.checkISBN("9781465478900");
        assertTrue("second value",result);

    }


    @Test
    public void ISBNNumbersEndingInAnXAreValid10(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

   @Test
    public void checkAnInvalidISBN10(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalidISBN13(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781465449186");
        assertFalse(result);

    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
       validator.checkISBN("123456789");
    }

    @Test(expected = NumberFormatException.class)
    public void ISBNIsANumber(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloworld");
    }


}
