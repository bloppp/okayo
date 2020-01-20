package com.okayo.test.model.user;

import java.util.UUID;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {

  public IUser findById(UUID id);

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  public User findForUpdateById(UUID reference);

  @Modifying
  public void save(User user);
}
