package org.pharmacy.pharmeasy.dao;

import java.util.ArrayList;

import org.pharmacy.pharmeasy.model.Prescription;

public interface PrescriptionDao {

	public Prescription retrievePrescription(Integer prescriptionId);

	public Prescription createPrescription(Prescription prescription);

	public ArrayList<Prescription> retrievePrescriptionByUserId(Integer userId);

	public ArrayList<Prescription> retrievePrescriptionByRequesterForUser(Integer userId, Integer requester);

}
