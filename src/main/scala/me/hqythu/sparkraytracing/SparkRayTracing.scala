/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing

import java.io.File
import javax.imageio.ImageIO

import me.hqythu.sparkraytracing.tracer.{Camera, Tracer}
import me.hqythu.sparkraytracing.utils.JsonReader
import org.apache.spark.{SparkConf, SparkContext}

object SparkRayTracing {
  def main(args: Array[String]) {
    val jsonReader = new JsonReader
    val tmp = jsonReader.parser("Scene.json")
    val camera = tmp._1
    val scene = tmp._2

    val tracer = new Tracer(camera, scene)
    val img = tracer.runspark()
//    val img = tracer.run()

    ImageIO.write(img, "png", new File("img.png"))
//    System.in.read()
  }
}
