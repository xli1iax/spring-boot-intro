package boot.service;

import boot.dto.BookDto;
import boot.dto.CreateBookRequestDto;
import boot.exception.EntityNotFoundException;
import boot.mapper.BookMapper;
import boot.model.Book;
import boot.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        System.out.println("DTO: " + bookDto);
        System.out.println("DTO author = " + bookDto.getAuthor());
        Book book = bookMapper.toBook(bookDto);
        return bookMapper.toBookDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Can't find Book with id:" + id)
        );
        return bookMapper.toBookDto(book);
    }
}
