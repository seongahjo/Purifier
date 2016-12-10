package com.skel.repository;

import com.skel.entity.App;
import com.skel.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hootting on 2016. 10. 11..
 */
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    List<Chat> findByApp(App app);
}
