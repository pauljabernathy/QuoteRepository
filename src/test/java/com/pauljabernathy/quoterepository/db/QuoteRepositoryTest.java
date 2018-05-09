/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pauljabernathy.quoterepository.db;

import com.pauljabernathy.quoterepository.Application;
import com.pauljabernathy.quoterepository.Quote;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author paul
 */
public class QuoteRepositoryTest {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public QuoteRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getGetConnectionProperties() {
	try {
	    Properties props = QuoteRepository.getConnectionProperties();
	    log.info(props.toString());
	} catch(IOException e) {
	    fail("got " + e.getClass() + ":  " + e.getMessage());
	}
	
    }
    
    @Test
    public void testGetAllQuotes() {
	List<Quote> quotes = QuoteRepository.getAllQuotes();
	assertNotNull(quotes);
    }
    
    //@Test
    public void testAddOneQuote() {
	List<Quote> quotes = QuoteRepository.getAllQuotes();
	
	int numQuotes = quotes.size();
	Quote newQuote = new Quote(3, 2, "new quote", "not sure");
	int result = QuoteRepository.addQuote(newQuote);
	log.info("result = " + result);
	
	int newNumQuotes = QuoteRepository.getAllQuotes().size();
	assertEquals(numQuotes + 1, newNumQuotes);
    }
    
    @Test
    public void testGetMaxId() {
	int max = QuoteRepository.getMaxId();
	log.info("max = " + max);
	assert(max > -1);
    }
  
}
