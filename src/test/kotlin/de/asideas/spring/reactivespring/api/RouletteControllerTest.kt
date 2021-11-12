package de.asideas.spring.reactivespring.api

import de.asideas.spring.reactivespring.model.RouletteRound
import de.asideas.spring.reactivespring.model.RouletteRoundRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import java.time.LocalDateTime

@WebFluxTest(RouletteController::class)
class RouletteControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var rouletteRoundRepository: RouletteRoundRepository

    @Test
    fun getAll_shouldCallPersistenceAndReturnList() {
        val expectedRound = RouletteRound.newRound(LocalDateTime.now(), listOf("foobar"))
        Mockito.doReturn(Flux.just(expectedRound)).`when`(rouletteRoundRepository).findAll()

        val responseBody = webTestClient
            .get()
            .uri("/api/roulette")
            .exchange()
            .expectStatus().isOk
            .expectBody(String::class.java).returnResult().responseBody

        assertThat(responseBody).contains(expectedRound.getParticipants().first())
    }

}