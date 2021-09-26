package pl.practise.pokemonapp.pokemonDetails;

public class NoPokemonFoundException extends RuntimeException {

    public NoPokemonFoundException(String pokemonName) {
        super(String.format("No pokemon with %s name found!", pokemonName));
    }

}
