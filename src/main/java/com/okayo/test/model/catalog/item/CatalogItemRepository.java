package com.okayo.test.model.catalog.item;

import java.util.UUID;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface CatalogItemRepository extends Repository<CatalogItem, String> {

	  public ICatalogItem findById(UUID id);

	  @Lock(LockModeType.PESSIMISTIC_WRITE)
	  public CatalogItem findForUpdateById(UUID id);

	  @Modifying
	  public void save(CatalogItem user);
	}
