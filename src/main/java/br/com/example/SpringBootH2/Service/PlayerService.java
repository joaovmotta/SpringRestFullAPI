package br.com.example.SpringBootH2.Service;

import br.com.example.SpringBootH2.Entity.Player;
import br.com.example.SpringBootH2.Exception.PlayerNotFoundException;
import br.com.example.SpringBootH2.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAll() {
        return (List<Player>) this.playerRepository.findAll();
    }

    public Long insert(Player player) {
        return this.playerRepository.save(player).getId();
    }

    public Player findById(Long id) {
        return this.playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    }

    public void delete(Long id) {
        this.playerRepository.deleteById(id);
    }

    public void update(Player player, Long id) {
        player.setId(id);
        this.playerRepository.save(player);
    }
}
