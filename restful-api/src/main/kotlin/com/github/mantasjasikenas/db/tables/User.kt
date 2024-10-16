package com.github.mantasjasikenas.db.tables

import com.github.mantasjasikenas.model.user.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*


object UsersTable : UUIDTable() {
    val userName = varchar("user_name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val forceRelogin = bool("force_relogin").default(false)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}


class UserDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserDAO>(UsersTable)

    var userName by UsersTable.userName
    var email by UsersTable.email
    var password by UsersTable.password
    var forceRelogin by UsersTable.forceRelogin
    var createdAt by UsersTable.createdAt

    val roles by UserRoleDAO referrersOn UsersRolesTable.userId
}


fun daoToModel(dao: UserDAO) = User(
    id = dao.id.value,
    username = dao.userName,
    email = dao.email,
    roles = dao.roles.map { it.role },
    password = dao.password,
    forceRelogin = dao.forceRelogin
)