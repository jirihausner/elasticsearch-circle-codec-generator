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

package org.elasticsearch.circecodecs.indices.rollover

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.indices.types.Alias.{ Alias }
import org.elasticsearch.circecodecs.spec_utils.Dictionary.{ Dictionary }
import org.elasticsearch.circecodecs.spec_utils.UserDefinedValue.{ UserDefinedValue }
import org.elasticsearch.circecodecs.types.Base.{ RequestBase }
import org.elasticsearch.circecodecs.types.common.{ IndexAlias, IndexName, WaitForActiveShards }
import org.elasticsearch.circecodecs.types.mapping.TypeMapping.{ TypeMapping }
import org.elasticsearch.circecodecs.types.Time.{ Time }
import org.elasticsearch.circecodecs.indices.rollover.{ RolloverConditions }

@JsonCodec case class Request(
	path_parts: PathParts, 
	query_parameters: QueryParameters, 
	body: Body
) extends RequestBase

object Request {
	@JsonCodec case class PathParts(
		alias: IndexAlias, 
		new_index: IndexName
	)
	@JsonCodec case class QueryParameters(
		dry_run: Boolean, 
		include_type_name: Boolean, 
		master_timeout: Time, 
		timeout: Time, 
		wait_for_active_shards: WaitForActiveShards
	)
	@JsonCodec case class Body(
		aliases: Dictionary[IndexName, Alias], 
		conditions: RolloverConditions, 
		mappings: Dictionary[String, TypeMapping] | TypeMapping, 
		settings: Dictionary[String, UserDefinedValue]
	)
}
