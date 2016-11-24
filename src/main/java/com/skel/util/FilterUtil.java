package com.skel.util;

import com.skel.entity.Slang;
import com.skel.repository.SlangRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hootting on 2016. 11. 5..
 */
@Log
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
            if (content.contains(s.getWord())) {
                int size = content.indexOf(s.getWord());
                sb.append(content.substring(0, size));
                for(int i=0; i<s.getWord().length();i++)
                sb.append("*");
                sb.append(content.substring(size+s.getWord().length(), content.length() - 1));
            }
        }
        if(sb.toString().equals(""))
            return content;
        return sb.toString();
    }
}
