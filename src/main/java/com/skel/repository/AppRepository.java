package com.skel.repository;

import com.skel.entity.App;
import com.skel.entity.Company;
import com.skel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hootting on 2016. 10. 11..
 */
public interface AppRepository extends JpaRepository<App,Integer> {
    List<App> findByCompany(Company company);
    List<App> findByIsregister(Boolean isregister);
    List<App> findByIsrequestclose(Boolean isrequestclose);
}
