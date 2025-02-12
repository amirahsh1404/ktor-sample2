package user.domain.aggregate.user.model.qry

import user.domain.aggregate.user.entity.valueObjects.Password
import user.domain.aggregate.user.entity.valueObjects.Username

data class GetInformationCmd(
    val username: Username,
    val password: Password,
)