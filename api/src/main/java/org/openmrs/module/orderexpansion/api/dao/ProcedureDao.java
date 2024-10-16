package org.openmrs.module.orderexpansion.api.dao;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import org.openmrs.module.orderexpansion.api.model.Procedure;

public interface ProcedureDao {
	
	Optional<Procedure> get(@NotNull int id);
	
	Optional<Procedure> getProcedureByUuid(@NotNull String uuid);
	
	Procedure saveOrUpdate(@NotNull Procedure procedure);
}
