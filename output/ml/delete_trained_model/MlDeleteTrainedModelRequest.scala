package com.converted.elasticsearch.ml.delete_trained_model

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._

import com.converted.elasticsearch._types.Base.{ RequestBase }
import com.converted.elasticsearch._types.common.{ Id }

@JsonCodec case class Request(
	path_parts: PathParts
) extends RequestBase

object Request {
	@JsonCodec case class PathParts(
		model_id: Id
	)
}
