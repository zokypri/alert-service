package se.implementer.alertservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class PoliceWarning (
    @JsonProperty("id") val id: Long,
    @JsonProperty("msg") val msg: String,
    @JsonProperty("warningLevel") val level: Level,
)