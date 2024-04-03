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
        handleProcedureComplications(procedure);
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

    private void handleProcedureComplications(Procedure procedure) {
        if (procedure.getComplications() != null && !procedure.getComplications().isEmpty()) {
            ConditionService conditionService = Context.getConditionService();
            Encounter encounter = (!procedure.getEncounters().isEmpty()) ? procedure.getEncounters().get(0) : null;
            procedure.getComplications().forEach(condition -> {
                if (encounter != null) {
                    condition.setEncounter(encounter);
                }
                conditionService.saveCondition(condition);
            });
        }
    }
}
