package de.asideas.spring.reactivespring.api

import de.asideas.spring.reactivespring.model.RouletteRound
import de.asideas.spring.reactivespring.model.RouletteRoundRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/roulette")
class RouletteController @Autowired constructor(
    private val rouletteRoundRepository: RouletteRoundRepository
) {

    @GetMapping
    private fun getRoulettes(): Flux<RouletteRound> {
        return rouletteRoundRepository.findAll()
    }

    @GetMapping("/{id}")
    private fun getRoulettes(@PathVariable("id") id: Long): Mono<RouletteRound> {
        return rouletteRoundRepository.findById(id)
    }

    @PostMapping
    private fun create(@RequestBody round: RouletteRoundCreationCmd): Mono<RouletteRound> {
        val entity = RouletteRound.newRound(round.appointment, round.participants)
        return rouletteRoundRepository.save(entity)
    }

    @PutMapping
    private fun update(@RequestBody round: RouletteRoundUpdateCmd): Mono<RouletteRound> {
        val entity = RouletteRound.newRound(round.appointment, round.participants)
        return rouletteRoundRepository.save(entity)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun deleteById(@PathVariable("id") id: Long) {
        rouletteRoundRepository.deleteById(id)
    }

    data class RouletteRoundCreationCmd(
        val appointment: LocalDateTime?,
        val participants: List<String>
    )

    data class RouletteRoundUpdateCmd(
        val id: Long,
        val appointment: LocalDateTime,
        val participants: List<String>
    )

}


