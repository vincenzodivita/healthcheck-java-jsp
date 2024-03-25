package it.contrader.service;

import it.contrader.converter.RegistryConverter;
import it.contrader.dao.RegistryDAO;
import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;

import java.util.List;


public class RegistryService extends AbstractService<Registry, RegistryDTO>{

    private RegistryDAO dao1 = new RegistryDAO();

    public RegistryService(){
        this.dao = new RegistryDAO();
        this.converter = new RegistryConverter();
    }

    public List<RegistryDTO> getAllPatient(int idAdmin) {
        return converter.toDTOList(dao1.getAllPatient(idAdmin));
    }
}
