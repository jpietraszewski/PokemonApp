package pl.practise.pokemonapp.pokemonList;

import org.springframework.stereotype.Component;
import pl.practise.pokemonapp.pokemonDetails.PokemonDetails;

@Component
class PokemonListItemTransformer {

    PokemonListItem toEntity(PokemonDetails pokemonDetails) {
        PokemonListItem pokemonListItem = new PokemonListItem();
        pokemonListItem.setName(pokemonDetails.getName());
        pokemonListItem.setImageUrl(pokemonDetails.getImageUrl());
        pokemonListItem.setUrl("localhost:8092/pokemon?names=" + pokemonDetails.getName());
        return pokemonListItem;
    }
}
