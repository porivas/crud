package com.luxoft.probation.crud.persistence.dao;

import com.luxoft.probation.crud.core.domain.Aircraft;

import java.util.List;

/**
 * Aircraft DAO
 * <p>
 * Created by HHayryan on 5/20/2016.
 */
public interface AircraftDAO {
    void createAircraft(Aircraft aircraft);

    void createAircraftBatch(List<Aircraft> aircraftList);

    List<Aircraft> getAllAircrafts();

    Aircraft getAircraftById(int id);

    List<Aircraft> getAircraftListByModel(String model);

    List<Aircraft> getAircraftListByCompanyId(int id);

    void updateAircraft(Aircraft aircraft);

    void deleteAircraft(int id);

    void deleteAircraftBatch(List<Aircraft> aircraftList);
}
