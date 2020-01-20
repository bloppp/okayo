package com.okayo.test.model.company;

import java.util.UUID;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface CompanyRepository extends Repository<Company, String> {

  public ICompany findById(UUID id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Company findForUpdateById(UUID reference);

  @Modifying
  public void save(Company user);
}
