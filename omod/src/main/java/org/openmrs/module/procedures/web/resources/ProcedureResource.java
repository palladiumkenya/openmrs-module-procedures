package org.openmrs.module.procedures.web.resources;

import java.util.Optional;

import org.openmrs.api.context.Context;
import org.openmrs.module.procedures.api.ProcedureService;
import org.openmrs.module.procedures.api.model.Procedure;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + "/procedure", supportedClass = Procedure.class, supportedOpenmrsVersions = {
        "2.6.* - 9.*" })
public class ProcedureResource extends DataDelegatingCrudResource<Procedure> {
	
	private ProcedureService procedureService;
	
	public ProcedureResource() {
		this.procedureService = Context.getService(ProcedureService.class);
	}
	
	@Override
	public Procedure getByUniqueId(String uuid) {
		Optional<Procedure> procedure = procedureService.getProcedureByUuid(uuid);
		if (procedure.isPresent()) {
			return procedure.get();
		} else {
			return null;
		}
	}
	
	@Override
	protected void delete(Procedure procedure, String s, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	public Procedure newDelegate() {
		return new Procedure();
	}
	
	@Override
	public Procedure save(Procedure procedure) {
		return new Procedure();
	}
	
	@Override
	public void purge(Procedure procedure, RequestContext requestContext) throws ResponseException {
		
	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("uuid");
		description.addProperty("startDatetime");
		description.addProperty("endDatetime");
		return description;
	}
}
