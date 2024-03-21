package org.openmrs.module.procedures.api.impl;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openmrs.Encounter;
import org.openmrs.api.ConditionService;
import org.openmrs.api.EncounterService;
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
        List<Encounter> encounters = handEncounter(procedure);
        procedure.setEncounters(encounters);
        procedure.setEncounter(encounters.get(0));
        return procedureDao.saveOrUpdate(procedure);
    }

    private List<Encounter> handEncounter(Procedure procedure) {
        if (procedure.getEncounters().isEmpty()) {
            return new ArrayList<>();
        }
        EncounterService service = Context.getEncounterService();
        List<Encounter> savedEncounters = new ArrayList<>();
        for (Encounter encounter : procedure.getEncounters()) {
            System.out.println("Before saving encounter ----------------------------" + encounter.getEncounterDatetime());
            Encounter saved = service.saveEncounter(encounter);
            System.out.println("After saving encounter ----------------------------");
            if (saved == null) {
                System.out.println("Encounter not save -------------------------");
            } else {
                System.out
                        .println("Saved encounter ID -------------------------+++++++++++++ " + saved.getEncounterId());
            }
        }
        if (!procedure.getComplications().isEmpty()) {
            ConditionService conditionService = Context.getConditionService();
            procedure.getComplications().forEach(c -> {
                c.setEncounter(savedEncounters.get(0));
                conditionService.saveCondition(c);
            });
        }
        return procedure.getEncounters();
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
