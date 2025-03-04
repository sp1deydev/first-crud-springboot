package com.test_springboot.test_springboot.repository;

import com.test_springboot.test_springboot.entity.Permission;
import com.test_springboot.test_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
