package org.openmrs.module.procedures.api.impl;

import javax.transaction.Transactional;

import java.util.Optional;

import org.openmrs.module.procedures.api.ProcedureService;
import org.openmrs.module.procedures.api.dao.ProcedureDao;
import org.openmrs.module.procedures.api.model.Procedure;

@Transactional
public class ProcedureServiceImpl implements ProcedureService {
	
	private ProcedureDao procedureDao;
	
	public void setProcedureDao(ProcedureDao procedureDao) {
		this.procedureDao = procedureDao;
	}
	
	@Override
	public Optional<Procedure> getProcedureByUuid(String uuid) {
		return procedureDao.getProcedureByUuid(uuid);
	}
}
