package me.hqythu.sparkraytracing

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by hqythu on 7/17/16.
  */
object SparkRayTracing {
  def main(args: Array[String]) {
    val logFile = "/Users/hqythu/dev/tensorpack/examples/DoReFa-Net/README.md" // Should be some file on your system
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
