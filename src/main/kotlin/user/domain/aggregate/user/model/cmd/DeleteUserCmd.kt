package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.valueObjects.Username


data class DeleteUserCmd(
    val username: Username,
)