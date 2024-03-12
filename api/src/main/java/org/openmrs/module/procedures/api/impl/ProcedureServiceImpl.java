package org.openmrs.module.procedures.api.impl;

import javax.transaction.Transactional;

import java.util.Optional;

import org.openmrs.api.ConditionService;
import org.openmrs.api.ObsService;
import org.openmrs.api.context.Context;
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
	
	@Override
	public Procedure saveOrUpdate(Procedure procedure) {
		handleProcedureComplications(procedure);
		handleProcedureResults(procedure);
		return procedureDao.saveOrUpdate(procedure);
	}
	
	private void handleProcedureComplications(Procedure procedure) {
		ConditionService service = Context.getConditionService();
		procedure.getComplications().forEach(p -> {
			if (p.getId() == null) {
				service.saveCondition(p);
			}
		});
	}
	
	private void handleProcedureResults(Procedure procedure) {
		ObsService service = Context.getObsService();
		procedure.getProcedureResults().forEach(ob -> {
			if (ob.getId() == null) {
				service.saveObs(ob, "");
			}
		});
	}
	
}
