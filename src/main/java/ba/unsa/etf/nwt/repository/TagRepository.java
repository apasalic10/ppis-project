package ba.unsa.etf.nwt.repository;

import ba.unsa.etf.nwt.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
