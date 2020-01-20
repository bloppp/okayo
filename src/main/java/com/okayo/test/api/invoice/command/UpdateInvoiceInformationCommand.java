package com.okayo.test.api.invoice.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UpdateInvoiceInformationCommand {
  private String reference;
  private String comment;
  private String paymentCondition;
  private Long dueTimestamp;
}
