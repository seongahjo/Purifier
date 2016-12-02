package com.skel;

import com.skel.util.FilterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurifierApplicationTests {

	@Test
	public void contextLoads() {
		System.load("/usr/local/Cellar/opencv3/3.1.0_4/share/OpenCV/java/libopencv_java310.dylib");
		System.out.println(FilterUtil.filterPicture("tmp/testtest.jpeg"));
	}

}
