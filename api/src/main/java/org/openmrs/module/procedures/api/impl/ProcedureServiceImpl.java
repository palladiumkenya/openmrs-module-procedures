package org.openmrs.module.procedures.api.impl;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.EncounterProvider;
import org.openmrs.Obs;
import org.openmrs.api.EncounterService;
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
		List<Encounter> encounters = handleEncounter(procedure);
		procedure.setEncounters(encounters);
		return procedureDao.saveOrUpdate(procedure);
	}
	
	/**
	 * Extract the obs and encounters from the payload and persist
	 * 
	 * @param procedure
	 * @return
	 */
	private List<Encounter> handleEncounter(Procedure procedure) {
		if (procedure.getEncounters().isEmpty()) {
			return new ArrayList<>();
		}
		EncounterService service = Context.getEncounterService();
		List<Encounter> ret = new ArrayList<>();
		
		if (procedure.getEncounters() != null) {
			for (Encounter encounter : procedure.getEncounters()) {
				Encounter enc = new Encounter();
				enc.setEncounterDatetime(encounter.getEncounterDatetime());
				enc.setPatient(encounter.getPatient());
				enc.setEncounterType(encounter.getEncounterType());
				
				if (encounter.getEncounterProviders() != null) {
					Set<EncounterProvider> providers = new HashSet<>();
					for (EncounterProvider provider : encounter.getEncounterProviders()) {
						provider.setEncounter(enc);
						providers.add(provider);
					}
					enc.setEncounterProviders(providers);
				}
				
				if (encounter.getObs() != null) {
					Set<Obs> obset = new HashSet<>();
					for (Obs tobs : encounter.getObs()) {
						Obs obs = new Obs();
						obs.setPerson(encounter.getPatient());
						obs.setObsDatetime(encounter.getEncounterDatetime());
						obs.setConcept(tobs.getConcept());
						obs.setValueCoded(tobs.getValueCoded());
						obs.setEncounter(enc);
						obset.add(obs);
					}
					enc.setObs(obset);
				}
				
				service.saveEncounter(enc);
				ret.add(enc);
			}
		}
		return ret;
	}
}
