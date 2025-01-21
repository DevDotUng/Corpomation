package com.kma.corpomation.domain.user.repository;

import com.kma.corpomation.domain.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {}
