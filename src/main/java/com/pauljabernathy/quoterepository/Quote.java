/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pauljabernathy.quoterepository;

/**
 *
 * @author paul
 */
public class Quote {
    private int id;
    private int speaker;
    private String text;
    private String reference;

    public Quote() {
	
    }
    
    public Quote(int id, int speaker, String text, String reference) {
	this.id = id;
	this.speaker = speaker;
	this.text = text;
	this.reference = reference;
    }
    
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getSpeaker() {
	return speaker;
    }

    public void setSpeaker(int speaker) {
	this.speaker = speaker;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getReference() {
	return reference;
    }

    public void setReference(String reference) {
	this.reference = reference;
    }
    
    @Override
    public String toString() {
	return new StringBuilder().append(this.text).append(" - ").append(this.speaker).toString();
    }
}
