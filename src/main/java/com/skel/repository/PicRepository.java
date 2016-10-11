package com.skel.repository;

import com.skel.entity.Chat;
import com.skel.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hootting on 2016. 10. 11..
 */
public interface PicRepository extends JpaRepository<Pic,Long> {
}
