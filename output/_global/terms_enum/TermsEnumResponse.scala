package com.converted.elasticsearch._global.terms_enum

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._

import com.converted.elasticsearch._types.Stats.{ ShardStatistics }

@JsonCodec case class Response(
	body: Body
)

object Response {
	@JsonCodec case class Body(
		_shards: ShardStatistics, 
		terms: Array[String], 
		complete: Boolean
	)
}

