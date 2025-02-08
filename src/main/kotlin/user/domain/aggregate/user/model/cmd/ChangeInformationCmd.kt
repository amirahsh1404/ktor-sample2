package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.Email
import user.domain.aggregate.user.entity.FullName
import user.domain.aggregate.user.entity.Username

data class ChangeInformationCmd(
    val username: Username,
    val fullName: FullName,
    val email: Email,
)