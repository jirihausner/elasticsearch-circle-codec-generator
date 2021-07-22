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

package org.elasticsearch.circecodecs.indices.get_data_stream

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs._indices._types.DataStreamStatus.{ DataStreamHealthStatus }
import org.elasticsearch.circecodecs._types.common.{ DataStreamName, Field, IndexName, Metadata, Name, Uuid }
import org.elasticsearch.circecodecs._types.Numeric.{ integer }

@JsonCodec case class Response(
	body: Body
)

object Response {
	@JsonCodec case class Body(
		data_streams: Seq[IndicesGetDataStreamItem]
	)
}


@JsonCodec case class IndicesGetDataStreamItem(
	name: DataStreamName, 
	timestamp_field: IndicesGetDataStreamItemTimestampField, 
	indices: Seq[IndicesGetDataStreamItemIndex], 
	generation: integer, 
	template: Name, 
	hidden: Boolean, 
	system: Boolean, 
	status: DataStreamHealthStatus, 
	ilm_policy: Name, 
	_meta: Metadata
)

@JsonCodec case class IndicesGetDataStreamItemTimestampField(
	name: Field
)

@JsonCodec case class IndicesGetDataStreamItemIndex(
	index_name: IndexName, 
	index_uuid: Uuid
)