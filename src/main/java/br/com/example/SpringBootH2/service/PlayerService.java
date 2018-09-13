package br.com.example.SpringBootH2.service;

import br.com.example.SpringBootH2.representation.entity.Player;
import br.com.example.SpringBootH2.representation.request.PlayerRequest;
import br.com.example.SpringBootH2.representation.response.PlayerResponse;

import java.util.List;

public interface PlayerService {

    public List<PlayerResponse> findAll();

    public Long insert(PlayerRequest player);

    public Player findById(Long id);

    public void delete(Long id);

    public void update(PlayerRequest player, Long id);
}
