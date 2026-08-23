package boot.mapper;

import boot.config.MapperConfig;
import boot.dto.BookDto;
import boot.dto.CreateBookRequestDto;
import boot.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toBookDto(Book book);

    Book toBook(CreateBookRequestDto bookDto);

    @Mapping(target = "id", ignore = true)
    void updateBookFromDto(CreateBookRequestDto dto, @MappingTarget Book book);
}
