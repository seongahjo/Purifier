package com.skel.util;

import com.skel.entity.Pic;
import com.skel.entity.Slang;
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

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by hootting on 2016. 11. 5..
 */
@Log
public class FilterUtil {


    static SlangRepository slangRepository;
    static PicRepository picRepository;

    public static final String path = "/Users/hootting/test/Purifier";

    @Autowired(required = true)
    public void setSlangRepository(SlangRepository slangRepository) {
        FilterUtil.slangRepository = slangRepository;
    }

    @Autowired(required = true)
    public void setPicRepository(PicRepository picRepository) {
        FilterUtil.picRepository = picRepository;
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String filterSlang(String content) {
        List<Slang> slangs = slangRepository.findAll();
        StringBuilder sb = new StringBuilder();
        for (Slang s : slangs) {
            if (content.contains(s.getWord())) {
                int size = content.indexOf(s.getWord());
                sb.append(content.substring(0, size));
                for (int i = 0; i < s.getWord().length(); i++)
                    sb.append("*");
                sb.append(content.substring(size + s.getWord().length(), content.length() - 1));
            }
        }
        if (sb.toString().equals(""))
            return content;
        return sb.toString();
    }

    public static boolean filterPicture(String filename) {
       // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //List<Pic> pics = picRepository.findAll();
        //return pics.stream().anyMatch(p -> filterPic(filename, p.getUrl()));
        return true;
    }


    private static boolean filterPic(String filename1, String filename2) {
        int ret;
        ret = compareFeature(filename1, filename2);
        if (ret > 0)
            return true;
        else
            return false;
    }

    private static int compareFeature(String filename1, String filename2) {
        int retVal = 0;
        Mat imgO1 = Imgcodecs.imread(filename1, Imgcodecs.CV_LOAD_IMAGE_COLOR);
        Mat imgO2 = Imgcodecs.imread(filename2, Imgcodecs.CV_LOAD_IMAGE_COLOR);

        Imgproc.cvtColor(imgO1, imgO1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(imgO2, imgO2, Imgproc.COLOR_BGR2GRAY);

        Mat img1 = new Mat();
        Mat img2 = new Mat();

        Imgproc.equalizeHist(imgO1, img1);
        Imgproc.equalizeHist(imgO2, img2);

        //Imgproc.cvtColor(img1, img1, Imgproc.COLOR_GRAY2BGR);
        //Imgproc.cvtColor(img2, img2, Imgproc.COLOR_GRAY2BGR);

        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();

        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();

        FeatureDetector detector = FeatureDetector.create(FeatureDetector.ORB);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

        detector.detect(img1, keypoints1);
        detector.detect(img2, keypoints2);

        extractor.compute(img1, keypoints1, descriptors1);
        extractor.compute(img2, keypoints2, descriptors2);

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

        MatOfDMatch matches = new MatOfDMatch();

        if (descriptors2.cols() == descriptors1.cols()) {
            matcher.match(descriptors1, descriptors2, matches);

            DMatch[] match = matches.toArray();
            double max_dist = 0;
            double min_dist = 100;

            for (int i = 0; i < descriptors1.rows(); i++) {
                if (match[i].distance <= 10) {
                    retVal++;
                }
            }
        }
        return retVal;
    }
}
