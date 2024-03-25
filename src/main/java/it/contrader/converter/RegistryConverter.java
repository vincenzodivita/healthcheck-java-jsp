package it.contrader.converter;

import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;

import java.util.ArrayList;
import java.util.List;


public class RegistryConverter implements Converter<Registry, RegistryDTO>{
    @Override
    public RegistryDTO toDTO(Registry registry) {
        return registry != null ? new RegistryDTO(registry.getId(), registry.getName(), registry.getSurname(), registry.getBirthDate(),registry.getEmail(),registry.getNationality(),registry.getCity(),registry.getAddress(),registry.getCf(),registry.getIdUser()) : null;
    }

    @Override
    public Registry toEntity(RegistryDTO registryDTO) {
        return registryDTO!= null ? new Registry(registryDTO.getId(), registryDTO.getName(), registryDTO.getSurname(), registryDTO.getBirthDate(),registryDTO.getEmail(),registryDTO.getNationality(),registryDTO.getCity(),registryDTO.getAddress(),registryDTO.getCf(),registryDTO.getIdUser()) : null;
    }

    @Override
    public List<RegistryDTO> toDTOList(List<Registry> registries) {
        List<RegistryDTO> registryDTOList = new ArrayList<>();
        if(registries != null) {
            for (Registry registry : registries) {
                registryDTOList.add(toDTO(registry));
            }
            return registryDTOList;
        } else return null;
    }

    public RegistryDTO DoctorListToDTO(Registry registry) {
        return registry != null ? new RegistryDTO(registry.getId(), registry.getSurname()) : null;
    }

}
