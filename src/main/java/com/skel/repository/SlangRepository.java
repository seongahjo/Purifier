package com.skel.repository;

import com.skel.entity.App;
import com.skel.entity.Slang;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hootting on 2016. 10. 11..
 */
public interface SlangRepository extends JpaRepository<Slang,Integer> {
}
