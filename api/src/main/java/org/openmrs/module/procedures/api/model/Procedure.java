package org.openmrs.module.procedures.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Date;
import java.util.List;

import org.openmrs.BaseFormRecordableOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Patient;

@Entity
@Table(name = "procedures")
public class Procedure extends BaseFormRecordableOpenmrsData {
	
	public enum ProcedureStatus {
		PREPARATION,
		IN_PROGRESS,
		NOT_DONE,
		ON_HOLD,
		STOPPED,
		COMPLETED
	}
	
	public enum ProcedureOutcome {
		SUCCESSFUL,
		NOT_SUCCESSFUL,
		PARTIALLY_SUCCESSFUL
	}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "procedure_id")
	private Integer procedureId;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "procedure_order_id")
	private ProcedureOrder procedureOrder;
	
	@ManyToOne
	@JoinColumn(name = "concept")
	private Concept concept;
	
	@ManyToOne
	@JoinColumn(name = "procedure_reason")
	private Concept procedureReason;
	
	@ManyToOne
	@JoinColumn(name = "category")
	private Concept category;
	
	@ManyToOne
	@JoinColumn(name = "body_site")
	private Concept bodySite;
	
	@ManyToOne
	@JoinColumn(name = "part_of")
	private Procedure partOf;
	
	@Column(name = "start_datetime")
	private Date startDatetime;
	
	@Column(name = "end_datetime")
	private Date endDatetime;
	
	@Enumerated(EnumType.STRING)
	private ProcedureStatus status;
	
	@ManyToOne
	@JoinColumn(name = "status_reason")
	private Concept statusReason;
	
	@Enumerated(EnumType.STRING)
	private ProcedureOutcome outcome;
	
	@Column(name = "report")
	private String procedureReport;
	
	@ManyToOne
	@JoinColumn(name = "modality")
	public Concept modality;
	
	@OneToMany
	@JoinTable(name = "encounter_procedures", joinColumns = @JoinColumn(name = "procedure_id"), inverseJoinColumns = @JoinColumn(name = "encounter_id"))
	private List<Encounter> encounters;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	public Integer getProcedureId() {
		return procedureId;
	}
	
	public void setProcedureId(Integer procedureId) {
		this.procedureId = procedureId;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public ProcedureOrder getProcedureOrder() {
		return procedureOrder;
	}
	
	public void setProcedureOrder(ProcedureOrder procedureOrder) {
		this.procedureOrder = procedureOrder;
	}
	
	public Concept getConcept() {
		return concept;
	}
	
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public Concept getCategory() {
		return category;
	}
	
	public void setCategory(Concept category) {
		this.category = category;
	}
	
	public Concept getBodySite() {
		return bodySite;
	}
	
	public void setBodySite(Concept bodySite) {
		this.bodySite = bodySite;
	}
	
	public Procedure getPartOf() {
		return partOf;
	}
	
	public void setPartOf(Procedure partOf) {
		this.partOf = partOf;
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
	
	public ProcedureStatus getStatus() {
		return status;
	}
	
	public void setStatus(ProcedureStatus status) {
		this.status = status;
	}
	
	public ProcedureOutcome getOutcome() {
		return outcome;
	}
	
	public void setOutcome(ProcedureOutcome outcome) {
		this.outcome = outcome;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Concept getProcedureReason() {
		return procedureReason;
	}
	
	public void setProcedureReason(Concept procedureReason) {
		this.procedureReason = procedureReason;
	}
	
	public Concept getStatusReason() {
		return statusReason;
	}
	
	public void setStatusReason(Concept statusReason) {
		this.statusReason = statusReason;
	}
	
	public String getProcedureReport() {
		return procedureReport;
	}
	
	public void setProcedureReport(String procedureReport) {
		this.procedureReport = procedureReport;
	}
	
	public Concept getModality() {
		return modality;
	}
	
	public void setModality(Concept modality) {
		this.modality = modality;
	}
	
	public List<Encounter> getEncounters() {
		return encounters;
	}
	
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	
	@Override
	public Integer getId() {
		return getProcedureId();
	}
	
	@Override
	public void setId(Integer integer) {
		setProcedureId(integer);
	}
}
