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

package org.elasticsearch.circecodecs.global.search

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.spec_utils.Dictionary.{ Dictionary }
import org.elasticsearch.circecodecs.spec_utils.UserDefinedValue.{ UserDefinedValue }
import org.elasticsearch.circecodecs.types.aggregations.Aggregate.{ Aggregate }
import org.elasticsearch.circecodecs.types.common.{ AggregateName, Id, ScrollId, SuggestionName }
import org.elasticsearch.circecodecs.types.Numeric.{ double, long }
import org.elasticsearch.circecodecs.types.Stats.{ ClusterStatistics, ShardStatistics }
import org.elasticsearch.circecodecs.global.search.{ HitsMetadata }
import org.elasticsearch.circecodecs.global.search.{ Profile }
import org.elasticsearch.circecodecs.global.search.{ Suggest }

@JsonCodec case class Response[TDocument](
	body: Body
)

object Response {
	@JsonCodec case class Body(
		took: long, 
		timed_out: Boolean, 
		_shards: ShardStatistics, 
		hits: HitsMetadata[TDocument], 
		aggregations: Dictionary[AggregateName, Aggregate], 
		_clusters: ClusterStatistics, 
		documents: Seq[TDocument], 
		fields: Dictionary[String, UserDefinedValue], 
		max_score: double, 
		num_reduce_phases: long, 
		profile: Profile, 
		pit_id: Id, 
		_scroll_id: ScrollId, 
		suggest: Dictionary[SuggestionName, Seq[Suggest[TDocument]]], 
		terminated_early: Boolean
	)
}
