package com.okayo.test.api.invoice.command;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AddLineCommand {
  @NotNull private String invoiceReference;
  private UUID itemReference;
  @NotNull private String name;
  private float quantity;
  private float vatRate;
  private float discount;
}
