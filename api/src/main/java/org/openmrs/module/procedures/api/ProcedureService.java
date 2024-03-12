package org.openmrs.module.procedures.api;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import org.openmrs.module.procedures.api.model.Procedure;
import org.springframework.transaction.annotation.Transactional;

public interface ProcedureService {
	
	Optional<Procedure> getProcedureByUuid(@NotNull String uuid);
}
