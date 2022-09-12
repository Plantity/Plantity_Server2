package com.plantity.server.repository;

import com.plantity.server.domain.testimg.TestImgData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestImgRepository extends JpaRepository<TestImgData, Long> {
}
