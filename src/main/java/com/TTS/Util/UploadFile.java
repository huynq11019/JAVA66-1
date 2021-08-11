package com.TTS.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class UploadFile {
//    @Autowired
//    private ServletContext context;
    public static String uploadDirectory = System.getProperty("user.dir");
    private Path getPath(String folDer, String fileName) {
//        File dir = new File(context.getRealPath(folDer));
        File dir = new File(uploadDirectory + "/"+folDer);
        if (!dir.exists()) { // tạo nếu chưa tồn tại
            dir.mkdir();
        }
        return Paths.get(dir.getAbsolutePath(), fileName);
    }

    public File uploadUtil(String folder,MultipartFile attach) {
//        File dir = new File(context.getRealPath(folder));
        File dir = new File(uploadDirectory + folder);
        if (!dir.exists()) { // tạo nếu chưa tồn tại
            dir.mkdir();
        }
        try {
            String fileName = attach.getOriginalFilename();
            File fileNew = new File(uploadDirectory+folder +"/"+ fileName);
            attach.transferTo(fileNew);
            return fileNew;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public byte[] getFile(String folder, String fileName) {
        try {
//            log.info("file path: " + fileName);
//            File file = new File(context.getRealPath(đuongan));
            Path path = this.getPath(folder, fileName);
//            log.info("path: "+path);
            return Files.readAllBytes(path);
        } catch (IOException exx) {
            log.error(exx.getMessage());
            throw new RuntimeException(exx);
        }
    }

    public void deleteFle(String folder, String fileName) {
        Path path = this.getPath(folder, fileName);
        path.toFile().delete();
    }
    //list
}
