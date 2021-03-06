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
import org.elasticsearch.circecodecs.types.Numeric.{ integer }
import org.elasticsearch.circecodecs.types.analysis.{ TokenFilterBase }

object PhoneticEncoder extends Enumeration {
	type PhoneticEncoder = Value

	val metaphone = Value(0, "metaphone")
	val double_metaphone = Value(1, "double_metaphone")
	val soundex = Value(2, "soundex")
	val refined_soundex = Value(3, "refined_soundex")
	val caverphone1 = Value(4, "caverphone1")
	val caverphone2 = Value(5, "caverphone2")
	val cologne = Value(6, "cologne")
	val nysiis = Value(7, "nysiis")
	val koelnerphonetik = Value(8, "koelnerphonetik")
	val haasephonetik = Value(9, "haasephonetik")
	val beider_morse = Value(10, "beider_morse")
	val daitch_mokotoff = Value(11, "daitch_mokotoff")
}

implicit val phoneticEncoderDecoder: Decoder[PhoneticEncoder.Value] = Decoder.decodeEnumeration(PhoneticEncoder)
implicit val phoneticEncoderEncoder: Encoder[PhoneticEncoder.Value] = Decoder.encodeEnumeration(PhoneticEncoder)

object PhoneticLanguage extends Enumeration {
	type PhoneticLanguage = Value

	val any = Value(0, "any")
	val comomon = Value(1, "comomon")
	val cyrillic = Value(2, "cyrillic")
	val english = Value(3, "english")
	val french = Value(4, "french")
	val german = Value(5, "german")
	val hebrew = Value(6, "hebrew")
	val hungarian = Value(7, "hungarian")
	val polish = Value(8, "polish")
	val romanian = Value(9, "romanian")
	val russian = Value(10, "russian")
	val spanish = Value(11, "spanish")
}

implicit val phoneticLanguageDecoder: Decoder[PhoneticLanguage.Value] = Decoder.decodeEnumeration(PhoneticLanguage)
implicit val phoneticLanguageEncoder: Encoder[PhoneticLanguage.Value] = Decoder.encodeEnumeration(PhoneticLanguage)

object PhoneticNameType extends Enumeration {
	type PhoneticNameType = Value

	val generic = Value(0, "generic")
	val ashkenazi = Value(1, "ashkenazi")
	val sephardic = Value(2, "sephardic")
}

implicit val phoneticNameTypeDecoder: Decoder[PhoneticNameType.Value] = Decoder.decodeEnumeration(PhoneticNameType)
implicit val phoneticNameTypeEncoder: Encoder[PhoneticNameType.Value] = Decoder.encodeEnumeration(PhoneticNameType)

object PhoneticRuleType extends Enumeration {
	type PhoneticRuleType = Value

	val approx = Value(0, "approx")
	val exact = Value(1, "exact")
}

implicit val phoneticRuleTypeDecoder: Decoder[PhoneticRuleType.Value] = Decoder.decodeEnumeration(PhoneticRuleType)
implicit val phoneticRuleTypeEncoder: Encoder[PhoneticRuleType.Value] = Decoder.encodeEnumeration(PhoneticRuleType)

@JsonCodec case class PhoneticTokenFilter(
	encoder: PhoneticEncoder, 
	languageset: Seq[PhoneticLanguage], 
	max_code_len: integer, 
	name_type: PhoneticNameType, 
	replace: Boolean, 
	rule_type: PhoneticRuleType
) extends TokenFilterBase
