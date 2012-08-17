package com.github.masseguillaume.zentasks

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSpec}
import org.scalatest.matchers.ShouldMatchers

import org.scalatest.selenium.Firefox
import org.openqa.selenium.WebElement

class LoginSpec
  /* extends JettySpec */
  extends FunSpec
  with ShouldMatchers
  with ApplicationSuite
{
  describe( "login" ) {
    it( "should denny a user with incorrect credentials" ) {

      incorectLogin()

      classWithText( "error", "Invalid email or password" ) should be( true )

      find("projects") should be( None )
    }
  }

  describe( "login/logout" ) {
    it( "should show main page then clear user credential and go back to login page" ) {

      corectLogin()

      find("projects") should not be None

      click on linkText( "Logout" )
      classWithText( "success", "You've been logged out" ) should be( true )
    }
  }
}