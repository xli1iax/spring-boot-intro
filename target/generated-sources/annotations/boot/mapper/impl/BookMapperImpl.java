package boot.mapper.impl;

import boot.dto.BookDto;
import boot.dto.CreateBookRequestDto;
import boot.mapper.BookMapper;
import boot.model.Book;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-24T23:28:12+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Homebrew)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto toBookDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        if ( book.getId() != null ) {
            bookDto.setId( book.getId() );
        }
        if ( book.getTitle() != null ) {
            bookDto.setTitle( book.getTitle() );
        }
        if ( book.getAuthor() != null ) {
            bookDto.setAuthor( book.getAuthor() );
        }
        if ( book.getPrice() != null ) {
            bookDto.setPrice( book.getPrice().doubleValue() );
        }
        if ( book.getDescription() != null ) {
            bookDto.setDescription( book.getDescription() );
        }
        if ( book.getCoverImage() != null ) {
            bookDto.setCoverImage( book.getCoverImage() );
        }

        return bookDto;
    }

    @Override
    public Book toBook(CreateBookRequestDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        if ( bookDto.getId() != null ) {
            book.setId( bookDto.getId() );
        }
        if ( bookDto.getTitle() != null ) {
            book.setTitle( bookDto.getTitle() );
        }
        if ( bookDto.getAuthor() != null ) {
            book.setAuthor( bookDto.getAuthor() );
        }
        if ( bookDto.getIsbn() != null ) {
            book.setIsbn( bookDto.getIsbn() );
        }
        if ( bookDto.getPrice() != null ) {
            book.setPrice( BigDecimal.valueOf( bookDto.getPrice() ) );
        }
        if ( bookDto.getDescription() != null ) {
            book.setDescription( bookDto.getDescription() );
        }
        if ( bookDto.getCoverImage() != null ) {
            book.setCoverImage( bookDto.getCoverImage() );
        }

        return book;
    }
}
