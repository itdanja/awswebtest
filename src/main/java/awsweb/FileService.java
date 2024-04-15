package awsweb;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    // 1. 업로드 경로
    String uploadPath = "C:\\Users\\MSI\\awswebtest\\build\\resources\\main\\static\\upload\\";
    // 2. multipartFile 존재하는 파일 업로드
    public String fileUpload(MultipartFile multipartFile ){
        String uuid = UUID.randomUUID().toString(); // UUID란?? 고유한 id 난수성으로 생성
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll( "_" ,"-");
        File file = new File( uploadPath + filename );
        try {   multipartFile.transferTo(file);}
        catch ( Exception e ){   System.out.println("e = " + e); return null;   }
        return filename;
    }
}