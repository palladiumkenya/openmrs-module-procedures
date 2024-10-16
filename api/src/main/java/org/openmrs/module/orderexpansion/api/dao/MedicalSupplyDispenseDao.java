package org.openmrs.module.orderexpansion.api.dao;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import org.openmrs.module.orderexpansion.api.model.MedicalSupplyDispense;

public interface MedicalSupplyDispenseDao {
	
	Optional<MedicalSupplyDispense> get(@NotNull int id);
	
	Optional<MedicalSupplyDispense> getMedicalSupplyDispenseByUuid(@NotNull String uuid);
	
	MedicalSupplyDispense saveOrUpdate(@NotNull MedicalSupplyDispense medicalSupplyDispense);
}
