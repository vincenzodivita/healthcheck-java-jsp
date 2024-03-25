package it.contrader.converter;

import it.contrader.dto.BloodTestDTO;
import it.contrader.model.BloodTest;


import java.util.ArrayList;
import java.util.List;

public class BloodTestConverter {

    public BloodTestDTO toDTO(BloodTest bloodTest) {
        return bloodTest != null ? new BloodTestDTO(bloodTest.getId(), bloodTest.getRedBloodCell(), bloodTest.getWhiteBloodCell(), bloodTest.getPlatelets(), bloodTest.getHemoglobin(), bloodTest.getIdAdmin(), bloodTest.getIdUser(), bloodTest.getChecked(), bloodTest.getDataInsert(), bloodTest.getDoctor(), bloodTest.getPatience()) : null;
    }

    public BloodTest toEntity(BloodTestDTO bloodTestDTO) {
        return bloodTestDTO != null ? new BloodTest(bloodTestDTO.getId(), bloodTestDTO.getRedBloodCell(), bloodTestDTO.getWhiteBloodCell(), bloodTestDTO.getPlatelets(), bloodTestDTO.getHemoglobin(), bloodTestDTO.getIdAdmin(), bloodTestDTO.getIdUser(), bloodTestDTO.getisChecked(), bloodTestDTO.getDataInsert()) : null;
    }

    public List<BloodTestDTO> toDTOList(List<BloodTest> bloodTestList) {
        List<BloodTestDTO> bloodTestDTOList = new ArrayList<>();
        if(bloodTestList != null) {
            for (BloodTest test : bloodTestList) {
                bloodTestDTOList.add(toDTO(test));
            }
            return bloodTestDTOList;
        } else return null;
    }
}