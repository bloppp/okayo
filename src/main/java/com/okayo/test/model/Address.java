package com.okayo.test.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {
  @NotNull private String street_number;
  @NotNull private String street;
  @NotNull private String postalCode;
  @NotNull private String city;
}
