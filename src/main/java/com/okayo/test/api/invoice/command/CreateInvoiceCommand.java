package com.okayo.test.api.invoice.command;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CreateInvoiceCommand {
  private UUID userId;
  private UUID companyId;
}
