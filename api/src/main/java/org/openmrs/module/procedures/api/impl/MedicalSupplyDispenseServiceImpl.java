package org.openmrs.module.procedures.api.impl;

import javax.transaction.Transactional;

import java.util.Optional;

import org.openmrs.module.procedures.api.MedicalSupplyDispenseService;
import org.openmrs.module.procedures.api.dao.MedicalSupplyDispenseDao;
import org.openmrs.module.procedures.api.model.MedicalSupplyDispense;

@Transactional
public class MedicalSupplyDispenseServiceImpl implements MedicalSupplyDispenseService {
	
	private MedicalSupplyDispenseDao medicalSupplyDispenseDao;
	
	public void setMedicalSupplyDispenseDao(MedicalSupplyDispenseDao medicalSupplyDispenseDao) {
		this.medicalSupplyDispenseDao = medicalSupplyDispenseDao;
	}
	
	@Override
	public Optional<MedicalSupplyDispense> getMedicalSupplyDispenseByUuid(String uuid) {
		return medicalSupplyDispenseDao.getMedicalSupplyDispenseByUuid(uuid);
	}
	
	@Override
	public MedicalSupplyDispense saveOrUpdate(MedicalSupplyDispense medicalSupplyDispense) {
		return medicalSupplyDispenseDao.saveOrUpdate(medicalSupplyDispense);
	}
	
}
