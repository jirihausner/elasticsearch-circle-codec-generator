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
// PageDuty ----------------------------- //
package org.elasticsearch.circecodecs.watcher.types

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.types.common.{ HttpHeaders, Id, IndexName, Type, VersionNumber }
import org.elasticsearch.circecodecs.types.Numeric.{ integer }
import org.elasticsearch.circecodecs.types.Result.{ Result }
import org.elasticsearch.circecodecs.types.Time.{ DateString }
import org.elasticsearch.circecodecs.watcher.types.{ HttpInputRequestDefinition }

@JsonCodec case class PagerDutyEvent(
	account: String, 
	attach_payload: Boolean, 
	client: String, 
	client_url: String, 
	context: Seq[PagerDutyContext], 
	description: String, 
	event_type: PagerDutyEventType, 
	incident_key: String
)

@JsonCodec case class PagerDutyContext(
	href: String, 
	src: String, 
	`type`: PagerDutyContextType
)

object PagerDutyContextType extends Enumeration {
	type PagerDutyContextType = Value

	val link = Value(0, "link")
	val image = Value(1, "image")
}

implicit val pagerDutyContextTypeDecoder: Decoder[PagerDutyContextType.Value] = Decoder.decodeEnumeration(PagerDutyContextType)
implicit val pagerDutyContextTypeEncoder: Encoder[PagerDutyContextType.Value] = Decoder.encodeEnumeration(PagerDutyContextType)

object PagerDutyEventType extends Enumeration {
	type PagerDutyEventType = Value

	val trigger = Value(0, "trigger")
	val resolve = Value(1, "resolve")
	val acknowledge = Value(2, "acknowledge")
}

implicit val pagerDutyEventTypeDecoder: Decoder[PagerDutyEventType.Value] = Decoder.decodeEnumeration(PagerDutyEventType)
implicit val pagerDutyEventTypeEncoder: Encoder[PagerDutyEventType.Value] = Decoder.encodeEnumeration(PagerDutyEventType)

@JsonCodec case class PagerDutyActionEventResult(
	event: PagerDutyEvent, 
	reason: String, 
	request: HttpInputRequestResult, 
	response: HttpInputResponseResult
)

@JsonCodec case class PagerDutyResult(
	sent_event: PagerDutyActionEventResult
)

@JsonCodec case class SlackResult(
	account: String, 
	message: SlackMessage
)

@JsonCodec case class SlackAttachment(
	author_icon: String, 
	author_link: String, 
	author_name: String, 
	color: String, 
	fallback: String, 
	fields: Seq[SlackAttachmentField], 
	footer: String, 
	footer_icon: String, 
	image_url: String, 
	pretext: String, 
	text: String, 
	thumb_url: String, 
	title: String, 
	title_link: String, 
	ts: DateString
)

@JsonCodec case class SlackAttachmentField(
	short: Boolean, 
	title: String, 
	value: String
)

@JsonCodec case class SlackDynamicAttachment(
	attachment_template: SlackAttachment, 
	list_path: String
)

@JsonCodec case class SlackMessage(
	attachments: Seq[SlackAttachment], 
	dynamic_attachments: SlackDynamicAttachment, 
	from: String, 
	icon: String, 
	text: String, 
	to: Seq[String]
)

@JsonCodec case class SlackActionMessageResult(
	message: SlackMessage, 
	request: HttpInputRequestResult, 
	response: HttpInputResponseResult
)

object DataAttachmentFormat extends Enumeration {
	type DataAttachmentFormat = Value

	val json = Value(0, "json")
	val yaml = Value(1, "yaml")
}

implicit val dataAttachmentFormatDecoder: Decoder[DataAttachmentFormat.Value] = Decoder.decodeEnumeration(DataAttachmentFormat)
implicit val dataAttachmentFormatEncoder: Encoder[DataAttachmentFormat.Value] = Decoder.encodeEnumeration(DataAttachmentFormat)

@JsonCodec case class EmailBody(
	html: String, 
	text: String
)

object EmailPriority extends Enumeration {
	type EmailPriority = Value

	val lowest = Value(0, "lowest")
	val low = Value(1, "low")
	val normal = Value(2, "normal")
	val high = Value(3, "high")
	val highest = Value(4, "highest")
}

implicit val emailPriorityDecoder: Decoder[EmailPriority.Value] = Decoder.decodeEnumeration(EmailPriority)
implicit val emailPriorityEncoder: Encoder[EmailPriority.Value] = Decoder.encodeEnumeration(EmailPriority)

@JsonCodec case class EmailResult(
	account: String, 
	message: EmailResult, 
	reason: String
)

@JsonCodec case class Email(
	bcc: Seq[String], 
	body: EmailBody, 
	cc: Seq[String], 
	from: String, 
	id: Id, 
	priority: EmailPriority, 
	reply_to: Seq[String], 
	sent_date: DateString, 
	subject: String, 
	to: Seq[String]
)

@JsonCodec case class Index(
	index: IndexName, 
	doc_id: Id
)

@JsonCodec case class IndexResult(
	response: IndexResultSummary
)

@JsonCodec case class IndexResultSummary(
	created: Boolean, 
	id: Id, 
	index: IndexName, 
	result: Result, 
	version: VersionNumber, 
	`type`: Type
)

@JsonCodec case class Logging(
	level: String, 
	text: String
)

@JsonCodec case class LoggingResult(
	logged_text: String
)

@JsonCodec case class WebhookResult(
	request: HttpInputRequestResult, 
	response: HttpInputResponseResult
)

@JsonCodec case class HttpInputRequestResult extends HttpInputRequestDefinition

@JsonCodec case class HttpInputResponseResult(
	body: String, 
	headers: HttpHeaders, 
	status: integer
)
