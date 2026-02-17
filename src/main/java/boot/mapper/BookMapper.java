package boot.mapper;

import boot.config.MapperConfig;
import boot.dto.BookDto;
import boot.dto.CreateBookRequestDto;
import boot.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toBookDto(Book book);

    Book toBook(CreateBookRequestDto bookDto);
}
