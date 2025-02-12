package user.domain.aggregate.user.model.cmd

import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.FullName
import user.domain.aggregate.user.entity.valueObjects.Username

data class ChangeInformationCmd(
    val username: Username,
    val fullName: FullName,
    val email: Email,
)