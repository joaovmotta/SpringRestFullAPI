package br.com.example.SpringBootH2.Repository;

import br.com.example.SpringBootH2.Entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player,Long> {
}
