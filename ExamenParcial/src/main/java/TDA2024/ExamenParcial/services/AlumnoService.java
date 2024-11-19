package TDA2024.ExamenParcial.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TDA2024.ExamenParcial.models.AlumnoModel;
import TDA2024.ExamenParcial.repositories.IAlumnoRepository;

@Service
public class AlumnoService implements IAlumnoService {
    @Autowired
    IAlumnoRepository repository;

    @Override
    public List<AlumnoModel> findAll() {
        return (List<AlumnoModel>) repository.findAll();
    }

    @Override
    public AlumnoModel findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public AlumnoModel add(AlumnoModel model) {
        if(repository.findById(model.id).get()==null){
            return repository.save(model);
        }else{
            return null;
        }
    }

    @Override
    public AlumnoModel update(AlumnoModel model) {
        if(repository.findById(model.id).get()!=null){
            return repository.save(model);
        }else{
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        if(repository.findById(id).get()!=null) {
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }

    
}
