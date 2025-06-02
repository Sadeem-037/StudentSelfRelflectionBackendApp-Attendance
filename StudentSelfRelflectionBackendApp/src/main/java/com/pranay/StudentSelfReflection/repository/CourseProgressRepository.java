package com.pranay.StudentSelfReflection.repository;

import com.pranay.StudentSelfReflection.model.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseProgressRepository extends JpaRepository<CourseProgress,Long>
{

}
