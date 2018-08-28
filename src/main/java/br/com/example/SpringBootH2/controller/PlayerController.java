package br.com.example.SpringBootH2.controller;

import br.com.example.SpringBootH2.entity.Player;
import br.com.example.SpringBootH2.request.PlayerRequest;
import br.com.example.SpringBootH2.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "RestFull API using SpringBoot, H2 and Swagger")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/player")
    @ApiOperation(value = "Return a list of players.")
    public HttpEntity<List<Player>> getAll() {
        return ResponseEntity.ok(this.playerService.findAll());
    }

    @GetMapping("/player/{id}")
    @ApiOperation(value = "Return a player according to id.")
    public HttpEntity<Player> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.playerService.findById(id));
    }

    @PostMapping("/player")
    @ApiOperation(value = "Create a new player in database.")
    public HttpEntity insert(@Valid @RequestBody PlayerRequest request) {
        Long persistedId = this.playerService.insert(request);
        return ResponseEntity.created(URI.create("http://localhost:8080/api/player/".concat(persistedId.toString()))).build();
    }

    @DeleteMapping("/player/{id}")
    @ApiOperation(value = "Delete a player from database")
    public HttpEntity delete(@PathVariable Long id) {
        this.playerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/player/{id}")
    @ApiOperation(value = "Update data from a player according to id")
    public HttpEntity update(@PathVariable Long id, @RequestBody Player player) {
        this.playerService.update(player, id);
        return ResponseEntity.ok().build();
    }
}
