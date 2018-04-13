package guava.test;

import java.io.File;

import com.google.common.io.Files;

public class FilesTest {

	public static void main(String[] args) {
		System.out.println(Files.isFile().apply(new File("./EzBigDataLicOccupied")));
	}

}
