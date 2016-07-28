/**
  * Created by wk on 16/7/27.
  */

package me.hqythu.sparkraytracing.utils

import java.io.File

import me.hqythu.sparkraytracing.objects._
import me.hqythu.sparkraytracing.tracer.{Camera, Scene}
import org.json4s.DefaultFormats
import org.json4s.native.JsonMethods._

import scala.collection.mutable.ArrayBuffer

class JsonReader {
  private def parseVecter3(obj: List[Double]): Vector3 = {
    new Vector3(obj(0), obj(1), obj(2))
  }

  private def parseColor(obj: List[Double]): Color = {
    new Color(obj(0), obj(1), obj(2))
  }

  private def parseMaterial(obj: Map[String, _]): Material = {
    new Material(
      parseColor(obj.get("color").get.asInstanceOf[List[Double]]),
      obj.get("index").get.asInstanceOf[Double],
      obj.get("diffIndex").get.asInstanceOf[Double],
      obj.get("reflIndex").get.asInstanceOf[Double],
      obj.get("refrIndex").get.asInstanceOf[Double]
    )
  }

  def parser(fileName: String): (Camera, Scene) = {
    val json = parse(new File(fileName))
    val objs = new ArrayBuffer[AbstractObject]
    val lights = new ArrayBuffer[Light]
    for (obj <- (json \ "Objects").values.asInstanceOf[List[Map[String, _]]]) {
      if (obj.get("type").get.equals("Sphere")) {
        objs.append(new Sphere(
          parseVecter3(obj.get("center").get.asInstanceOf[List[Double]]),
          obj.get("radius").get.asInstanceOf[Double],
          parseMaterial(obj.get("material").get.asInstanceOf[Map[String, _]])
        ))
      } else if (obj.get("type").get.equals("Plane")) {
        objs.append(new Plane(
          parseVecter3(obj.get("position").get.asInstanceOf[List[Double]]),
          parseVecter3(obj.get("normal").get.asInstanceOf[List[Double]]),
          parseMaterial(obj.get("material").get.asInstanceOf[Map[String, _]])
        ))
      }
    }
    for (obj <- (json \ "Lights").values.asInstanceOf[List[Map[String, _]]]) {
      lights.append(new Light(
        parseVecter3(obj.get("position").get.asInstanceOf[List[Double]]),
        parseColor(obj.get("color").get.asInstanceOf[List[Double]])
      ))
    }
    implicit val formats = DefaultFormats
    val tmp = (json \ "Camera").values.asInstanceOf[Map[String, _]]
    val camera = new Camera(
      parseVecter3(tmp.get("position").get.asInstanceOf[List[Double]]),
      parseVecter3(tmp.get("front").get.asInstanceOf[List[Double]]),
      parseVecter3(tmp.get("up").get.asInstanceOf[List[Double]]),
      (json \ "width").values.asInstanceOf[Int],
      (json \ "height").values.asInstanceOf[Int],
      tmp.get("ratio").get.asInstanceOf[Double]
    )
    (camera, new Scene(objs.toArray, lights.toArray))
  }
}
