package org.openmrs.module.procedures.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/procedure/")
public class ProcedureConceptSearchController {
	
	@RequestMapping(method = RequestMethod.GET, path = "concepts")
	@ResponseBody
	public List<Concept> findConceptByClass(@RequestParam String conceptClass) {
		ConceptService service = Context.getConceptService();
		ConceptClass conceptClazz = service.getConceptClassByUuid(conceptClass);
		
		List<Concept> results = service.getConceptsByClass(conceptClazz);
		if (!results.isEmpty()) {
			return results;
		}
		
		return new ArrayList<>();
	}
}
