package com.datasolution.learnsecurity.repository;

import com.datasolution.learnsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUserId(String userId);

    // userId 중복체크
    boolean existsByUserId(String userId);

    // 로그인
    boolean existsByUserIdAndPassword(String userId, String password);
}
