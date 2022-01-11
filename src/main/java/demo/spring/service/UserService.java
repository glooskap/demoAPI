package demo.spring.service;

import demo.spring.data.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private DataAccess dao;

    @Autowired
    public UserService(DataAccess dao) {
        this.dao = dao;
    }

    public String getAllQuotes() {
        return dao.selectAll();
    }

    public String getQuote(int id) {
        return dao.selectQuote(id);
    }

    public int insertQuote(String quote) {
        return dao.insertQuote(quote);
    }

    public int updateQuote(int id, String quote) {return dao.updateQuote(id, quote);}

    public int deleteQuote(int id) {
        return dao.deleteQuote(id);
    }

}
