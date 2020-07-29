package tcg.domain

import tcg.domain.runner.GamePrinter
import tcg.domain.runner.Projection

data class HistoryProjection(val lines: List<String> = emptyList()) : Projection {

    override val name: String = NAME

    override fun evolve(event: Event): Projection {
        val line = when (event) {
            is GameCreated -> "Game created with players ${event.usernames.first} and ${event.usernames.second}"
            is GameStarted -> "Game started with ${event.firstPlayer} as first active player"
            is TurnStarted -> "Active player is now ${event.player}"
//            is DamageDealtWithCard -> TODO()
//            is PlayerKilled -> TODO()
//            is PlayerBleed -> TODO()
//            is PlayerBleedToDeath -> TODO()
            else -> ""
        }
        return HistoryProjection(lines + line)
    }

    override fun printOn(gamePrinter: GamePrinter) {
        lines.forEach {
            gamePrinter.print(it)
        }
    }

    companion object {
        const val NAME = "history projection"
    }

}