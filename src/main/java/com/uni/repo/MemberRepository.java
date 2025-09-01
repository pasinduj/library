package com.uni.repo;

import com.uni.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsername(String username);
    boolean existsByUsernameAndPassword(String username, String password);

}


