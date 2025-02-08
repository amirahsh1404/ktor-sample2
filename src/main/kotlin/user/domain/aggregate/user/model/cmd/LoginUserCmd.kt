package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.Password
import user.domain.aggregate.user.entity.Username

data class LoginUserCmd(
    val username: Username,
    val password: Password
)