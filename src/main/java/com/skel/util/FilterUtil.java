package com.skel.util;

import com.skel.entity.Slang;
import com.skel.repository.SlangRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hootting on 2016. 11. 5..
 */
public class FilterUtil {

    static SlangRepository slangRepository;

    @Autowired(required = true)
    public void setSlangRepository(SlangRepository slangRepository) {
        FilterUtil.slangRepository = slangRepository;
    }

    public static String filterSlang(String content){
        List<Slang> slangs = slangRepository.findAll();
        StringBuilder sb=new StringBuilder();
        for (Slang s : slangs) {
            if (content.indexOf(s.getWord()) != -1) {
                int size = content.indexOf(s.getWord());
                sb.append(content.substring(0, size));
                sb.append(content.substring(size, content.length() - 1));
            }
        }
        return sb.toString();
    }
}
