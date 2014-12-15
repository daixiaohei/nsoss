package com.nspaces.oss.busi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the busi_cache_seq database table.
 * 
 */

public class BusiCacheSeq implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;


	private int busiId;

	private int busiSeq;

	private Date updatedAt;

	public BusiCacheSeq() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusiId() {
		return this.busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public int getBusiSeq() {
		return this.busiSeq;
	}

	public void setBusiSeq(int busiSeq) {
		this.busiSeq = busiSeq;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}