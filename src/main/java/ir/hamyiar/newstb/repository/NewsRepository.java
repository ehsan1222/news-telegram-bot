package ir.hamyiar.newstb.repository;

import ir.hamyiar.newstb.dao.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {

}
