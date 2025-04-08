package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingCategoryRepository extends JpaRepository<Category, Long> {
}
