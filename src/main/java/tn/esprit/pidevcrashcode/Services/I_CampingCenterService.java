package tn.esprit.pidevcrashcode.Services;

import tn.esprit.pidevcrashcode.Entities.CampingCenter;

import java.util.List;

public interface I_CampingCenterService {

    public List<CampingCenter> findAllCampingCenters();
    public CampingCenter AddCampingCenter(CampingCenter CC);
    public String DeleteCampingCenter(CampingCenter CC);
    public void deleteCampingCenterById(int id);
}
