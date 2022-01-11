package demo.spring.model;


public class Quote {

    private int id;
    private String quote;

    public Quote(String quote, int id) {
        this.quote = quote;
        this.id = id;
    }

    public Quote() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public int getID() {
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
