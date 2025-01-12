package com.runtracker.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStorageUtils {

   private final Path fileStorageLocation;

   public FileStorageUtils() {
      // Ganti dengan path absolut jika relatif tidak bekerja
      String uploadDir = "C:\\Users\\aderi\\OneDrive\\Documents\\PROJECT\\UNPAR\\TUBES-PBW\\runtracker\\src\\main\\resources\\static\\image";
      this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
      try {
         Files.createDirectories(this.fileStorageLocation);
      } catch (IOException ex) {
         throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
      }
   }

   public String storeFile(MultipartFile file) throws IOException {
      String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
      String fileName = UUID.randomUUID().toString() + (fileExtension != null ? "." + fileExtension : "");

      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      try {
         Files.copy(file.getInputStream(), targetLocation);
      } catch (IOException e) {
         throw new IOException("Failed to store file " + fileName, e);
      }

      return fileName;
   }
}
