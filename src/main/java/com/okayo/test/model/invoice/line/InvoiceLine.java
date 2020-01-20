package com.okayo.test.model.invoice.line;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.annotations.BatchSize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Table
@BatchSize(size = 100)
@Entity(name = "invoice_line")
@Getter
@Setter(value = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvoiceLine {
  @Id private UUID id;
  // private CatalogItem refItem;
  private String name;
  private BigDecimal quantity;

  @DecimalMin(value = "0")
  private BigDecimal vatRate;

  @DecimalMin(value = "0")
  @DecimalMax(value = "100")
  private BigDecimal discount;

  public BigDecimal getPrice() {
    return quantity
        .multiply(vatRate.add(new BigDecimal(1)))
        .multiply(new BigDecimal(1).subtract(discount));
  }
}
