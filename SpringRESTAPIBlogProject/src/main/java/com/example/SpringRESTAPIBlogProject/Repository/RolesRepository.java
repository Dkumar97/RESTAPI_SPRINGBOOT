package com.example.SpringRESTAPIBlogProject.Repository;

import com.example.SpringRESTAPIBlogProject.Entity.Roles;
import com.example.SpringRESTAPIBlogProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}
