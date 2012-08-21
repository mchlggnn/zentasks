package com.github.masseguillaume.zentasks.snippet

import net.liftweb.http.{S, SHtml}
import net.liftweb.http.js.JsCmds
import net.liftweb.util.Helpers._

import xml.Text


case class Date(month:String, day:String, year:String)

case class Task(title:String, date:Date, user:String)




class Item {
      val taskList = List( Task("Tâche no.1", Date("08","18","2012"), "Michel Gagnon"),
                           Task("Tâche no.2", Date("08","27","2012"), "Guillaume Massé") )	

      def renderwithcheckbox  = 
      	 "ul" #> taskList.map(item => 
            ("h4 *" #> item.title &
             "time *" #> Text(item.date.month + "//" + item.date.day + "//" + item.date.year) &
             ".assignedTo *" #> item.user ))

      def renderwithoutcheckbox  = 
      	 "ul" #> taskList.map(item => 
            ("h4 *" #> item.title &
             "input" #> Text("") &
             "time *" #> Text(item.date.month + "/" + item.date.day + "/" + item.date.year) &
             ".assignedTo *" #> item.user ))


               
}


