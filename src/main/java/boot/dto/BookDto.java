package boot.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private Double price;
    private String description;
    private String coverImage;
}
