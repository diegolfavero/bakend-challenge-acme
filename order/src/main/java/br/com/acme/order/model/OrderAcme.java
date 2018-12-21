package br.com.acme.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class OrderAcme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 331053547347390218L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany
	@JoinTable(name = "order_order_item_acme", joinColumns = { @JoinColumn(name = "id_order") }, inverseJoinColumns = {
			@JoinColumn(name = "id_order_item") })
	private List<OrderItemAcme> orderItemAcme;
	
	@Column(name = "address")
	private String address;

	@Column(name = "confirm_date")
	private Date confirmDate;

	@Column(name = "status")
	private String status;

	public OrderAcme(Integer id, String address, Date confirmDate, String status) {
		super();
		this.id = id;
		this.address = address;
		this.confirmDate = confirmDate;
		this.status = status;
		this.orderItemAcme = new ArrayList<>();
	}

	public OrderAcme() {
		super();
		this.orderItemAcme = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItemAcme> getOrderItemAcme() {
		return orderItemAcme;
	}

	public void setOrderItemAcme(List<OrderItemAcme> orderItemAcme) {
		this.orderItemAcme = orderItemAcme;
	}

}
