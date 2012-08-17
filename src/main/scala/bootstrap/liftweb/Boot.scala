package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._

class Boot {
  def boot {

		LiftRules.addToPackages("com.github.masseguillaume.zentasks")

		val entries = List( 
			Menu.i("Home") / "index",
			Menu.i("Login") / "login",
			Menu.i("Project") / "project"
		)

		LiftRules.setSiteMap(SiteMap(entries:_*))

		LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

		LiftRules.htmlProperties.default.set( (r: Req) =>
			new Html5Properties(r.userAgent)
		)

		LiftRules.jsArtifacts = JQueryArtifacts
		JQueryModule.InitParam.JQuery=JQueryModule.JQuery172
		JQueryModule.init()
	}
}
