package boot;

import boot.model.Book;
import boot.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootIntroApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BootIntroApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BookService bookService) {
        return args -> {
            Book book = new Book();
            book.setTitle("Kobzar");
            book.setAuthor("Taras Shevchenko");
            book.setDescription("Good book");
            book. setIsbn("978-3-16-148410-0");
            book.setPrice(new BigDecimal("19.99"));
            book.setCoverImage("https://example.com/cover.jpg");

            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
