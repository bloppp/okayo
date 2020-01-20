package com.okayo.test.model.invoice;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface InvoiceRepository extends Repository<Invoice, String> {

  public IInvoice findByReference(String reference);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public Invoice findForUpdateByReference(String reference);

  @Modifying
  public void save(Invoice user);
}
