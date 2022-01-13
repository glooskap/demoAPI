package demo.spring.repository;

import demo.spring.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public interface JPAdao extends JpaRepository<Quote, Integer> {
//configured in application properties
}
