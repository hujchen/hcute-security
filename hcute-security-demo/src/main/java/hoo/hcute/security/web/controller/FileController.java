package hoo.hcute.security.web.controller;

import hoo.hcute.security.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private final  String folder = "/Users/hcute/Documents/projects/Myframe/hcute-security/hcute-security-demo/src/main/java/hoo/hcute/security/web/controller";


    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        File localFile = new File(folder,new Date().getTime()+".txt");
        // 传上来的文件写入本地文件
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());

    }

    @GetMapping("/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        try (InputStream is = new FileInputStream(new File(folder,id+".txt"));
             OutputStream os = response.getOutputStream()){
            response.setContentType("application/x-download");
            // 指定下载的文件名
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(is,os);
            os.flush();
        }catch (Exception e){

        }
    }
}
