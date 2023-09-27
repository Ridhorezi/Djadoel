package com.djadoel.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class FileUploadUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadUtil.class);

	public static void saveFile(String uploadDirectory, String fileName, MultipartFile multipartFile)
			throws IOException {

		Path uploadPath = Paths.get(uploadDirectory);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException exception) {
			throw new IOException("Could not save file: " + fileName, exception);
		}
	}

	public static void cleanDirectory(String directory) {
		Path directoryPath = Paths.get(directory);

		try {
			Files.list(directoryPath).forEach(file -> {
				if (!Files.isDirectory(file)) {
					try {
						Files.delete(file);
					} catch (IOException exception) {
						LOGGER.error("Could not delete file: " + file);
						System.out.println("Could not delete file: " + file);
					}
				}
			});
		} catch (IOException exception) {
			LOGGER.error("Could not delete file: " + directoryPath);
			System.out.println("Could not list directory: " + directoryPath);
		}
	}

	public static void deleteDirectory(String directory) {
		Path directoryPath = Paths.get(directory);

		try {
			Files.walk(directoryPath).sorted(Comparator.reverseOrder()) // delete files inside the folder
					.map(Path::toFile).forEach(File::delete);

			// delete the folder after all files have been deleted
			Files.delete(directoryPath);
		} catch (IOException exception) {
			System.out.println("Could not delete directory: " + directoryPath);
		}
	}
}
