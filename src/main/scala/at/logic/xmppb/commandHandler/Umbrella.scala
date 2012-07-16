package at.logic.xmppb
package commandHandler

import dispatch.{url, Http}


// UmbrellaToday.com (Forecast for Vienna, Austria)
class Umbrella() extends CommandHandler {
  val command = "umbrella"
  def handle(rest: String, callback: String => Unit) = {
    val ExtractorRegExp = """<h3>\s*<span>(YES|NO)</span>\s*</h3>""".r
    val source = Http(url("http://umbrellatoday.com/locations/1073763541/forecast") as_str)
    ExtractorRegExp.findFirstMatchIn(source) match {
      case Some(found) => callback(found.group(1).toLowerCase)
      case _ => 
    }
    println(sender.getClass())
    sender ! "bla"
  }
}