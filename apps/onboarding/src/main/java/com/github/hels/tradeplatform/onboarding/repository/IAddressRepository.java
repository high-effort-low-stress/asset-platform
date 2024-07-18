package com.github.hels.tradeplatform.onboarding.repository;

import com.github.hels.tradeplatform.onboarding.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
}
