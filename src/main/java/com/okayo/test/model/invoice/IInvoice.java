package com.okayo.test.model.invoice;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.okayo.test.model.invoice.line.InvoiceLine;
import com.okayo.test.model.user.IUser;

public interface IInvoice {
  String getReference();

  IUser getUser();

  Set<InvoiceLine> getLineList();

  String getComment();

  Instant getEmissionDate();

  Instant getDueDate();

  String getPaymentCondition();

  BigDecimal getPrice();
}
