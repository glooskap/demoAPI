package demo.spring.service;

import demo.spring.repository.JPAdao;
import demo.spring.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    JPAdao dao;

    public UserService (JPAdao dao) {
        this.dao = dao;
    }

    public String getAllQuotes() {
        List<Quote> ans = dao.findAll();
        if ((long) ans.size() < 1) return null;

        return ans.toString();
    }

    public String getQuote(int id) {
        if (!dao.existsById(id)) return null;

        return dao.findById(id).toString();
    }

    public int insertQuote(String quote) {
        Quote tmp = new Quote(quote);
        dao.save(tmp);

        return 1;
    }

    public int updateQuote(int id, String quote) {
        if (!dao.existsById(id)) return -1;

        Quote tmp = new Quote(quote, id);
        dao.save(tmp);

        if (!dao.findById(id).get().getQuote().equals(quote) ) return -1;
        return 1;
    }

    public int deleteQuote(int id) {
        if (!dao.existsById(id)) return -1;
        dao.deleteById(id);

        return 1;
    }

}
