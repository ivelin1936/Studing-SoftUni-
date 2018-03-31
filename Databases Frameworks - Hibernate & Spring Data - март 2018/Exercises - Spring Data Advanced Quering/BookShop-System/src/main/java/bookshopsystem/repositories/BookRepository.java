package bookshopsystem.repositories;

import bookshopsystem.dto.BookP03Dto;
import bookshopsystem.enums.AgeRestriction;
import bookshopsystem.enums.EditionType;
import bookshopsystem.models.entity.Author;
import bookshopsystem.models.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(Date releaseDate);

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByAuthorAndReleaseDateBefore(Author author, Date date);

    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    @Query(value = "SELECT b.title FROM Book b WHERE b.ageRestriction = :restriction")
    List<String> allBooksByRestriction(@Param("restriction") AgeRestriction restriction);

    @Query(value = "SELECT b.title FROM Book b WHERE b.copies < 5000 AND b.editionType = :edition")
    List<String> allGoldenEditionBooksWithLessThan5000Copies(@Param("edition") EditionType editionType);

    @Query("SELECT new bookshopsystem.dto.BookP03Dto(title, price) FROM Book b WHERE b.price < 5 OR b.price > 40")
    List<BookP03Dto> allBooksByPrice();

    @Query(value = "SELECT b.title FROM Book b WHERE b.releaseDate <> :date")
    List<String> notReleasedBooksOnGivenYear(@Param("date") Date date);
}
