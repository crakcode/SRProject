package net.javaguides.springboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User[] findByAccountidAndPassword(String accountid, String password);
	
	User[] findByAccountidAndName(String accountid, String name);

	User findById(String id);

	User findByAccountid(Long accountid);

	List<User> findAllByAccountid(Long accountid, Pageable pageable);


}
