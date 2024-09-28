package com.webAppApi.service;

import com.webAppApi.entity.Registraion;
import com.webAppApi.exception.ResourceNotFoundException;
import com.webAppApi.payload.RegistrationDto;
import com.webAppApi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private ModelMapper modelMapper;
    private RegistrationRepository registrationRepository;

    public RegistrationService(ModelMapper modelMapper, RegistrationRepository registrationRepository) {
        this.modelMapper = modelMapper;
        this.registrationRepository = registrationRepository;
    }


    public List<RegistrationDto> getAllRegs() {
        List<Registraion> list = registrationRepository.findAll();
        List<RegistrationDto> dtos = list.stream().map(e -> modelMapper.map(e, RegistrationDto.class)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto saveReg(RegistrationDto registrationDto) {
        Registraion reg = modelMapper.map(registrationDto, Registraion.class);
        Registraion saveReg = registrationRepository.save(reg);
        return modelMapper.map(saveReg,RegistrationDto.class);
    }

    public void deleteReg(long id) {
        registrationRepository.deleteById(id);
    }

    public RegistrationDto updateReg(long id, RegistrationDto registrationDto) {
        Registraion reg = registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        reg.setId(id);
        reg.setEmail(registrationDto.getEmail());
        reg.setMobile(registrationDto.getMobile());
        reg.setName(registrationDto.getName());
        Registraion saveReg = registrationRepository.save(reg);
        return modelMapper.map(saveReg,RegistrationDto.class);
    }

    public RegistrationDto findRegById(long id) {
        Registraion reg = registrationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Record Not Found "));
        return modelMapper.map(reg,RegistrationDto.class);
    }
}
