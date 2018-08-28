package br.com.example.SpringBootH2.repository;

import br.com.example.SpringBootH2.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
