package br.com.example.SpringBootH2.Controller;

import br.com.example.SpringBootH2.Entity.Player;
import br.com.example.SpringBootH2.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping("/player")
    public List<Player> getAll(){
        return this.playerService.getAll();
    }

    @GetMapping("/player/{id}")
    public Optional<Player> findById(@PathVariable Long id){
        return this.playerService.findById(id);
    }

    @PostMapping("/player")
    public void insert(@RequestBody Player player){
        this.playerService.insert(player);
    }

    @DeleteMapping("/player/{id}")
    public void delete(@PathVariable Long id){
        this.playerService.delete(id);
    }

    @PutMapping("/player/{id}")
    public void update(@PathVariable Long id, @RequestBody Player player){
        this.playerService.update(player,id);
    }
}
