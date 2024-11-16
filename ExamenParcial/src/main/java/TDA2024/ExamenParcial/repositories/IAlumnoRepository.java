package TDA2024.ExamenParcial.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import TDA2024.ExamenParcial.models.AlumnoModel;


@Repository
public interface IAlumnoRepository extends CrudRepository <AlumnoModel,Integer> {

    
}