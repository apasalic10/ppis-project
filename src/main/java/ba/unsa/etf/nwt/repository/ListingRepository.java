package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    @Query("SELECT l FROM Listing l " +
            "WHERE (:status IS NULL OR l.status = :status) " +
            "AND (:minPrice IS NULL OR l.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR l.price <= :maxPrice) " +
            "AND (:title IS NULL OR LOWER(l.title) LIKE LOWER(CONCAT('%', :title, '%')))")
    List<Listing> filterListings(
            @Param("status") String status,
            @Param("minPrice") Float minPrice,
            @Param("maxPrice") Float maxPrice,
            @Param("title") String title
    );
}
