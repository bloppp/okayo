package com.okayo.test.model.catalog.item;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import com.okayo.test.model.IVatRate;
import com.okayo.test.model.VatRate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Table
@BatchSize(size = 100)
@Entity(name = "catalog_item")
@Getter
@Setter(value = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogItem implements ICatalogItem {

  @Id @NotNull private UUID id;
  @NotNull private String description;
  @NotNull private float unitPrice;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, targetEntity = VatRate.class)
  @JoinColumn(name = "vat_rate_code")
  private IVatRate vatRate;
}
