package org.pharmacy.pharmeasy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "prescription")
public class Prescription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "PRESCRIPTIONID")
	private Integer prescriptionId;
	@Column(name = "USERID")
	private Integer userId;
	@Transient
	private String userName;
	@Column(name = "PRESCRIPTIONNAME")
	private String prescriptionName;
	@Column(name = "PRESCRIPTION")
	private String prescription;
	@Column(name = "MEDICALRECORDS")
	private String medicalRecords;

	public Integer getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public String getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(String medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
