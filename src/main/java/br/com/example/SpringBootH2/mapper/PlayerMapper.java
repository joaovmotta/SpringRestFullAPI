package br.com.example.SpringBootH2.mapper;

import br.com.example.SpringBootH2.representation.entity.Player;
import br.com.example.SpringBootH2.representation.request.PlayerRequest;
import br.com.example.SpringBootH2.representation.response.PlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Player requestToPlayer(PlayerRequest request);

    List<PlayerResponse> playerToResponse(List<Player> list);
}
