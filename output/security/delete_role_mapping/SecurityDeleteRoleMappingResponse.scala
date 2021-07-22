package com.converted.elasticsearch.security.delete_role_mapping

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._


@JsonCodec case class Response(
	body: Body
)

object Response {
	@JsonCodec case class Body(
		found: Boolean
	)
}
