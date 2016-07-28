package me.hqythu.sparkraytracing.utils

import com.esotericsoftware.kryo.Kryo
import me.hqythu.sparkraytracing.objects.{AbstractObject, Light}
import me.hqythu.sparkraytracing.tracer.Ray
import me.hqythu.sparkraytracing.tracer.Scene
import me.hqythu.sparkraytracing.utils.Vector3
import org.apache.spark.serializer.KryoRegistrator

/**
  * Created by wk on 16/7/27.
  */

class MyRegisterKryo extends KryoRegistrator {
  override def registerClasses(kryo: Kryo): Unit = {
    kryo.register(classOf[Light])
    kryo.register(classOf[AbstractObject])
    kryo.register(classOf[Ray])
    kryo.register(classOf[Scene])
    kryo.register(classOf[Vector3])
    kryo.register(classOf[Color])
  }
}