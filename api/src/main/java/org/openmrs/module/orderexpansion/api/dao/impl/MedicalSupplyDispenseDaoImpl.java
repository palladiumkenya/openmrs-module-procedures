package org.openmrs.module.orderexpansion.api.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.orderexpansion.api.dao.MedicalSupplyDispenseDao;
import org.openmrs.module.orderexpansion.api.model.MedicalSupplyDispense;

public class MedicalSupplyDispenseDaoImpl implements MedicalSupplyDispenseDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Optional<MedicalSupplyDispense> get(int id) {
		return Optional.empty();
	}
	
	@Override
	public Optional<MedicalSupplyDispense> getMedicalSupplyDispenseByUuid(String uuid) {
		Criteria criteria = getCurrentSession().createCriteria(MedicalSupplyDispense.class);
		return Optional.ofNullable((MedicalSupplyDispense) criteria.add(eq("uuid", uuid)).uniqueResult());
	}
	
	@Override
	public MedicalSupplyDispense saveOrUpdate(MedicalSupplyDispense medicalSupplyDispense) {
		getCurrentSession().saveOrUpdate(medicalSupplyDispense);
		return medicalSupplyDispense;
	}
	
}
