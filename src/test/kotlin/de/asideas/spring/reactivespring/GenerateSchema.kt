package de.asideas.spring.reactivespring

import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.tool.hbm2ddl.SchemaExport
import org.hibernate.tool.schema.TargetType
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val settings: MutableMap<String, String> = HashMap()
    settings["hibernate.dialect"] = "org.hibernate.dialect.MySQL8Dialect"
    settings["hibernate.dialect.storage_engine"] = "innodb"
    settings["hibernate.connection.driver_class"] = "com.mysql.jdbc.Driver"
    settings["hibernate.connection.url"] = "jdbc:mysql://localhost:3306/reactive?useSSL=false"
    settings["hibernate.connection.username"] = "root"
    settings["hibernate.connection.password"] = "reactive"
    settings["hibernate.hbm2ddl.auto"] = "create"
    settings["show_sql"] = "true"

    val metadata = MetadataSources(
        StandardServiceRegistryBuilder()
            .applySettings(settings)
            .build()
    )
    //metadata.addAnnotatedClass(de.asideas.spring.reactivespring.model.RouletteRound::class.java)

    val schemaExport = SchemaExport()
    schemaExport.setHaltOnError(true)
    schemaExport.setFormat(true)
    schemaExport.setDelimiter(";")
    schemaExport.setOutputFile("schema.sql")
    schemaExport.execute(EnumSet.of(TargetType.SCRIPT), SchemaExport.Action.CREATE, metadata.buildMetadata())
}
