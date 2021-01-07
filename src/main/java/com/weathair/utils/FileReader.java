package com.weathair.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileReader {

	public static List<String> readFile(File file) throws IOException {
		
		List<String> fileLines = FileUtils.readLines(file, "UTF-8");
		
		return fileLines;
	}
}
