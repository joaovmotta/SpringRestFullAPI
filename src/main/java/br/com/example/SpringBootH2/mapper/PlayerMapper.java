package br.com.example.SpringBootH2.mapper;

import br.com.example.SpringBootH2.entity.Player;
import br.com.example.SpringBootH2.request.PlayerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Player requestToPlayer(PlayerRequest request);
}
