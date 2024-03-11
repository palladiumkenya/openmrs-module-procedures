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

import java.util.Date;

import org.openmrs.*;

public class ProcedureOrder extends ServiceOrder {
	
	public static final long serialVersionUID = 1L;
	
	private ProcedureOrder relatedProcedure;
	
	public Concept modality;
	
	private Provider participant;
	
	private Date startDatetime;
	
	private Date endDatetime;
	
	private Concept outcome;
	
	private Condition complication;
	
	private String report;
	
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
	
	public Concept getModality() {
		return modality;
	}
	
	public void setModality(Concept modality) {
		this.modality = modality;
	}
	
	public ProcedureOrder getRelatedProcedure() {
		return relatedProcedure;
	}
	
	public void setRelatedProcedure(ProcedureOrder relatedProcedure) {
		this.relatedProcedure = relatedProcedure;
	}
	
	public Provider getParticipant() {
		return participant;
	}
	
	public void setParticipant(Provider participant) {
		this.participant = participant;
	}
	
	public Date getStartDatetime() {
		return startDatetime;
	}
	
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	
	public Date getEndDatetime() {
		return endDatetime;
	}
	
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	
	public Concept getOutcome() {
		return outcome;
	}
	
	public void setOutcome(Concept outcome) {
		this.outcome = outcome;
	}
	
	public Condition getComplication() {
		return complication;
	}
	
	public void setComplication(Condition complication) {
		this.complication = complication;
	}
	
	public String getReport() {
		return report;
	}
	
	public void setReport(String report) {
		this.report = report;
	}
}
