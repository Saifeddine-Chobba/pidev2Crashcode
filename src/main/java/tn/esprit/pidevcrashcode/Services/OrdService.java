package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.crashcode.campsidelocal.Entities.Ord;
import tn.crashcode.campsidelocal.Repositories.OrdRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdService {

    private final OrdRepository ordRepository;

    @Autowired
    public OrdService(OrdRepository ordRepository) {
        this.ordRepository = ordRepository;
    }

    public Ord createOrd(Ord ord) {
        return ordRepository.save(ord);
    }

    public Optional<Ord> getOrdById(int id) {
        return ordRepository.findById(id);
    }

    public List<Ord> getAllOrds() {
        return ordRepository.findAll();
    }

    public Ord updateOrd(Ord ord) {
        return ordRepository.save(ord);
    }

    public void deleteOrd(int id) {
        ordRepository.deleteById(id);
    }
}
