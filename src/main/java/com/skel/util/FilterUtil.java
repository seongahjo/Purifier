package com.skel.util;

import com.skel.entity.Badpic;
import com.skel.entity.Pic;
import com.skel.entity.Slang;
import com.skel.repository.BadpicRepository;
import com.skel.repository.PicRepository;
import com.skel.repository.SlangRepository;
import lombok.extern.java.Log;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hootting on 2016. 11. 5..
 */
@Log
public class FilterUtil {


    static SlangRepository slangRepository;

    static BadpicRepository badpicRepository;
    public static final String path = "/Users/hootting/test/Purifier";

    @Autowired(required = true)
    public void setSlangRepository(SlangRepository slangRepository) {
        FilterUtil.slangRepository = slangRepository;
    }

    @Autowired(required = true)
    public void setBadpicRepository(BadpicRepository badpicRepository) {
        FilterUtil.badpicRepository = badpicRepository;
    }

    public static String filterSlang(String content) {


        List<Slang> slangs = slangRepository.findAll();

        StringBuilder sb = new StringBuilder();
        for (Slang s : slangs) {
            while (content.contains(s.getWord())) {
                s.setCount(s.getCount()+1);
                slangRepository.saveAndFlush(s);
                for(int i=0; i<s.getWord().length();i++)
                    sb.append("*");
                content=content.replace(s.getWord(),sb.toString());
                sb.delete(0,sb.length());
              }
        }
        return content;
    }

    public static boolean filterPicture(String filename) {
       // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //List<Pic> pics = picRepository.findAll();
        //return pics.stream().anyMatch(p -> filterPic(filename, p.getUrl()));
        List<Badpic> badpics=badpicRepository.findAll();
        return badpics.stream().anyMatch(p->filterPic(filename,p.getUrl()));
    }


    private static boolean filterPic(String filename1, String filename2) {
        /*int ret;
        ret = compareFeature(filename1, filename2);
        if (ret > 0)
            return true;
        else
            return false;*/
        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("img1", filename1);
        map.add("img2", filename2);
        log.info(filename1 + " "+filename2);
        Map<String,Boolean> result = rest.postForObject("http://localhost:9999", map, Map.class);
        System.out.println(result.get("result"));
        return result.get("result");
    }

}
