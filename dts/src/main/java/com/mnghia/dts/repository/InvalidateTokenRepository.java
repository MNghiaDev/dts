package com.mnghia.dts.repository;

import com.mnghia.dts.model.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidateTokenRepository extends JpaRepository<InvalidateToken, String> {
}
