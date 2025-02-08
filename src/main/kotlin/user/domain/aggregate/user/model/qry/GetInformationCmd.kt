package user.domain.aggregate.user.model.qry

import user.domain.aggregate.user.entity.Password
import user.domain.aggregate.user.entity.Username

data class GetInformationCmd(
    val username: Username,
    val password: Password,
)