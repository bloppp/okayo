package com.okayo.test.model.catalog.item;

import java.util.UUID;

import com.okayo.test.model.IVatRate;

public interface ICatalogItem {
  public UUID getId();

  public String getDescription();

  public float getUnitPrice();

  public IVatRate getVatRate();
}
