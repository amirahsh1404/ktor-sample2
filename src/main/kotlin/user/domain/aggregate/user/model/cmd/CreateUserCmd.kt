package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.FullName
import user.domain.aggregate.user.entity.valueObjects.Password
import user.domain.aggregate.user.entity.valueObjects.Username

data class CreateUserCmd(
    val username: Username,
    val password: Password,
    val fullName: FullName,
    val email: Email,
)


