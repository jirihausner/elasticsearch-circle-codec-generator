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

package org.elasticsearch.circecodecs.cluster.stats

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.spec_utils.Dictionary.{ Dictionary }
import org.elasticsearch.circecodecs.types.common.{ Name, VersionString }
import org.elasticsearch.circecodecs.types.Numeric.{ double, integer, long }
import org.elasticsearch.circecodecs.types.Stats.{ CompletionStats, DocStats, FielddataStats, QueryCacheStats, SegmentsStats, StoreStats, PluginStats }

@JsonCodec case class ClusterFileSystem(
	available_in_bytes: long, 
	free_in_bytes: long, 
	total_in_bytes: long
)

@JsonCodec case class ClusterIndicesShardsIndex(
	primaries: ClusterShardMetrics, 
	replication: ClusterShardMetrics, 
	shards: ClusterShardMetrics
)

@JsonCodec case class ClusterIndicesShards(
	index: ClusterIndicesShardsIndex, 
	primaries: double, 
	replication: double, 
	total: double
)

@JsonCodec case class ClusterIndices(
	completion: CompletionStats, 
	count: long, 
	docs: DocStats, 
	fielddata: FielddataStats, 
	query_cache: QueryCacheStats, 
	segments: SegmentsStats, 
	shards: ClusterIndicesShards, 
	store: StoreStats, 
	mappings: FieldTypesMappings, 
	analysis: CharFilterTypes, 
	versions: Seq[IndicesVersions]
)

@JsonCodec case class FieldTypesMappings(
	field_types: Seq[FieldTypes], 
	runtime_field_types: Seq[RuntimeFieldTypes]
)

@JsonCodec case class FieldTypes(
	name: Name, 
	count: integer, 
	index_count: integer, 
	script_count: integer
)

@JsonCodec case class RuntimeFieldTypes(
	name: Name, 
	count: integer, 
	index_count: integer, 
	scriptless_count: integer, 
	shadowed_count: integer, 
	lang: Seq[String], 
	lines_max: integer, 
	lines_total: integer, 
	chars_max: integer, 
	chars_total: integer, 
	source_max: integer, 
	source_total: integer, 
	doc_max: integer, 
	doc_total: integer
)

@JsonCodec case class CharFilterTypes(
	char_filter_types: Seq[FieldTypes], 
	tokenizer_types: Seq[FieldTypes], 
	filter_types: Seq[FieldTypes], 
	analyzer_types: Seq[FieldTypes], 
	built_in_char_filters: Seq[FieldTypes], 
	built_in_tokenizers: Seq[FieldTypes], 
	built_in_filters: Seq[FieldTypes], 
	built_in_analyzers: Seq[FieldTypes]
)

@JsonCodec case class IndicesVersions(
	index_count: integer, 
	primary_shard_count: integer, 
	total_primary_bytes: long, 
	version: VersionString
)

@JsonCodec case class ClusterIngest(
	number_of_pipelines: integer, 
	processor_stats: Dictionary[String, ClusterProcessor]
)

@JsonCodec case class ClusterJvm(
	max_uptime_in_millis: long, 
	mem: ClusterJvmMemory, 
	threads: long, 
	versions: Seq[ClusterJvmVersion]
)

@JsonCodec case class ClusterJvmMemory(
	heap_max_in_bytes: long, 
	heap_used_in_bytes: long
)

@JsonCodec case class ClusterJvmVersion(
	bundled_jdk: Boolean, 
	count: integer, 
	using_bundled_jdk: Boolean, 
	version: VersionString, 
	vm_name: String, 
	vm_vendor: String, 
	vm_version: VersionString
)

@JsonCodec case class ClusterNetworkTypes(
	http_types: Dictionary[String, integer], 
	transport_types: Dictionary[String, integer]
)

@JsonCodec case class ClusterNodeCount(
	coordinating_only: integer, 
	data: integer, 
	ingest: integer, 
	master: integer, 
	total: integer, 
	voting_only: integer, 
	data_cold: integer, 
	data_frozen: integer, 
	data_content: integer, 
	data_warm: integer, 
	data_hot: integer, 
	ml: integer, 
	remote_cluster_client: integer, 
	transform: integer
)

@JsonCodec case class ClusterNodes(
	count: ClusterNodeCount, 
	discovery_types: Dictionary[String, integer], 
	fs: ClusterFileSystem, 
	ingest: ClusterIngest, 
	jvm: ClusterJvm, 
	network_types: ClusterNetworkTypes, 
	os: ClusterOperatingSystem, 
	packaging_types: Seq[NodePackagingType], 
	plugins: Seq[PluginStats], 
	process: ClusterProcess, 
	versions: Seq[VersionString]
)

@JsonCodec case class ClusterOperatingSystemArchitecture(
	count: integer, 
	arch: String
)

@JsonCodec case class ClusterOperatingSystem(
	allocated_processors: integer, 
	available_processors: integer, 
	mem: OperatingSystemMemoryInfo, 
	names: Seq[ClusterOperatingSystemName], 
	pretty_names: Seq[ClusterOperatingSystemName], 
	architectures: Seq[ClusterOperatingSystemArchitecture]
)

@JsonCodec case class ClusterOperatingSystemName(
	count: integer, 
	name: Name
)

@JsonCodec case class ClusterProcess(
	cpu: ClusterProcessCpu, 
	open_file_descriptors: ClusterProcessOpenFileDescriptors
)

@JsonCodec case class ClusterProcessCpu(
	percent: integer
)

@JsonCodec case class ClusterProcessOpenFileDescriptors(
	avg: long, 
	max: long, 
	min: long
)

@JsonCodec case class ClusterProcessor(
	count: long, 
	current: long, 
	failed: long, 
	time_in_millis: long
)

@JsonCodec case class ClusterShardMetrics(
	avg: double, 
	max: double, 
	min: double
)

@JsonCodec case class NodePackagingType(
	count: integer, 
	flavor: String, 
	`type`: String
)

@JsonCodec case class OperatingSystemMemoryInfo(
	free_in_bytes: long, 
	free_percent: integer, 
	total_in_bytes: long, 
	used_in_bytes: long, 
	used_percent: integer
)
