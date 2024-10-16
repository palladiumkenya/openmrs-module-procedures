package org.openmrs.module.procedures.api;

import java.util.Optional;

import org.openmrs.module.procedures.api.model.MedicalSupplyDispense;
import org.springframework.lang.NonNull;

public interface MedicalSupplyDispenseService {
	
	Optional<MedicalSupplyDispense> getMedicalSupplyDispenseByUuid(@NonNull String uuid);
	
	MedicalSupplyDispense saveOrUpdate(@NonNull MedicalSupplyDispense medicalSupplyDispense);
	
}
