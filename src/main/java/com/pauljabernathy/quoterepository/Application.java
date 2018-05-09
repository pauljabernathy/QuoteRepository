/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pauljabernathy.quoterepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 *
 * @author paul
 */
@SpringBootApplication
public class Application { //implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    //@Override
    public void run(String... strings) throws Exception {

        //this.doCustomersDBFromDemo();
	//this.doSomethingWithQuotes();
	com.pauljabernathy.quoterepository.db.QuoteRepository.doSomethingWithQuotes();
    }
    
    /*public void doCustomersDBFromDemo() {
	log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
    }*/
    
    /*public void doSomethingWithQuotes() {
	
	jdbcTemplate = jdbcTemplate();
	//this.doCustomersDBFromDemo();
	
	//jdbcTemplate.queryForList("select * from archimedes.quotes").forEach(row -> log.info(row.toString()));
	
	jdbcTemplate.query("select * from archimedes.quotes", (rs, rowNum) -> new Quote(rs.getInt("id"), rs.getInt("speaker"), rs.getString("text"), rs.getString("reference")))
	    .forEach(row -> log.info(row.toString()));
    }
    
    public DataSource dataSource() {

	BasicDataSource ds = new BasicDataSource();
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	String parameters = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String url = "jdbc:mysql://mysqlcluster27.registeredsite.com:3306/archimedes";
	ds.setUrl(url + parameters);
	ds.setUsername("archimedes");
	ds.setPassword("a5chImed");
	return ds;
    }
    
    public JdbcTemplate jdbcTemplate() {
	return new JdbcTemplate(dataSource());
    }*/
}