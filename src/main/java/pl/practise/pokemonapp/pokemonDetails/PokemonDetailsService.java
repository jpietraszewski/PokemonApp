package pl.practise.pokemonapp.pokemonDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.practise.pokemonapp.pokemonList.Pokemon;
import pl.practise.pokemonapp.pokemonList.PokemonRepository;

@Service
public class PokemonDetailsService {
    private final PokemonDetailsNetworkRepository pokemonDetailsNetworkRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonDetailsService(PokemonDetailsNetworkRepository pokemonDetailsNetworkRepository, PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonDetailsNetworkRepository = pokemonDetailsNetworkRepository;
    }

    public PokemonDetailsResponse getPokemonDetails(String pokemonName) {
        Pokemon pokemon = pokemonRepository.findByName(pokemonName)
                .orElseThrow(()-> new NoPokemonFoundException(pokemonName));
        return pokemonDetailsNetworkRepository.fetchPokemonDetails(pokemon.getId());
    }

}

