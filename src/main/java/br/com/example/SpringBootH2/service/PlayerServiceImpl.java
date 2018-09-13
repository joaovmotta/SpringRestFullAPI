package br.com.example.SpringBootH2.service;

import br.com.example.SpringBootH2.infra.exception.ResourceNotFoundException;
import br.com.example.SpringBootH2.mapper.PlayerMapper;
import br.com.example.SpringBootH2.repository.PlayerRepository;
import br.com.example.SpringBootH2.representation.entity.Player;
import br.com.example.SpringBootH2.representation.request.PlayerRequest;
import br.com.example.SpringBootH2.representation.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Transactional(readOnly = true)
    public List<PlayerResponse> findAll() {
        return this.playerMapper.playerToResponse((List<Player>) this.playerRepository.findAll());
    }

    @Transactional
    public Long insert(PlayerRequest player) {
        return this.playerRepository.save(this.playerMapper.requestToPlayer(player)).getId();
    }

    @Transactional(readOnly = true)
    public Player findById(Long id) {
        return this.playerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Transactional
    public void delete(Long id) {
        this.playerRepository.deleteById(id);
    }

    @Transactional
    public void update(PlayerRequest player, Long id) {
        if (this.playerRepository.existsById(id)) {
            Player playerEntity = this.playerMapper.requestToPlayer(player);
            playerEntity.setId(id);
            this.playerRepository.save(playerEntity);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
