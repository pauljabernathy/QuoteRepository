/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pauljabernathy.quoterepository.db;

import com.pauljabernathy.quoterepository.Application;
import com.pauljabernathy.quoterepository.Quote;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

//TODO:  Add Hibernate.

/**
 *
 * @author paul
 */
public class QuoteRepository {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    static JdbcTemplate jdbcTemplate;
    
    //TODO: Figure out how to get the correct datasource injected!
    static {
	jdbcTemplate = jdbcTemplate();
    }
    
    private static DataSource dataSource() {
	
	String url = "";
	String parameters = "";
	String userName = "";
	String password = "";
	Properties props = new Properties();
	try {
	    props = getConnectionProperties();
	} catch(IOException e) {
	    log.error("Could not load database properties file!");
	}
	
	BasicDataSource ds = new BasicDataSource();
	ds.setDriverClassName(props.getProperty("driver"));
	parameters = props.getProperty("parameters");
	url = "jdbc:mysql://mysqlcluster27.registeredsite.com:3306/archimedes";
	StringBuilder urlb = new StringBuilder(props.getProperty("url")).append(":").append(props.getProperty("port"))
	    .append("/").append(props.getProperty("schema"));
	ds.setUrl(urlb.toString() + parameters);
	ds.setUsername(props.getProperty("userName"));
	ds.setPassword(props.getProperty("password"));
	return ds;
    }
    
    protected static Properties getConnectionProperties() throws IOException {
	Properties props = new Properties();
	FileInputStream in = new FileInputStream("db.properties");
	props.load(in);
	in.close();
	return props;
    }
    
    private static JdbcTemplate jdbcTemplate() {
	return new JdbcTemplate(dataSource());
    }
    
    public static void doSomethingWithQuotes() {
	
	jdbcTemplate = jdbcTemplate();
	//this.doCustomersDBFromDemo();
	
	//jdbcTemplate.queryForList("select * from archimedes.quotes").forEach(row -> log.info(row.toString()));
	
	/*jdbcTemplate.query("select * from archimedes.quotes", (rs, rowNum) -> new Quote(rs.getInt("id"), rs.getInt("speaker"), rs.getString("text"), rs.getString("reference")))
	    .forEach(row -> log.info(row.toString()));*/
	QuoteRepository.getAllQuotes().stream().forEach(quote -> log.info(quote.toString()));
    }
    
    public static ArrayList<Quote> getAllQuotes() {
	ArrayList<Quote> response = new ArrayList<>();
	
	jdbcTemplate.query("select * from archimedes.quotes", (rs, rowNum) -> new Quote(rs.getInt("id"), rs.getInt("speaker"), rs.getString("text"), rs.getString("reference")))
	    .forEach(row -> response.add(row));
	return response;
    }
    
    public static int addQuote(Quote quote) {
	
	String sql = "insert into archimedes.quotes values(?, ?, ?, ?)";
	return jdbcTemplate.update(sql, new Object[] { getNextId(), quote.getSpeaker(), quote.getText(), quote.getReference() });
	//sql = "insert into archimedes.quotes values(3, 2, 'new quote', 'not sure')";
	//return jdbcTemplate.update(sql);
    }
    
    public static int getMaxId() {
	String sql = "select max(id) from archimedes.quotes";
	List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql);
	if(rs.isEmpty()) {
	    //TODO: what to do here?
	    return 0;
	}
	return Integer.parseInt(rs.get(0).get("max(id)").toString());
    }
    
    private static int getNextId() {
	return getMaxId() + 1;
    }
}
