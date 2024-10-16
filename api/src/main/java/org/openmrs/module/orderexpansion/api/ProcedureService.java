package org.openmrs.module.orderexpansion.api;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import org.openmrs.module.orderexpansion.api.model.Procedure;

public interface ProcedureService {
	
	Optional<Procedure> getProcedureByUuid(@NotNull String uuid);
	
	Procedure saveOrUpdate(@NotNull Procedure procedure);
	
}
