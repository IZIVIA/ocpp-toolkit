package fr.simatix.cs.simulator.api.model.getlocallistversion

import fr.simatix.cs.simulator.api.model.Response

data class GetLocalListVersionResp(
    val versionNumber: Int
) : Response
