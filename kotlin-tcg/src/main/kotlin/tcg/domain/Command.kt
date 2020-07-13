package tcg.domain

import java.util.*

sealed class Command {
    abstract val aggregateIdentifier: UUID
}

data class CreateGame(
    override val aggregateIdentifier: UUID,
    val usernames: Pair<String, String>
) : Command()

data class StartGame(
    override val aggregateIdentifier: UUID,
    val chooseFirstPlayer: (players: TwoPlayers) -> Player,
    val cardDealer: (Deck, Int) -> Pair<Deck, List<Card>>
) : Command()

data class FirstTurn(
    override val aggregateIdentifier: UUID,
    val cardDealer: (Deck) -> Pair<Deck, List<Card>>
) : Command()

data class DealDamageWithCard(
    override val aggregateIdentifier: UUID,
    val card: Card
) : Command()

data class SwitchPlayer(
    override val aggregateIdentifier: UUID,
    val cardDealer: (Deck) -> Pair<Deck, List<Card>>
) : Command()
