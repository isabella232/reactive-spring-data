package de.asideas.spring.reactivespring

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@SpringBootApplication
@EnableR2dbcRepositories
class ReactiveSpringApplication {

	@Bean
	fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer? {
		val initializer = ConnectionFactoryInitializer()
		initializer.setConnectionFactory(connectionFactory)

		val populator = CompositeDatabasePopulator()
		populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("schema.sql")))

		initializer.setDatabasePopulator(populator)
		return initializer
	}

}

fun main(args: Array<String>) {
	runApplication<ReactiveSpringApplication>(*args)
}
