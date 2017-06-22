package com.gym.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class EquipmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session(){
		return sessionFactory.getCurrentSession();
	}	
	
	@Transactional
	public void create(Equipment equipment) {
		session().save(equipment);
	}

	public boolean exists(int id) {
		return getEquipment(id) != null;
	}

	@SuppressWarnings("unchecked")
	public List<Equipment> getAllEquipmentPerFacility(int facilityId) {
		Criteria crit = session().createCriteria(Equipment.class);
		crit.add(Restrictions.eq("facility_id", facilityId));
		return crit.list();
	}

	public Equipment getEquipment(int id) {
		Criteria crit = session().createCriteria(Equipment.class);
		crit.add(Restrictions.idEq(id));
		return (Equipment) crit.uniqueResult();
	}
}
