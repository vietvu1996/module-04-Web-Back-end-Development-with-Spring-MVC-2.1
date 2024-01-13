package com.codegym.service.impl;

import com.codegym.model.Code;
import com.codegym.repository.ICodeRepository;
import com.codegym.service.ICodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeService implements ICodeService {
    private final ICodeRepository codeRepository;

    public CodeService(ICodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public List<Code> findAll() {
        return codeRepository.findAll();
    }

    @Override
    public Code findById(int id) {
        return codeRepository.findById(id).orElse(null);
    }

    @Override
    public Code save(Code code) {
        return codeRepository.save(code);
    }

    @Override
    public void delete(int id) {
        codeRepository.deleteById(id);
    }

    @Override
    public List<Code> findAllCodesByBookId(int bookId) {
        return codeRepository.findAllByBook_id(bookId);
    }

    @Override
    public List<Code> findAvailableCodesByBookId(int bookId) {
        List<Code> allCodes = findAllCodesByBookId(bookId);
        return allCodes.stream().filter(code -> code.getStatus().getStatusName().equals("available")).collect(Collectors.toList());
    }

    @Override
    public boolean existsByCodeAndStatus_StatusName(int code, String statusName) {
        return codeRepository.existsByCodeAndStatus_StatusName(code, statusName);
    }

    @Override
    public boolean existsByCode(int code) {
        return codeRepository.existsByCode(code);
    }
}
