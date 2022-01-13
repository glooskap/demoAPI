package demo.spring.service;

import demo.spring.repository.JPAdao;
import demo.spring.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    JPAdao dao;

    public String getAllQuotes() {
        return dao.findAll().toString();
    }

    public String getQuote(int id) {
        return dao.findById(id).toString();
    }

    public int insertQuote(String quote) {
        Quote tmp = new Quote(quote);
        dao.save(tmp);

        return 1;
    }

    public int updateQuote(int id, String quote) {
        Quote tmp = new Quote(quote, id);
        dao.save(tmp);

        return 1;
    }

    public int deleteQuote(int id) {
        dao.deleteById(id);
        return 1;
    }

}
