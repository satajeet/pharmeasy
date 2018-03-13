package org.pharmacy.pharmeasy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.pharmacy.pharmeasy.dao.PrescriptionDao;
import org.pharmacy.pharmeasy.model.Prescription;

public class PrescriptionDaoImpl implements PrescriptionDao {

	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	@Transactional
	public Prescription createPrescription(Prescription prescriptionObj) {
		try {
			entityManager.getTransaction().begin();
			prescriptionObj = entityManager.merge(prescriptionObj);
			entityManager.flush();
			entityManager.getTransaction().commit();
			return prescriptionObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Prescription retrievePrescription(Integer prescriptionId) {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			String qr = "select obj.prescriptionId,obj.userId,user.userName,obj.prescriptionName,obj.prescription,obj.medicalRecords from Prescription obj left join User user on obj.userId=user.userId where obj.prescriptionId=:prescriptionId";
			Query query = entityManager.createNativeQuery(qr.toUpperCase());
			query.setParameter("PRESCRIPTIONID", prescriptionId);

			Object[] obj = (Object[]) query.getResultList().get(0);
			return mapPrescriptionObject(obj);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Prescription> retrievePrescriptionByUserId(Integer userId) {
		try {
			String qr = "select obj.prescriptionId,obj.userId,user.userName,obj.prescriptionName,obj.prescription,obj.medicalRecords from Prescription obj left join User user on obj.userId=user.userId where obj.userId=:userId";
			Query query = entityManager.createNativeQuery(qr.toUpperCase());
			query.setParameter("USERID", userId);

			List<Object[]> objLst = query.getResultList();
			List<Prescription> prescriptions = new ArrayList<>();
			for (Object[] obj : objLst) {
				prescriptions.add(mapPrescriptionObject(obj));
			}
			return prescriptions;
		} catch (Exception e) {
			return null;
		}
	}

	private Prescription mapPrescriptionObject(Object[] obj) {
		Prescription prescription = new Prescription();
		prescription.setMedicalRecords((String) obj[5]);
		prescription.setPrescription((String) obj[4]);
		prescription.setPrescriptionId((Integer) obj[0]);
		prescription.setPrescriptionName((String) obj[3]);
		prescription.setUserId((Integer) obj[1]);
		prescription.setUserName((String) obj[2]);
		return prescription;
	}

}
