package com.nspaces.oss.busi.domain;

import java.io.Serializable;


/**
 * The persistent class for the game_card database table.
 * 
 */
public class GameCard implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String cardId;

	private String firstChar;

	private String name;

	private String price;

	private String priceDescEn;

	private String priceDescZh;

	private String priceId;

	private String type;

	public GameCard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getFirstChar() {
		return this.firstChar;
	}

	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceDescEn() {
		return this.priceDescEn;
	}

	public void setPriceDescEn(String priceDescEn) {
		this.priceDescEn = priceDescEn;
	}

	public String getPriceDescZh() {
		return this.priceDescZh;
	}

	public void setPriceDescZh(String priceDescZh) {
		this.priceDescZh = priceDescZh;
	}

	public String getPriceId() {
		return this.priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}