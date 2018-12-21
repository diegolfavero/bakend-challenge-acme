package br.com.acme.payment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class PaymentAcme implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7538538990349639746L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "status")
	private String status;

	@Column(name = "numberCard")
	private Long numberCard;

	@Column(name = "paym_date")
	private Date paymDate;

	@OneToOne
	@JoinTable(name = "payment_order", joinColumns = { @JoinColumn(name = "id_payment") }, inverseJoinColumns = {
			@JoinColumn(name = "id_order") })
	private OrderAcme order;

	public PaymentAcme(Integer id, String status, Long numberCard, Date paymDate) {
		super();
		this.id = id;
		this.status = status;
		this.numberCard = numberCard;
		this.paymDate = paymDate;
	}

	public PaymentAcme() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(Long numberCard) {
		this.numberCard = numberCard;
	}

	public Date getPaymDate() {
		return paymDate;
	}

	public void setPaymDate(Date paymDate) {
		this.paymDate = paymDate;
	}

	public OrderAcme getOrder() {
		return order;
	}

	public void setOrder(OrderAcme order) {
		this.order = order;
	}

}
