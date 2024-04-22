package org.openmrs.module.procedures.web.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openmrs.Encounter;
import org.openmrs.api.context.Context;
import org.openmrs.module.procedures.api.ProcedureService;
import org.openmrs.module.procedures.api.model.Procedure;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.CustomRepresentation;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
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
		return procedureService.saveOrUpdate(procedure);
	}
	
	@Override
	public void purge(Procedure procedure, RequestContext requestContext) throws ResponseException {
		
	}
	
	@Override
	protected PageableResult doSearch(RequestContext requestContext) {
		String orderUuid = requestContext.getParameter("orderUuid");
		String orderTypeUuid = requestContext.getParameter("orderTypeUuid");
		
		return new NeedsPaging<>(new ArrayList<>(), requestContext);
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() throws ResourceDoesNotSupportOperationException {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("patient");
		description.addProperty("encounter");
		description.addProperty("procedureOrder");
		description.addProperty("concept");
		description.addProperty("procedureReason");
		description.addProperty("category");
		description.addProperty("bodySite");
		description.addProperty("partOf");
		description.addProperty("startDatetime");
		description.addProperty("endDatetime");
		description.addProperty("status");
		description.addProperty("statusReason");
		description.addProperty("outcome");
		description.addProperty("location");
		description.addProperty("encounters");
		description.addProperty("participants");
		description.addProperty("procedureResults");
		description.addProperty("procedureReport");
		return description;
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		if (representation instanceof RefRepresentation) {
			description.addProperty("uuid");
			description.addProperty("patient", Representation.REF);
			description.addProperty("procedureOrder", Representation.REF);
			description.addProperty("concept", Representation.REF);
			description.addProperty("procedureReason", Representation.REF);
			description.addProperty("category", Representation.REF);
			description.addProperty("bodySite", Representation.REF);
			description.addProperty("partOf", Representation.REF);
			description.addProperty("startDatetime");
			description.addProperty("endDatetime");
			description.addProperty("status");
			description.addProperty("statusReason", Representation.REF);
			description.addProperty("outcome");
			description.addProperty("procedureReport");
			description.addProperty("location", Representation.REF);
			description.addProperty("encounters", Representation.REF);
		} else if (representation instanceof DefaultRepresentation) {
			description.addProperty("uuid");
			description.addProperty("patient", Representation.DEFAULT);
			description.addProperty("procedureOrder", Representation.DEFAULT);
			description.addProperty("concept", Representation.DEFAULT);
			description.addProperty("procedureReason", Representation.DEFAULT);
			description.addProperty("category", Representation.DEFAULT);
			description.addProperty("bodySite", Representation.DEFAULT);
			description.addProperty("partOf", Representation.DEFAULT);
			description.addProperty("startDatetime");
			description.addProperty("endDatetime");
			description.addProperty("status");
			description.addProperty("statusReason", Representation.DEFAULT);
			description.addProperty("outcome");
			description.addProperty("procedureReport");
			description.addProperty("location", Representation.DEFAULT);
			description.addProperty("encounters", Representation.DEFAULT);
		} else if (representation instanceof FullRepresentation) {
			description.addProperty("uuid");
			description.addProperty("patient", Representation.REF);
			description.addProperty("procedureOrder", Representation.FULL);
			description.addProperty("concept", Representation.FULL);
			description.addProperty("procedureReason", Representation.REF);
			description.addProperty("category", Representation.FULL);
			description.addProperty("bodySite", Representation.FULL);
			description.addProperty("partOf", Representation.REF);
			description.addProperty("startDatetime");
			description.addProperty("endDatetime");
			description.addProperty("status");
			description.addProperty("statusReason", Representation.FULL);
			description.addProperty("outcome");
			description.addProperty("procedureReport");
			description.addProperty("location", Representation.REF);
			description.addProperty("encounters", Representation.REF);
		} else if (representation instanceof CustomRepresentation) { // custom rep
			description = null;
		}
		return description;
	}
	
	@PropertyGetter(value = "encounters")
	public List<Encounter> getEncounters(Procedure instance) {
		try {
			List<Encounter> encounters = instance.getEncounters();
			return encounters;
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
