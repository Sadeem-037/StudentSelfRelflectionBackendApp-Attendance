package com.pranay.StudentSelfReflection.repository;

import com.pranay.StudentSelfReflection.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long>
{

}
