package com.okayo.test.model.invoice.line;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class InvoiceLineDto {

  @NotNull private UUID id;
  private String itemId;
  @NotNull private String name;
  private String quantity;
  private String vatRate;
  private String discount;
  private String price;

  public InvoiceLineDto(InvoiceLine line) {
    id = line.getId();
    itemId = null;
    name = line.getName();
    quantity = line.getQuantity().toString();
    discount =
        line.getDiscount().compareTo(new BigDecimal(100)) == 0
            ? "Offert"
            : line.getDiscount().toString() + "%";
    vatRate = line.getVatRate().toString() + "%";
    price = line.getPrice().toString();
  }
}
