package bookshopsystem.services.interfaces;

import bookshopsystem.dto.BookP03Dto;
import bookshopsystem.enums.AgeRestriction;
import bookshopsystem.enums.EditionType;
import bookshopsystem.models.entity.Author;
import bookshopsystem.models.entity.Book;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookService {

    void saveBookInToDb(Book book);

    List<Book> allTitlesAfterYear(Date year);

    List<Book> allBooksByAuthor(Author author);

    List<Book> allByAuthorAndReleaseDateBefore(Author author, Date date);

    List<Book> allByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    List<String> allBooksByRestriction(AgeRestriction restriction);

    List<String> allGoldenEditionBooksWithLessThan5000Copies(EditionType editionType);

    List<BookP03Dto> allBooksByPrice();

    List<String> notReleasedBooksOnGivenYear(Date date);
}
