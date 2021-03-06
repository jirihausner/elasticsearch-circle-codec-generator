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

package org.elasticsearch.circecodecs.watcher.execute_watch

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.watcher.types.Conditions.{ ConditionContainer }
import org.elasticsearch.circecodecs.watcher.types.Execution.{ ExecutionResult, ExecutionStatus }
import org.elasticsearch.circecodecs.watcher.types.Input.{ InputContainer }
import org.elasticsearch.circecodecs.watcher.types.Trigger.{ TriggerEventResult }
import org.elasticsearch.circecodecs.types.common.{ Id, Metadata, Username }

@JsonCodec case class WatchRecord(
	condition: ConditionContainer, 
	input: InputContainer, 
	messages: Seq[String], 
	metadata: Metadata, 
	node: String, 
	result: ExecutionResult, 
	state: ExecutionStatus, 
	trigger_event: TriggerEventResult, 
	user: Username, 
	watch_id: Id
)
