package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.valueObjects.Password
import user.domain.aggregate.user.entity.valueObjects.Username

data class LoginUserCmd(
    val username: Username,
    val password: Password
)