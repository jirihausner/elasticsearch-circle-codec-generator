package com.converted.elasticsearch.ml.get_trained_models

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._

import com.converted.elasticsearch._ml._types.TrainedModel.{ TrainedModelConfig }
import com.converted.elasticsearch._types.Numeric.{ integer }

@JsonCodec case class Response(
	body: Body
)

object Response {
	@JsonCodec case class Body(
		count: integer, 
		trained_model_configs: Array[TrainedModelConfig]
	)
}

