package org.openmrs.module.procedures.api.dao.impl;

import static org.hibernate.criterion.Restrictions.eq;

import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.procedures.api.dao.ProcedureDao;
import org.openmrs.module.procedures.api.model.Procedure;

public class ProcedureDaoImpl implements ProcedureDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Optional<Procedure> get(int id) {
		return Optional.empty();
	}
	
	@Override
	public Optional<Procedure> getProcedureByUuid(String uuid) {
		Criteria criteria = getCurrentSession().createCriteria(Procedure.class);
		return Optional.ofNullable((Procedure) criteria.add(eq("uuid", uuid)).uniqueResult());
	}
	
	@Override
	public Procedure saveOrUpdate(Procedure procedure) {
		getCurrentSession().saveOrUpdate(procedure);
		return procedure;
	}
}
