package de.asideas.spring.reactivespring.conf

import io.r2dbc.spi.ConnectionFactory
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
@EnableR2dbcRepositories
@EnableConfigurationProperties(SqlInitializationProperties::class)
class R2dbcConf {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Bean
    @ConditionalOnProperty(prefix = "spring.sql.init", name = ["mode"], havingValue = "always")
    fun initializer(connectionFactory: ConnectionFactory, props: SqlInitializationProperties): ConnectionFactoryInitializer {
        val populator = ResourceDatabasePopulator(ClassPathResource("schema_${props.platform}.sql"))

        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        initializer.setDatabasePopulator(populator)

        log.info("SQL schema initialization incoming ..")
        return initializer
    }

}