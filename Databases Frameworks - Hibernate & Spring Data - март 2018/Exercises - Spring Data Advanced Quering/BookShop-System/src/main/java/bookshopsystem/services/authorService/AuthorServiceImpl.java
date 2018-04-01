package bookshopsystem.services.authorService;

import bookshopsystem.dto.AuthorDto;
import bookshopsystem.models.entity.Author;
import bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public void saveAuthorIntoDb(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public void saveAuthorsInDb(List<Author> authors) {
        this.authorRepository.save(authors);
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> getAllWithBooksBefore(Date date) {
        return this.authorRepository.findAllAuthorsWithBooksBefore(date);
    }

    @Override
    public List<Author> authorsOrderByBooksCount() {
        return this.authorRepository.authorsOrderByBooksCount();
    }

    @Override
    public Author authorByFirstAndLastName(String fName, String lName) {
        return this.authorRepository.findAuthorByFirstNameAndLastName(fName, lName);
    }

    @Override
    public List<Author> findAllByFirstNameEndingWith(String str) {
        return this.authorRepository.findAllByFirstNameEndingWith(str);
    }

    public AuthorDto callStoredProcedure(String fName, String lName){
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery("usp_get_author_books");

        //Define the stored procedure
        query.registerStoredProcedureParameter("f_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("l_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("first_name", String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("last_name", String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("books_count", Long.class, ParameterMode.OUT);

        //Set input params
        query.setParameter("f_name", fName);
        query.setParameter("l_name", lName);

        //Call the stored procedure and get the result
        query.execute();
        String aFirstName = (String)query.getOutputParameterValue("first_name");
        String aLastName = (String)query.getOutputParameterValue("last_name");
        Long booksCount = (Long) query.getOutputParameterValue("books_count");

        return new AuthorDto(aFirstName, aLastName, booksCount);
    }
}
