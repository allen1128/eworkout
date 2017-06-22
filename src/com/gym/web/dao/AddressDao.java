package com.gym.web.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
@Component("addressDao")
public class AddressDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(Address address) {
		session().save(address);
	}

	public User getAddress(int addressId) {
		Criteria crit = session().createCriteria(Address.class);
		crit.add(Restrictions.idEq(addressId));
		return (User) crit.uniqueResult();
	}
}
