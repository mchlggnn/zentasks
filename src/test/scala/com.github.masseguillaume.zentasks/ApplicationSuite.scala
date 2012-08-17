package com.github.masseguillaume.zentasks

import org.scalatest.{FunSpec, BeforeAndAfterAll}
import org.scalatest.selenium.Firefox
import org.scalatest.selenium.WebBrowser.{click, go, Query}
import org.openqa.selenium.WebElement

trait LoggedApplicationSuite
  extends ApplicationSuite { self: FunSpec =>
  override def beforeAll() {
    corectLogin()
  }
}

trait ApplicationSuite extends BeforeAndAfterAll with Firefox { this: FunSpec =>

  val playHost = "http://localhost:9000"

  implicit def workAround(query: Query): WebElement = query.getWebElement // scalatest bug

  protected def corectLogin() { login( "guillaume@sample.com", "secret" ) }
  protected def incorectLogin() { login( "1", "1" ) }

  private def login( email: String, password: String )
  {
    go to ( playHost )

    click on id( "email" )
    textField( "email" ).value = email

    click on id( "password" )
    textField( "password" ).value = password

    submit
  }

  def classWithText( clazz: String, text: String ): Boolean =
  {
    findAll( className( clazz ) ).exists( element => {
      element.underlying.getText == text
    })
  }

  override def afterAll(){
  //  close()
  }
}