package com.gym.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class FacilityDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(Facility facility) {
		session().save(facility);
	}

	public boolean exists(int id) {
		return getFacility(id) != null;
	}

	@SuppressWarnings("unchecked")
	public List<Facility> getAllFacilities() {
		return session().createQuery("from Facility").list();
	}

	public List<Equipment> getAllEqupiments(int facilityId) {
		EquipmentDao equipmentDao = new EquipmentDao();
		return equipmentDao.getAllEquipmentPerFacility(facilityId);
	}

	public Facility getFacility(int id) {
		Criteria crit = session().createCriteria(Facility.class);
		crit.add(Restrictions.idEq(id));
		return (Facility) crit.uniqueResult();
	}
}
