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

package org.elasticsearch.circecodecs.types.analysis

import io.circe._, io.circe.generic.semiauto._
import io.circe.generic.JsonCodec, io.circe.syntax._
import org.elasticsearch.circecodecs.types.common.{ VersionString }
import org.elasticsearch.circecodecs.types.Numeric.{ integer }

@JsonCodec case class TokenizerBase(
	`type`: String, 
	version: VersionString
)

@JsonCodec case class EdgeNGramTokenizer(
	custom_token_chars: String, 
	max_gram: integer, 
	min_gram: integer, 
	token_chars: Seq[TokenChar]
) extends TokenizerBase

@JsonCodec case class NGramTokenizer(
	custom_token_chars: String, 
	max_gram: integer, 
	min_gram: integer, 
	token_chars: Seq[TokenChar]
) extends TokenizerBase

object TokenChar extends Enumeration {
	type TokenChar = Value

	val letter = Value(0, "letter")
	val digit = Value(1, "digit")
	val whitespace = Value(2, "whitespace")
	val punctuation = Value(3, "punctuation")
	val symbol = Value(4, "symbol")
	val custom = Value(5, "custom")
}

implicit val tokenCharDecoder: Decoder[TokenChar.Value] = Decoder.decodeEnumeration(TokenChar)
implicit val tokenCharEncoder: Encoder[TokenChar.Value] = Decoder.encodeEnumeration(TokenChar)

@JsonCodec case class CharGroupTokenizer(
	tokenize_on_chars: Seq[String]
) extends TokenizerBase

@JsonCodec case class KeywordTokenizer(
	buffer_size: integer
) extends TokenizerBase

@JsonCodec case class LetterTokenizer extends TokenizerBase

@JsonCodec case class LowercaseTokenizer extends TokenizerBase

object NoriDecompoundMode extends Enumeration {
	type NoriDecompoundMode = Value

	val discard = Value(0, "discard")
	val none = Value(1, "none")
	val mixed = Value(2, "mixed")
}

implicit val noriDecompoundModeDecoder: Decoder[NoriDecompoundMode.Value] = Decoder.decodeEnumeration(NoriDecompoundMode)
implicit val noriDecompoundModeEncoder: Encoder[NoriDecompoundMode.Value] = Decoder.encodeEnumeration(NoriDecompoundMode)

@JsonCodec case class NoriTokenizer(
	decompound_mode: NoriDecompoundMode, 
	discard_punctuation: Boolean, 
	user_dictionary: String, 
	user_dictionary_rules: Seq[String]
) extends TokenizerBase

@JsonCodec case class PathHierarchyTokenizer(
	buffer_size: integer, 
	delimiter: String, 
	replacement: String, 
	reverse: Boolean, 
	skip: integer
) extends TokenizerBase

@JsonCodec case class PatternTokenizer(
	flags: String, 
	group: integer, 
	pattern: String
) extends TokenizerBase

@JsonCodec case class StandardTokenizer(
	max_token_length: integer
) extends TokenizerBase

@JsonCodec case class UaxEmailUrlTokenizer(
	max_token_length: integer
) extends TokenizerBase

@JsonCodec case class WhitespaceTokenizer(
	max_token_length: integer
) extends TokenizerBase
type Tokenizer = CharGroupTokenizer | EdgeNGramTokenizer | KeywordTokenizer | LetterTokenizer | LowercaseTokenizer | NGramTokenizer | NoriTokenizer | PathHierarchyTokenizer | StandardTokenizer | UaxEmailUrlTokenizer | WhitespaceTokenizer
