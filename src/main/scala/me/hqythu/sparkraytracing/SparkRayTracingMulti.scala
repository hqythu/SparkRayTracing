/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing

import java.io.File
import javax.imageio.ImageIO

import me.hqythu.sparkraytracing.tracer.Tracer
import me.hqythu.sparkraytracing.utils.JsonReader

object SparkRayTracingMulti {
  def main(args: Array[String]) {
    val jsonReader = new JsonReader
    val basePath = new File("scenes")
    val files = basePath.listFiles()
    for (file <- files) {
      val filename = file.getName
      val i = filename.substring(filename.indexOf('_') + 1, filename.indexOf('.')).toInt
      val tmp = jsonReader.parser(file)
      val camera = tmp._1
      val scene = tmp._2
      val tracer = new Tracer(camera, scene)
      val img = tracer.runspark()
      ImageIO.write(img, "png", new File("output/img_" + i.toString + ".png"))
    }
  }
}
