package com.okayo.test.api.invoice.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class GenerateInvoiceCommand {
  private String reference;
}
