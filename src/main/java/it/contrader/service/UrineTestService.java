package it.contrader.service;

import it.contrader.converter.UrineTestConverter;
import it.contrader.dao.UrineTestDAO;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UrineTestDTO;
import it.contrader.model.UrineTest;

import java.util.List;

public class UrineTestService extends AbstractService<UrineTest, UrineTestDTO>{
    private final UrineTestDAO urineTestDAO;
    private final UrineTestConverter urineTestConverter;
    public UrineTestService() {
        this.urineTestDAO = new UrineTestDAO();
        this.urineTestConverter = new UrineTestConverter();
    }
    public List<UrineTestDTO> getAll() {return urineTestConverter.toDTOList(urineTestDAO.getAll()); }
    public List<UrineTestDTO> getAll(int id) {return urineTestConverter.toDTOList(urineTestDAO.getAll(id));}
    public List<UrineTestDTO> getAllUser(int id) { return urineTestConverter.toDTOList(urineTestDAO.getAllUser(id)); }
    public boolean update(UrineTestDTO dto) {
        return urineTestDAO.update(urineTestConverter.toEntity(dto));
    }
    public boolean insert(UrineTestDTO urineTest) {return urineTestDAO.insert(urineTestConverter.toEntity(urineTest));}
    public boolean check(int id) {return urineTestDAO.check(id);}
    public UrineTestDTO read(int id){ return urineTestConverter.toDTO(urineTestDAO.read(id)); }
    public UrineTestDTO readUser(int id, int idUser) { return urineTestConverter.toDTO(urineTestDAO.readUser(id,idUser));}
    public UrineTestDTO readAdmin(int id, int idAdmin) { return  urineTestConverter.toDTO(urineTestDAO.readAdmin(id, idAdmin));}
    public boolean delete(int id) {return urineTestDAO.delete(id);}
}
