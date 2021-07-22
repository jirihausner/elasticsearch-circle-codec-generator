package cz.jirihausner.es_codec_generator

import better.files.File

object Configuration {
  val inputDir: File = File.currentWorkingDirectory / "elasticsearch-specification" / "output" / "typescript"
  val outputDir: File = File.currentWorkingDirectory / "output" // "src" / "main" / "scala"
  val packageName: String = "com.converted.elasticsearch"
  val debug: Boolean = false
}
