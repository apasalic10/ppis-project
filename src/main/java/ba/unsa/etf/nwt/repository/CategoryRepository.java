package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
