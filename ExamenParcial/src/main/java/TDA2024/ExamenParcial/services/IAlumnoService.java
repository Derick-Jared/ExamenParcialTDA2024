package TDA2024.ExamenParcial.services;

import java.util.List;

import TDA2024.ExamenParcial.models.AlumnoModel;

public interface IAlumnoService {
    public List<AlumnoModel> findAll ();
    public AlumnoModel findById (int id);
    public AlumnoModel add (AlumnoModel model);
    public AlumnoModel update (AlumnoModel model);
    public boolean delete (int id);
    
}
