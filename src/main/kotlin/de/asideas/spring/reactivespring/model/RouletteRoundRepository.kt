package de.asideas.spring.reactivespring.model

import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

@Repository
interface RouletteRoundRepository : R2dbcRepository<RouletteRound, Long>