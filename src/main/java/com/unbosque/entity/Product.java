package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="buy_price")
	private int buyPrice;

	@Column(name="cat_id")
	private int catId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="has_iva")
	private byte hasIva;

	@Column(name="maximum_stock")
	private int maximumStock;

	@Column(name="minimum_stock")
	private int minimumStock;

	@Column(name="product_desc")
	private String productDesc;

	@Column(name="product_ref")
	private String productRef;

	@Column(name="product_status")
	private byte productStatus;

	@Column(name="product_stock")
	private int productStock;

	@Column(name="sell_price")
	private int sellPrice;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBuyPrice() {
		return this.buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getCatId() {
		return this.catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public byte getHasIva() {
		return this.hasIva;
	}

	public void setHasIva(byte hasIva) {
		this.hasIva = hasIva;
	}

	public int getMaximumStock() {
		return this.maximumStock;
	}

	public void setMaximumStock(int maximumStock) {
		this.maximumStock = maximumStock;
	}

	public int getMinimumStock() {
		return this.minimumStock;
	}

	public void setMinimumStock(int minimumStock) {
		this.minimumStock = minimumStock;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductRef() {
		return this.productRef;
	}

	public void setProductRef(String productRef) {
		this.productRef = productRef;
	}

	public byte getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(byte productStatus) {
		this.productStatus = productStatus;
	}

	public int getProductStock() {
		return this.productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

}