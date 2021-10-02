package pl.practise.pokemonapp.pokemonDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practise.pokemonapp.pokemonList.Pokemon;
import pl.practise.pokemonapp.pokemonList.PokemonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonDetailsService {
    private final PokemonDetailsNetworkRepository pokemonDetailsNetworkRepository;
    private final PokemonRepository pokemonRepository;
    private final PokemonDetailsTransformer pokemonDetailsTransformer;

    @Autowired
    public PokemonDetailsService(PokemonDetailsNetworkRepository pokemonDetailsNetworkRepository,
                                 PokemonRepository pokemonRepository,
                                 PokemonDetailsTransformer pokemonDetailsTransformer) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonDetailsNetworkRepository = pokemonDetailsNetworkRepository;
        this.pokemonDetailsTransformer = pokemonDetailsTransformer;
    }

    public List<PokemonDetails> getListOfPokemonDetails(String pokemonNames) {
        String[] names = pokemonNames.split(",");
        return Arrays.stream(names).map(name -> {
            return getPokemonDetails(name);
        }).collect(Collectors.toList());
    }


    public PokemonDetails getPokemonDetails(String pokemonName) {
        Pokemon pokemon = pokemonRepository.findByName(pokemonName)
                .orElseThrow(() -> new NoPokemonFoundException(pokemonName));
        PokemonDetailsResponse pokemonDetailsResponse = pokemonDetailsNetworkRepository.fetchPokemonDetails(pokemon.getId());
        return pokemonDetailsTransformer.toEntity(pokemonDetailsResponse);
    }

}

