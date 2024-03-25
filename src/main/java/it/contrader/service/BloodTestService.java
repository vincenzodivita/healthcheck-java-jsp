package it.contrader.service;

import it.contrader.converter.BloodTestConverter;
import it.contrader.dao.BloodTestDAO;
import it.contrader.dto.BloodTestDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.BloodTest;

import java.util.List;

public class BloodTestService {
    private final BloodTestDAO bloodTestDAO;
    private final BloodTestConverter bloodTestConverter;

    public BloodTestService() {
        this.bloodTestDAO = new BloodTestDAO();
        this.bloodTestConverter = new BloodTestConverter();
    }


    public boolean insert(BloodTestDTO dto) {return bloodTestDAO.insert(bloodTestConverter.toEntity(dto));}
    public boolean update(BloodTestDTO dto) {
        return bloodTestDAO.update(bloodTestConverter.toEntity(dto));
    }

    public boolean check(int id) {return bloodTestDAO.check(id);}

    public List<BloodTestDTO> getAll() {
        return bloodTestConverter.toDTOList(bloodTestDAO.getAll());
    }
    public BloodTestDTO read(int id){ return bloodTestConverter.toDTO(bloodTestDAO.read(id)); }
    public BloodTestDTO readUser(int id, int idUser) { return bloodTestConverter.toDTO(bloodTestDAO.readUser(id,idUser));}
    public List<BloodTestDTO> getAll(int id) {
        return bloodTestConverter.toDTOList(bloodTestDAO.getAll(id));
    }
    public List<BloodTestDTO> getAllUser(int id) { return bloodTestConverter.toDTOList(bloodTestDAO.getAllUser(id)); }

    public boolean delete(int id) {return bloodTestDAO.delete(id);}

    public BloodTestDTO readAdmin(int id, int idAdmin) { return  bloodTestConverter.toDTO(bloodTestDAO.readAdmin(id, idAdmin));}


}