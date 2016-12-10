package com.skel.repository;

import com.skel.entity.App;
import com.skel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hootting on 2016. 10. 11..
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findFirstByAppOrderByCountPictureAsc(App app);
    User findFirstByAppOrderByCountSlangAsc(App app);
    User findById(String id);
}
