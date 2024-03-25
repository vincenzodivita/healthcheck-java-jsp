package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UrineTestDTO;
import it.contrader.model.UrineTest;
public class UrineTestConverter {

    public UrineTestDTO toDTO(UrineTest urineTest) {
        return urineTest != null ? new UrineTestDTO(urineTest.getId(), urineTest.getColor(), urineTest.getPh(), urineTest.getProtein(), urineTest.getHemoglobin(), urineTest.getIdAdmin(), urineTest.getIdUser(), urineTest.getIsChecked(), urineTest.getDataInsert(), urineTest.getDoctor(), urineTest.getPatience()) : null;
    }


    public UrineTest toEntity(UrineTestDTO urineTestDTO) {
        return urineTestDTO != null ? new UrineTest(urineTestDTO.getId(), urineTestDTO.getColor(), urineTestDTO.getPh(), urineTestDTO.getProtein(), urineTestDTO.getHemoglobin(), urineTestDTO.getIdAdmin(), urineTestDTO.getIdUser(), urineTestDTO.getIsChecked(), urineTestDTO.getDataInsert()) :null;
    }

    public List<UrineTestDTO> toDTOList(List<UrineTest> urineTestList) {

        List<UrineTestDTO> urineTestDTOList = new ArrayList<>();
        if (urineTestList != null) {
            for (UrineTest test : urineTestList) {
                urineTestDTOList.add(toDTO(test));
            }
            return urineTestDTOList;
        } else return null;
    }
}