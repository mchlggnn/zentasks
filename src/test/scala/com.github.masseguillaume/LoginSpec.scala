package com.github.masseguillaume

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

import org.scalatest.selenium.WebBrowser
import org.scalatest.selenium.Firefox
import org.scalatest.selenium.WebBrowser.{click, Query}

import org.openqa.selenium.WebElement

class LoginSpec
  /* extends JettySpec */
  extends FunSpec
  with ShouldMatchers
  with WebBrowser
  with Firefox {

  val playHost = "http://localhost:9000"
  implicit def workAround(query: Query): WebElement = query.getWebElement // scalatest bug

  private def login( email: String, password: String )
  {
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

  describe( "login" ) {
    it( "should denny a user with incorrect credentials" ) {
      go to ( playHost )
      login( "1", "1" )

      classWithText( "error", "Invalid email or password" ) should be( true )

      find("projects") should be( None )
    }
  }

  describe( "login/logout" ) {
    it( "should show main page then clear user credential and go back to login page" ) {
      go to ( playHost )
      login( "guillaume@sample.com", "secret" )
      
      find("projects") should not be None

      click on linkText( "Logout" )
      classWithText( "success", "You've been logged out" ) should be( true )
    }
  }
}