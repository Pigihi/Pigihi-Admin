/**
 * 
 */
package com.pigihi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pigihi.entity.AdminEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface AdminRepository extends MongoRepository<AdminEntity, String> {
	
	@Query("{email: ?0}")
	AdminEntity findByEmail(String email);

}
