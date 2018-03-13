package org.pharmacy.pharmeasy.dao;

import java.util.List;

import org.pharmacy.pharmeasy.model.Prescription;

public interface PrescriptionDao {

	public Prescription retrievePrescription(Integer prescriptionId);

	public Prescription createPrescription(Prescription prescription);

	public List<Prescription> retrievePrescriptionByUserId(Integer userId);

}
