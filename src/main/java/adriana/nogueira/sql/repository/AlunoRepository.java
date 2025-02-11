package adriana.nogueira.sql.repository;

import adriana.nogueira.sql.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}