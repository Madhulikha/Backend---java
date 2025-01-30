package com.weclear.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.weclear.backend.model.Address;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}