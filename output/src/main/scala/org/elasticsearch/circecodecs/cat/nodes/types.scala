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

package org.elasticsearch.circecodecs.cat.nodes

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.types.common.{ ByteSize, Id, Name, Type, VersionString }
import org.elasticsearch.circecodecs.types.Numeric.{ Percentage }

@JsonCodec case class NodesRecord(
	id: Id, 
	pid: String, 
	ip: String, 
	port: String, 
	http_address: String, 
	version: VersionString, 
	flavor: String, 
	`type`: Type, 
	build: String, 
	jdk: String, 
	`disk.total`: ByteSize, 
	`disk.used`: ByteSize, 
	`disk.avail`: ByteSize, 
	`disk.used_percent`: Percentage, 
	`heap.current`: String, 
	`heap.percent`: Percentage, 
	`heap.max`: String, 
	`ram.current`: String, 
	`ram.percent`: Percentage, 
	`ram.max`: String, 
	`file_desc.current`: String, 
	`file_desc.percent`: Percentage, 
	`file_desc.max`: String, 
	cpu: String, 
	load_1m: String, 
	load_5m: String, 
	load_15m: String, 
	uptime: String, 
	`node.role`: String, 
	master: String, 
	name: Name, 
	`completion.size`: String, 
	`fielddata.memory_size`: String, 
	`fielddata.evictions`: String, 
	`query_cache.memory_size`: String, 
	`query_cache.evictions`: String, 
	`query_cache.hit_count`: String, 
	`query_cache.miss_count`: String, 
	`request_cache.memory_size`: String, 
	`request_cache.evictions`: String, 
	`request_cache.hit_count`: String, 
	`request_cache.miss_count`: String, 
	`flush.total`: String, 
	`flush.total_time`: String, 
	`get.current`: String, 
	`get.time`: String, 
	`get.total`: String, 
	`get.exists_time`: String, 
	`get.exists_total`: String, 
	`get.missing_time`: String, 
	`get.missing_total`: String, 
	`indexing.delete_current`: String, 
	`indexing.delete_time`: String, 
	`indexing.delete_total`: String, 
	`indexing.index_current`: String, 
	`indexing.index_time`: String, 
	`indexing.index_total`: String, 
	`indexing.index_failed`: String, 
	`merges.current`: String, 
	`merges.current_docs`: String, 
	`merges.current_size`: String, 
	`merges.total`: String, 
	`merges.total_docs`: String, 
	`merges.total_size`: String, 
	`merges.total_time`: String, 
	`refresh.total`: String, 
	`refresh.time`: String, 
	`refresh.external_total`: String, 
	`refresh.external_time`: String, 
	`refresh.listeners`: String, 
	`script.compilations`: String, 
	`script.cache_evictions`: String, 
	`script.compilation_limit_triggered`: String, 
	`search.fetch_current`: String, 
	`search.fetch_time`: String, 
	`search.fetch_total`: String, 
	`search.open_contexts`: String, 
	`search.query_current`: String, 
	`search.query_time`: String, 
	`search.query_total`: String, 
	`search.scroll_current`: String, 
	`search.scroll_time`: String, 
	`search.scroll_total`: String, 
	`segments.count`: String, 
	`segments.memory`: String, 
	`segments.index_writer_memory`: String, 
	`segments.version_map_memory`: String, 
	`segments.fixed_bitset_memory`: String, 
	`suggest.current`: String, 
	`suggest.time`: String, 
	`suggest.total`: String, 
	`bulk.total_operations`: String, 
	`bulk.total_time`: String, 
	`bulk.total_size_in_bytes`: String, 
	`bulk.avg_time`: String, 
	`bulk.avg_size_in_bytes`: String
)
