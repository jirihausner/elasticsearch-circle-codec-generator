/*
 * Licensed to Elasticsearch B.V. under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch B.V. licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.circecodecs.types.aggregations

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.global.search.types.sort.{ Sort }
import org.elasticsearch.circecodecs.types.common.{ Name, Field }
import org.elasticsearch.circecodecs.types.Numeric.{ integer, double, float }
import org.elasticsearch.circecodecs.types.Scripting.{ Script }
import org.elasticsearch.circecodecs.types.aggregations.{ Aggregation }

@JsonCodec case class PipelineAggregationBase(
	buckets_path: BucketsPath, 
	format: String, 
	gap_policy: GapPolicy
) extends Aggregation

object GapPolicy extends Enumeration {
	type GapPolicy = Value

	val skip = Value(0, "skip")
	val insert_zeros = Value(1, "insert_zeros")
}

implicit val gapPolicyDecoder: Decoder[GapPolicy.Value] = Decoder.decodeEnumeration(GapPolicy)
implicit val gapPolicyEncoder: Encoder[GapPolicy.Value] = Decoder.encodeEnumeration(GapPolicy)

@JsonCodec sealed trait BucketsPath

@JsonCodec case class AverageBucketAggregation extends PipelineAggregationBase

@JsonCodec case class BucketScriptAggregation(
	script: Script
) extends PipelineAggregationBase

@JsonCodec case class BucketSelectorAggregation(
	script: Script
) extends PipelineAggregationBase

@JsonCodec case class BucketSortAggregation(
	from: integer, 
	gap_policy: GapPolicy, 
	size: integer, 
	sort: Sort
) extends Aggregation

@JsonCodec case class CumulativeCardinalityAggregation extends PipelineAggregationBase

@JsonCodec case class CumulativeSumAggregation extends PipelineAggregationBase

@JsonCodec case class DerivativeAggregation extends PipelineAggregationBase

@JsonCodec case class ExtendedStatsBucketAggregation(
	sigma: double
) extends PipelineAggregationBase

@JsonCodec case class InferenceAggregation(
	model_id: Name, 
	inference_config: InferenceConfigContainer
) extends PipelineAggregationBase

@JsonCodec case class InferenceConfigContainer(
	regression: RegressionInferenceOptions, 
	classification: ClassificationInferenceOptions
)

@JsonCodec case class RegressionInferenceOptions(
	results_field: Field, 
	num_top_feature_importance_values: integer
)

@JsonCodec case class ClassificationInferenceOptions(
	num_top_classes: integer, 
	num_top_feature_importance_values: integer, 
	prediction_field_type: String, 
	results_field: String, 
	top_classes_results_field: String
)

@JsonCodec case class MaxBucketAggregation extends PipelineAggregationBase

@JsonCodec case class MinBucketAggregation extends PipelineAggregationBase

@JsonCodec case class MovingAverageAggregation(
	minimize: Boolean, 
	model: MovingAverageModel, 
	settings: MovingAverageSettings, 
	predict: integer, 
	window: integer
) extends PipelineAggregationBase

object MovingAverageModel extends Enumeration {
	type MovingAverageModel = Value

	val linear = Value("linear")
	val simple = Value("simple")
	val ewma = Value("ewma")
	val holt = Value("holt")
	val holt_winters = Value("holt_winters")
}

implicit val movingAverageModelDecoder: Decoder[MovingAverageModel.Value] = Decoder.decodeEnumeration(MovingAverageModel)
implicit val movingAverageModelEncoder: Encoder[MovingAverageModel.Value] = Decoder.encodeEnumeration(MovingAverageModel)
type MovingAverageSettings = EwmaModelSettings | HoltLinearModelSettings | HoltWintersModelSettings

@JsonCodec case class EwmaModelSettings(
	alpha: float
)

@JsonCodec case class HoltLinearModelSettings(
	alpha: float, 
	beta: float
)

@JsonCodec case class HoltWintersModelSettings(
	alpha: float, 
	beta: float, 
	gamma: float, 
	pad: Boolean, 
	period: integer, 
	`type`: HoltWintersType
)

object HoltWintersType extends Enumeration {
	type HoltWintersType = Value

	val add = Value("add")
	val mult = Value("mult")
}

implicit val holtWintersTypeDecoder: Decoder[HoltWintersType.Value] = Decoder.decodeEnumeration(HoltWintersType)
implicit val holtWintersTypeEncoder: Encoder[HoltWintersType.Value] = Decoder.encodeEnumeration(HoltWintersType)

@JsonCodec case class MovingFunctionAggregation(
	script: String, 
	shift: integer, 
	window: integer
) extends PipelineAggregationBase

@JsonCodec case class MovingPercentilesAggregation(
	window: integer, 
	shift: integer
) extends PipelineAggregationBase

@JsonCodec case class NormalizeAggregation(
	method: NormalizeMethod
) extends PipelineAggregationBase

object NormalizeMethod extends Enumeration {
	type NormalizeMethod = Value

	val rescale_0_1 = Value("rescale_0_1")
	val rescale_0_100 = Value("rescale_0_100")
	val percent_of_sum = Value("percent_of_sum")
	val mean = Value("mean")
	val zscore = Value("zscore")
	val softmax = Value("softmax")
}

implicit val normalizeMethodDecoder: Decoder[NormalizeMethod.Value] = Decoder.decodeEnumeration(NormalizeMethod)
implicit val normalizeMethodEncoder: Encoder[NormalizeMethod.Value] = Decoder.encodeEnumeration(NormalizeMethod)

@JsonCodec case class PercentilesBucketAggregation(
	percents: Seq[double]
) extends PipelineAggregationBase

@JsonCodec case class SerialDifferencingAggregation(
	lag: integer
) extends PipelineAggregationBase

@JsonCodec case class StatsBucketAggregation extends PipelineAggregationBase

@JsonCodec case class SumBucketAggregation extends PipelineAggregationBase
