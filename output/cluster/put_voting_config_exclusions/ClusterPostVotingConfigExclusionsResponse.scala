package com.converted.elasticsearch.cluster.put_voting_config_exclusions

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._

import com.converted.elasticsearch._types.Numeric.{ integer }

@JsonCodec case class Response(
	body: Body
)

object Response {
	@JsonCodec case class Body(
		stub: integer
	)
}
