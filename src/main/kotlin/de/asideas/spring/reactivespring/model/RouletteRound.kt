package de.asideas.spring.reactivespring.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "roulette_round")
class RouletteRound {

    @Id
    @org.springframework.data.annotation.Id
    var id: Long? = null
        private set

    var appointment: LocalDateTime = LocalDateTime.now()

    private var participants: String? = null

    fun addParticipant(participant: String) {
        if (this.participants == null) {
            this.participants = participant
        } else {
            this.participants += ",${participant}"
        }
    }

    fun getParticipants(): List<String> {
        return this.participants?.split(",") ?: listOf()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RouletteRound

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun newRound(appointment: LocalDateTime?, participants: List<String>): RouletteRound {
            val round = RouletteRound()
            round.appointment = appointment ?: LocalDateTime.now()
            participants.forEach { round.addParticipant(it) }
            return round
        }
    }

}
