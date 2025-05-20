package org.example.moreeduceoriginapplication.repository;

import org.example.moreeduceoriginapplication.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
