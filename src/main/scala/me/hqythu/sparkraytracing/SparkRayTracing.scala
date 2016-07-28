/**
  * Created by hqythu on 7/17/16.
  */

package me.hqythu.sparkraytracing

import me.hqythu.sparkraytracing.tracer.{Camera, Tracer}
import me.hqythu.sparkraytracing.utils.JsonReader
import org.apache.spark.{SparkConf, SparkContext}

object SparkRayTracing {
  def main(args: Array[String]) {
    val logFile = "/Users/hqythu/dev/tensorpack/examples/DoReFa-Net/README.md" // Should be some file on your system
    val conf = new SparkConf()
      .setAppName("raytracer")
      .setMaster("local[8]")
      .set("spark.executor.heartbeatInterval", "1000000")
//      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
//      .set("spark.kryo.registrator", "me.hqythu.sparkraytracing.utils.MyRegisterKryo")

    val sc = SparkContext.getOrCreate(conf)

    val jsonReader = new JsonReader
    val tmp = jsonReader.parser("Scene.json")
    val camera = tmp._1
    val scene = tmp._2
    val tracer = new Tracer(camera, scene)
    tracer.runspark(sc)
  }
}
