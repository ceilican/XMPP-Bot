package at.logic.xmppb
package commandHandler

class Google() extends CommandHandler {
  val command = "google"
  def handle(query: String, callback: String => Unit) = {
    callback("http://lmgtfy.com/?q=" + query.replaceAll(" ", "+"))
  }
}


//
///**
// * UmbrellaToday.com (NYC only)
// */
//class Umbrella() extends CommandHandler {
//  def handle(c: CommandData) = c match {
//    case CommandData("umbrella", data) => CommandHandlerResponse(find())
//    case _ => CommandHandlerResponse(None)
//  }
//
//  private def find() = {
//    val AnswerPattern = """<h3>\s*<span>(YES|NO)</span>\s*</h3>""".r
//    val u = url("http://umbrellatoday.com/locations/596360971/forecast")
//    val source = Http(u as_str)
//    AnswerPattern.findFirstMatchIn(source) match {
//      case Some(found: Match) => Some(found.group(1))
//      case _ => None
//    }
//  }
//}