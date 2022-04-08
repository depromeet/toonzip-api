package kr.co.antoon.webtoon.infrastructure;

import kr.co.antoon.webtoon.domain.WebtoonGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebtoonGenreRepository extends JpaRepository<WebtoonGenre, Long> {
}