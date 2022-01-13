package demo.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quotes")
public class Quote {

    @Id
    private int id;
    private String quote;

    public Quote() {
    }

    public Quote(String quote, int id) {
        this.quote = quote;
        this.id = id;
    }

    public Quote(String quote) {
        this.quote = quote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tid: " + id +
                "\n\tquote: " + quote +
                "\n}";
    }
}
