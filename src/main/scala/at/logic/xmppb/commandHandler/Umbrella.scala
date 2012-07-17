package at.logic.xmppb
package commandHandler

import dispatch.{url, Http}


// UmbrellaToday.com (Forecast for Vienna, Austria)
class Umbrella() extends CommandHandler {
  val command = "umbrella"
  def handle(rest: String) = {
    val ExtractorRegExp = """<h3>\s*<span>(YES|NO)</span>\s*</h3>""".r
    val source = Http(url("http://umbrellatoday.com/locations/1073763541/forecast") as_str)
    ExtractorRegExp.findFirstMatchIn(source) match {
      case Some(found) => found.group(1).toLowerCase
      case _ => "Sorry. I don't know."
    }
  }
}