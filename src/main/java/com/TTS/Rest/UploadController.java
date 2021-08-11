package com.TTS.Rest;

import com.TTS.Entity.UploadFileResponse;
import com.TTS.Util.MediaTypeUtil;
import com.TTS.Util.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@CrossOrigin("*")
@RestController
public class UploadController {
    @Autowired
    private UploadFile uploadFileUtil;
    @Autowired
    private ServletContext servletContext;
    public static String uploadDirectory = System.getProperty("user.dir");

    @PostMapping("/api/uploadfile")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestBody MultipartFile uploadFile) throws IOException {
//        UploadFileResponse response = new UploadFileResponse();
//        StringBuilder fileNames = new StringBuilder();
//
////        Path fileNameAndPath = Paths.get(uploadDirectory + "/src/main/resources/static/img", uploadFile.getOriginalFilename());
//        Path fileNameAndPath = Paths.get(servletContext.getRealPath("/img"+ uploadFile.getOriginalFilename()));
//
//        fileNames.append(uploadFile.getOriginalFilename() + " ");
//        try {
//            Files.write(fileNameAndPath, uploadFile.getBytes());
//            response.setFileName(uploadFile.getOriginalFilename());
//            response.setSize(uploadFile.getSize());
//            return ResponseEntity.ok().body(response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
//        return ResponseEntity.badRequest().build();

        File f = uploadFileUtil.uploadUtil("/img/", uploadFile);
        UploadFileResponse response = new UploadFileResponse();
        response.setFileName(f.getName());
        response.setSize(f.length());
        log.info(f.getAbsolutePath());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/file/{folder}/{fileName}")
    public byte[] getFile(@PathVariable("folder") String folder, @PathVariable("fileName") String fileName) {
        return uploadFileUtil.getFile(folder, fileName);
    }

    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(name = "f", required = true) String fileName) {

        MediaType mediaType = MediaTypeUtil.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);
//        ExcelUtil.writeExcel(userDAO	.loadAll(0, 100, "id", true), servletContext.getRealPath("/docs/" + fileName));
//		ExcelUtil.createFileExcel(userDAO.loadAll(0, 1000, "id", null));
//		File file = new File(servletContext.getRealPath("/docs/" + fileName));
        Path path = Paths.get(servletContext.getRealPath("/docs/" + fileName));
        byte[] data = null;
//                = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                // Content-Type
                .contentType(mediaType) //
                // Content-Lengh
                .contentLength(data.length) //
                .body(resource);
    }

}
