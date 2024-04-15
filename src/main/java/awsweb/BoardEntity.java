package awsweb;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "board")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bno;
    public String bcontent;
    private String bfile;
}
