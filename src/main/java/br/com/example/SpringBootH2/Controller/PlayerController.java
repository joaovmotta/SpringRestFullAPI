package br.com.example.SpringBootH2.Controller;

import br.com.example.SpringBootH2.Entity.Player;
import br.com.example.SpringBootH2.Service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api(value = "RestFull API using SpringBoot, H2 and Swagger")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping("/player")
    @ApiOperation(value = "Return a list of players.")
    public List<Player> getAll(){
        return this.playerService.getAll();
    }

    @GetMapping("/player/{id}")
    @ApiOperation(value = "Return a player according to id.")
    public Optional<Player> findById(@PathVariable Long id){
        return this.playerService.findById(id);
    }

    @PostMapping("/player")
    @ApiOperation(value = "Create a new player in database.")
    public void insert(@RequestBody Player player){
        this.playerService.insert(player);
    }

    @DeleteMapping("/player/{id}")
    @ApiOperation(value = "Delete a player from database")
    public void delete(@PathVariable Long id)
    {
        this.playerService.delete(id);
    }

    @PutMapping("/player/{id}")
    @ApiOperation(value = "Update data from a player according to id")
    public void update(@PathVariable Long id, @RequestBody Player player)
    {
        this.playerService.update(player,id);
    }
}
