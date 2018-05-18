package br.com.example.SpringBootH2.Service;

import br.com.example.SpringBootH2.Entity.Player;
import br.com.example.SpringBootH2.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAll(){
        return (List<Player>) this.playerRepository.findAll();
    }

    public void insert(Player player){
        this.playerRepository.save(player);
    }

    public Optional<Player> findById(Long id){
        return this.playerRepository.findById(id);
    }

    public void delete(Long id){
        this.playerRepository.deleteById(id);
    }

    public void update(Player player, Long id){
        player.setId(id);
        this.playerRepository.save(player);
    }
}
