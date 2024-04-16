package awsweb;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    // AWS 경로
    // 3. AWS S3 리소드 업로드 , 다운로드
    // 업로드[바이트복사해서 이동하는 개념 ]
    // 1. S3 객체 생성
    @Autowired // 그레이들에 implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'
    private AmazonS3Client amazonS3Client;

    // **** application.properties 에 버킷 설정값 가져와서 변수에 저장 [ 서비스에 보완에 관련된 코드 숨기기 ]
    @Value("${cloud.aws.s3.bucket}") // lombok 아님..
    private String bucket ; // application.properties 에 설정한 버킷명 가져오기
    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;// application.properties 에 설정한 버킷 저장 경로

    String s3url;

    // 2. multipartFile 존재하는 파일 업로드
    public String fileUpload(MultipartFile multipartFile ){
        String uuid = UUID.randomUUID().toString();
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll( "_" ,"-");
        // 4.
        try {
            s3url= defaultUrl+filename;
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(multipartFile.getContentType());
            metadata.setContentLength(multipartFile.getSize());
            amazonS3Client.putObject( bucket, filename,multipartFile.getInputStream(),metadata);
        }
        catch ( Exception e ){   System.out.println("e = " + e); return null;   }
        return s3url ;
    }

}