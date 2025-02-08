package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.Email
import user.domain.aggregate.user.entity.FullName
import user.domain.aggregate.user.entity.Password
import user.domain.aggregate.user.entity.Username

data class CreateUserCmd(
    val username: Username,
    val password: Password,
    val fullName: FullName,
    val email: Email,
)


