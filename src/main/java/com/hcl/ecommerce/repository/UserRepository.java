package com.hcl.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("select u from User u where userName=:userName")
	User findByUserName(@Param("userName") String userName);

	Optional<User> findByUserNameAndPassword(String userName, String encodePassword);

}
