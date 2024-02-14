package com.Identity_reconcilation.Identity.repository;

import com.Identity_reconcilation.Identity.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    //Optional<ContactEntity> findById(Integer id);
    @Query("SELECT c FROM Contact c WHERE c.email = :email")
    List<Contact> findByEmail(@Param("email") String email);

    List<Contact> findByPhoneNumber(String phoneNumber);
}
