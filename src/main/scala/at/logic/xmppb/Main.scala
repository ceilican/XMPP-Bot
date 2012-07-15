package at.logic.xmppb

import at.logic.xmppb.commandHandler._

object Main{

  def main(args: Array[String]){

    //val google = new Google()
    //val umbrella = new Umbrella()
    val greet = new Greet()
    
//    username = args(0)
//    password = args(1)
//    server = args(2)
    
    val bot = new XMPPBot(args(0), args(1), args(2), greet).start
    bot ! "!greet"
    
    bot ! 'stop
  }
}