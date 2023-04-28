package com.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.mockito.Mock;


public class StockManagementTests {
    @Test
    public void testCanGetACorrectLocatorCode(){

        ExternalISBNDataService testWebService= new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };


        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locaterCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locaterCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){
       ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
       ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

       when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locaterCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("0140177396");
        verify(webService, times(0)).lookup(anyString());


    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){

        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class);


        when(databaseService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locaterCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("0140177396");
        verify(webService, times(1)).lookup("0140177396");
    }
}
