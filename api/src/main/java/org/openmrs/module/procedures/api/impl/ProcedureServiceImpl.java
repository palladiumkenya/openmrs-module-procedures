package org.openmrs.module.procedures.api.impl;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openmrs.Encounter;
import org.openmrs.EncounterProvider;
import org.openmrs.api.ConditionService;
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
        List<Encounter> encounters = handEncounter(procedure);
        procedure.setEncounters(encounters);
        return procedureDao.saveOrUpdate(procedure);
    }

    private List<Encounter> handEncounter(Procedure procedure) {
        if (procedure.getEncounters().isEmpty()) {
            return new ArrayList<>();
        }
        EncounterService service = Context.getEncounterService();
        for (Encounter encounter : procedure.getEncounters()) {
            for (EncounterProvider provider : encounter.getEncounterProviders()) {
                provider.setEncounter(encounter);
            }
            service.saveEncounter(encounter);
        }
        return procedure.getEncounters();
    }
}
