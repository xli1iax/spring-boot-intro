package boot.dto;

import lombok.Data;

@Data
public class CreateBookRequestDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Double price;
    private String description;
    private String coverImage;
}
