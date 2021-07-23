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

package org.elasticsearch.circecodecs.ccr.follow_info

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.types.common.{ IndexName, Name }

@JsonCodec case class FollowerIndex(
	follower_index: IndexName, 
	leader_index: IndexName, 
	parameters: FollowerIndexParameters, 
	remote_cluster: Name, 
	status: FollowerIndexStatus
)

object FollowerIndexStatus extends Enumeration {
	type FollowerIndexStatus = Value

	val active = Value(0, "active")
	val paused = Value(1, "paused")
}

implicit val followerIndexStatusDecoder: Decoder[FollowerIndexStatus.Value] = Decoder.decodeEnumeration(FollowerIndexStatus)
implicit val followerIndexStatusEncoder: Encoder[FollowerIndexStatus.Value] = Decoder.encodeEnumeration(FollowerIndexStatus)
import org.elasticsearch.circecodecs.types.Numeric.{ integer }
import org.elasticsearch.circecodecs.types.Time.{ Time }

@JsonCodec case class FollowerIndexParameters(
	max_outstanding_read_requests: integer, 
	max_outstanding_write_requests: integer, 
	max_read_request_operation_count: integer, 
	max_read_request_size: String, 
	max_retry_delay: Time, 
	max_write_buffer_count: integer, 
	max_write_buffer_size: String, 
	max_write_request_operation_count: integer, 
	max_write_request_size: String, 
	read_poll_timeout: Time
)
