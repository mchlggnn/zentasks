package com.github.masseguillaume.zentasks

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec

import org.openqa.selenium.{Keys, By}
import org.openqa.selenium.interactions.Actions

class GroupSpec
  extends FunSpec
  with ShouldMatchers
  with LoggedApplicationSuite
{
  describe( "new group" ) {
    it("should add a new group to the project list")
    {
      val groupCount = findAll( cssSelector( "#projects > li[data-group]") ).size

      click on id( "newGroup" )

      val newGroupCount = findAll( cssSelector( "#projects > li[data-group]") ).size

      newGroupCount should be( groupCount + 1 )
    }
  }

  describe ( "group" ) {
    it( "can be rename when double clicked" )
    {
      val cssFirstGroup = "#projects > [data-group]:first-child"
      find( cssSelector( cssFirstGroup + " .groupName") ) match {
        case Some( element ) => {

          val actions = new Actions( webDriver )
          actions.doubleClick( element.underlying )

          val newName = "new name"
          val input = textField( cssSelector( cssFirstGroup + " input" ) )
          input.value = newName
          input.underlying.sendKeys( Keys.ENTER )

          element.underlying.getText should be ( newName )
        }
        case _ => fail( "cannot find a group" )
      }
    }

    it( "should be possible to delete" )
    {

    }

    it( "should be possible to add new project" )
    {

    }
  }
}
