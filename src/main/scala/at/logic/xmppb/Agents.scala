package at.logic.xmppb
package commandHandler

import dispatch.{url, Http}
import util.matching.Regex.{Match}

/**
 * Simple Greeting
 */
class Greet() extends CommandHandler {
  def handle(c: CommandData) = c match {
    case CommandData("greet", data) => Some("Hi.")
    case _ => None
  }
}

/**
 * Let Me Google That For You
 */
//class Google() extends CommandHandler {
//  def handle(c: CommandData) = c match {
//    case CommandData("google", data) => CommandHandlerResponse(Some("http://lmgtfy.com/?q=" + normalize(data)))
//    case _ => CommandHandlerResponse(None)
//  }
//  def normalize(data: String) = data.replaceAll(" ", "+")
//}
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