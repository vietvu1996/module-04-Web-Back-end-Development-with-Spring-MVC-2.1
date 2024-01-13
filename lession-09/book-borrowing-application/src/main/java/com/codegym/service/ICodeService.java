package com.codegym.service;

import com.codegym.model.Code;

import java.util.List;

public interface ICodeService {
    List<Code> findAll();

    Code findById(int id);

    Code save(Code code);

    void delete(int id);

    List<Code> findAllCodesByBookId(int bookId);

    List<Code> findAvailableCodesByBookId(int bookId);

    boolean existsByCodeAndStatus_StatusName(int code, String statusName);

    boolean existsByCode(int code);
}
