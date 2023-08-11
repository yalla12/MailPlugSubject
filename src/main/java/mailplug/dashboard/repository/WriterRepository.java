package mailplug.dashboard.repository;

import mailplug.dashboard.domain.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long> {

    Optional<Writer> findByDisplayName(String displayName);
}
