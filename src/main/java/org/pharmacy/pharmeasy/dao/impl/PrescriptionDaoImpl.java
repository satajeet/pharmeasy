package org.pharmacy.pharmeasy.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.pharmacy.pharmeasy.dao.PrescriptionDao;
import org.pharmacy.pharmeasy.model.Prescription;

public class PrescriptionDaoImpl implements PrescriptionDao {

	@Override
	public Prescription retrievePrescription(Integer prescriptionId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String qr = "select obj.prescriptionId,obj.userId,user.userName,obj.prescriptionName,obj.prescription,obj.medicalRecords from Prescription obj left join User user on obj.userId=user.userId where obj.prescriptionId=:prescriptionId";
		Query query = entityManager.createNativeQuery(qr.toUpperCase());
		query.setParameter("PRESCRIPTIONID", prescriptionId);
		try {
			Object[] obj = (Object[]) query.getResultList().get(0);
			Prescription prescription = new Prescription();
			prescription.setMedicalRecords((String) obj[5]);
			prescription.setPrescription((String) obj[4]);
			prescription.setPrescriptionId((Integer) obj[0]);
			prescription.setPrescriptionName((String) obj[3]);
			prescription.setUserId((Integer) obj[1]);
			prescription.setUserName((String) obj[2]);
			return prescription;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Prescription createPrescription(Prescription prescriptionObj) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query maxIdQuery = entityManager.createQuery("select max(obj.prescriptionId) from Prescription obj");
		Integer maxId = 0;
		try {
			maxId = (Integer) maxIdQuery.getSingleResult();
		} catch (Exception e) {
			maxId = 0;
		}
		if (maxId == null) {
			maxId = 0;
		}
		Integer prescriptionId = maxId + 1;
		prescriptionObj.setPrescriptionId(prescriptionId);
		try {
			entityManager.getTransaction().begin();
			if (!entityManager.contains(prescriptionObj)) {
				entityManager.persist(prescriptionObj);
				entityManager.flush();
			}
			entityManager.getTransaction().commit();
			return prescriptionObj;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Prescription> retrievePrescriptionByUserId(Integer userId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String qr = "select obj.prescriptionId,obj.userId,user.userName,obj.prescriptionName,obj.prescription,obj.medicalRecords from Prescription obj left join User user on obj.userId=user.userId where obj.userId=:userId";
		Query query = entityManager.createNativeQuery(qr.toUpperCase());
		query.setParameter("USERID", userId);
		try {
			ArrayList<Object[]> objLst = (ArrayList<Object[]>) query.getResultList();
			ArrayList<Prescription> prescriptions = new ArrayList<>();
			for (Object[] obj : objLst) {
				Prescription prescription = new Prescription();
				prescription.setMedicalRecords((String) obj[5]);
				prescription.setPrescription((String) obj[4]);
				prescription.setPrescriptionId((Integer) obj[0]);
				prescription.setPrescriptionName((String) obj[3]);
				prescription.setUserId((Integer) obj[1]);
				prescription.setUserName((String) obj[2]);
				prescriptions.add(prescription);
			}
			return prescriptions;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ArrayList<Prescription> retrievePrescriptionByRequesterForUser(Integer userId, Integer requesterId) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rootdb");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String qr = "select obj.prescriptionId,obj.userId,user.userName,obj.prescriptionName,obj.prescription,obj.medicalRecords from Prescription obj left join User user on obj.userId=user.userId where obj.userId=:userId and obj.requesterId=:requesterId";
		Query query = entityManager.createNativeQuery(qr.toUpperCase());
		query.setParameter("USERID", userId);
		query.setParameter("REQUESTERID", requesterId);
		try {
			ArrayList<Object[]> objLst = (ArrayList<Object[]>) query.getResultList();
			ArrayList<Prescription> prescriptions = new ArrayList<>();
			for (Object[] obj : objLst) {
				Prescription prescription = new Prescription();
				prescription.setMedicalRecords((String) obj[5]);
				prescription.setPrescription((String) obj[4]);
				prescription.setPrescriptionId((Integer) obj[0]);
				prescription.setPrescriptionName((String) obj[3]);
				prescription.setUserId((Integer) obj[1]);
				prescription.setUserName((String) obj[2]);
				prescriptions.add(prescription);
			}
			return prescriptions;
		} catch (Exception e) {
			return null;
		}
	}

}
