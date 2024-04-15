package awsweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @Autowired BoardEntityRepository boardEntityRepository;

    @PostMapping("/board/save")
    public boolean boardSave ( BoardDto boardDto ){

        // 2. DB처리
        BoardEntity boardEntity = BoardEntity.builder()
                .bcontent( boardDto.getBcontent() )
                .build();
        boardEntityRepository.save( boardEntity );
        // 3.
        return true;
    }
}
