package com.luxoft.probation.crud.persistence.dao.impl;

import com.luxoft.probation.crud.core.domain.Aircraft;
import com.luxoft.probation.crud.persistence.dao.AircraftDAO;
import com.luxoft.probation.crud.persistence.dao.mapper.AircraftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Aircraft DAO implementation
 * <p>
 * Created by HHayryan on 5/20/2016.
 */
public class AircraftDAOImpl implements AircraftDAO {

    @Autowired
    private AircraftMapper aircraftMapper;

    @Override
    @Transactional
    public void createAircraft(Aircraft aircraft) {
        aircraftMapper.createAircraft(aircraft);
    }

    @Override
    @Transactional
    public void createAircraftBatch(List<Aircraft> aircraftList) {
        aircraftMapper.createAircraftBatch(aircraftList);
    }

    @Override
    public List<Aircraft> getAllAircrafts() {
        return aircraftMapper.getAllAircrafts();
    }

    @Override
    public Aircraft getAircraftById(int id) {
        return aircraftMapper.getAircraftById(id);
    }

    @Override
    public List<Aircraft> getAircraftListByModel(String model) {
        return aircraftMapper.getAircraftListByModel(model);
    }

    @Override
    public List<Aircraft> getAircraftListByCompanyId(int id) {
        return aircraftMapper.getAircraftListByCompanyId(id);
    }

    @Override
    public void updateAircraft(Aircraft aircraft) {
        aircraftMapper.updateAircraft(aircraft);
    }

    @Override
    public void deleteAircraft(int id) {
        aircraftMapper.deleteAircraft(id);
    }

    @Override
    public void deleteAircraftBatch(List<Aircraft> aircraftList) {
        aircraftMapper.deleteAircraftBatch(aircraftList);
    }
}
