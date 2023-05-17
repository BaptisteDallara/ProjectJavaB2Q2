package Test;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestBookModel {
    private Exemplar exemplar;

    @BeforeEach
    public void setUp(){
        Genre genre = new Genre("genre");
        Type type = new Type("type");
        Language language = new Language("language");
        Serie serie = new Serie("serie");
        Country country = new Country("country");
        Edition edition = new Edition("edition",country);
        Book book = new Book("title", LocalDate.now(),12,false,genre,type,language,serie,edition);
        exemplar = new Exemplar(book,language,120,12.25,12.98,new Status("New"),new Storage(1,2,3));
    }

    @Test
    public void testGetExemplarId(){
        assert(exemplar.getExemplarId() == null);
        assertNull(exemplar.getExemplarId());
    }

    @Test
    public void testGetNbPages(){
        assert(exemplar.getNbPages() == 120);
        assertNotNull(exemplar.getNbPages());
    }

    @Test
    public void testGetLending(){
        assert(exemplar.getLending() == null);
        assertNull(exemplar.getLending());
    }

    @Test
    public void testGetState(){
        assert(exemplar.getState() != null);
        assertNotNull(exemplar.getState());
    }

    @Test
    public void testGetStateString(){
        assert(exemplar.getStateString() != null);
        assertEquals(exemplar.getStateString(),"New");
        assertNotNull(exemplar.getStateString());
    }

    @Test
    public void testGetLanguage(){
        assert(exemplar.getLanguage() != null);
        assertNotNull(exemplar.getLanguage());
    }

    @Test
    public void testGetLanguageName(){
        assert(exemplar.getLanguageName() != null);
        assertEquals(exemplar.getLanguageName(),"language");
        assertNotNull(exemplar.getLanguageName());
    }

    @Test
    public void testGetBook(){
        assert(exemplar.getBook() != null);
        assertNotNull(exemplar.getBook());
    }

    @Test
    public void testGetStorage(){
        assert(exemplar.getStorage() != null);
        assertNotNull(exemplar.getStorage());
    }

    @Test
    public void testSetExemplarId(){
        exemplar.setExemplarId(1);
        assert(exemplar.getExemplarId() == 1);
        assertNotNull(exemplar.getExemplarId());
    }

    @Test
    public void testSetLending(){
        Borrower borrower = new Borrower("Bob","Marley");
        Lending lending = new Lending(borrower,LocalDate.now(),LocalDate.now().plusDays(15));
        exemplar.setLending(lending);
        assert(exemplar.getLending() != null);
        assertNotNull(exemplar.getLending());
    }


}
