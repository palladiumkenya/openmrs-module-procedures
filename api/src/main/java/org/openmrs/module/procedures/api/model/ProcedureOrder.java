/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.procedures.api.model;

import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ServiceOrder;

public class ProcedureOrder extends ServiceOrder {
	
	public static final long serialVersionUID = 1L;
	
	public Concept specimenType;
	
	public Concept bodySite;
	
	private ProcedureOrder relatedProcedure;
	
	private Set<Procedure> procedures;
	
	public ProcedureOrder() {
	}
	
	@Override
	public ProcedureOrder copy() {
		ProcedureOrder newOrder = new ProcedureOrder();
		super.copyHelper(newOrder);
		return newOrder;
	}
	
	/**
	 * Creates a discontinuation order for this.
	 *
	 * @return the newly created order
	 * @see org.openmrs.ServiceOrder#cloneForDiscontinuing()
	 */
	@Override
	public ProcedureOrder cloneForDiscontinuing() {
		ProcedureOrder newOrder = new ProcedureOrder();
		super.cloneForDiscontinuingHelper(newOrder);
		return newOrder;
	}
	
	/**
	 * Creates a ReferralOrder for revision from this order, sets the previousOrder, action field and
	 * other test order fields.
	 *
	 * @return the newly created order
	 */
	@Override
	public ProcedureOrder cloneForRevision() {
		ProcedureOrder newOrder = new ProcedureOrder();
		super.cloneForRevisionHelper(newOrder);
		return newOrder;
	}
	
	public Concept getSpecimenType() {
		return specimenType;
	}
	
	public void setSpecimenType(Concept specimenType) {
		this.specimenType = specimenType;
	}
	
	public Concept getBodySite() {
		return bodySite;
	}
	
	public void setBodySite(Concept bodySite) {
		this.bodySite = bodySite;
	}
	
	public Set<Procedure> getProcedures() {
		return procedures;
	}
	
	public void setProcedures(Set<Procedure> procedures) {
		this.procedures = procedures;
	}
	
	public ProcedureOrder getRelatedProcedure() {
		return relatedProcedure;
	}
	
	public void setRelatedProcedure(ProcedureOrder relatedProcedure) {
		this.relatedProcedure = relatedProcedure;
	}
}
