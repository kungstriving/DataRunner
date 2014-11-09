package main;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.io.filefilter.FileFilterUtils;

/**
 * 
 * @author kongxiaoyang
 * @date 2014年3月9日 下午12:30:21 
 * @version V1.0 
 */
public class CommonsFilesMain {

	public static void main(String[] args) {
		File dir = new File("F:\\Next\\resources\\lib");
		File[] files = dir.listFiles((FileFilter)FileFilterUtils.directoryFileFilter());
//		Collection<File> files = FileUtils.listFilesAndDirs(dir, null, null);
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}
}
