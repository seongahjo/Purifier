package com.skel.repository;

import com.skel.entity.Chat;
import com.skel.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hootting on 2016. 10. 11..
 */
public interface ReportRepository extends JpaRepository<Report,Integer> {
}
